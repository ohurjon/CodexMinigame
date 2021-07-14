package kr.ohurjon.codex.minigame.game.listener

import kr.ohurjon.codex.minigame.CODEX
import kr.ohurjon.codex.minigame.game.Game
import kr.ohurjon.codex.minigame.game.GameManager
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.game.event.*
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoard
import kr.ohurjon.codex.minigame.util.Default
import kr.ohurjon.codex.minigame.util.Item
import kr.ohurjon.codex.minigame.util.Util
import kr.ohurjon.codex.minigame.util.WorldUtilType
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityTeleportEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import sun.audio.AudioPlayer.player


class GameEventListener : Listener, Default() {
    @EventHandler
    fun GameTime(event: GameTime) {
        val n = event.game.roomnumber
        event.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("§7Time : "+event.game.getTimeToStamp()))
        if(event.game.type == GameType.SHULKER && (event.game.tick % 200) == 0L){

            val x = Util().randomRange(n*34+11,n*34+23).toDouble()
            val z = Util().randomRange(n*35+13,n*35+25).toDouble()

            val loc = Location(WorldUtilType.SHULKER.world,x,5.0,z)

            event.game.entitys.add(plugin.spawnEntity(loc,EntityType.SHULKER))
        }
        if(event.game.type == GameType.TAKGU && (event.game.tick % 100) == 0L){
            val x = Util().randomRange(n*45+4,n*45+40).toDouble()
            val z = Util().randomRange(n*45+4,n*45+40).toDouble()

            val loc = Location(WorldUtilType.TAKGU.world,x,25.0,z)

            event.game.entitys.add(plugin.spawnEntity(loc,EntityType.GHAST))
        }
    }

    @EventHandler
    fun JumpEmerald(event: PlayerMoveEvent) {
        val game = GameManager().getGame(event.player)
        if (game != null) {
            if (game.type == GameType.JUMP) {
                if (event.to.block.getRelative(BlockFace.DOWN).type == Material.EMERALD_BLOCK) {
                    plugin.callEvent(GameEnd(event.player, game))
                }
            }
        }
    }

    @EventHandler
    fun EntityDeath(event: EntityDeathEvent){
        val player = event.entity.killer
        if( player != null) {
            val game = GameManager().getGame(player)
            if(game != null){
                if(game.type == GameType.TAKGU && event.entityType == EntityType.GHAST){
                    game.tick += 100L
                }
            }
        }
    }

    @EventHandler
    fun GameDeathEvent(event: EntityDamageEvent){
        if(event.entity is Player){
            val player = event.entity as Player
            val game = GameManager().getGame(player)
            if(game != null){
                if(player.health <= event.damage){
                    if(game.type == GameType.JUMP) {
                        plugin.callEvent(GameRespawn(player,game))
                    } else {
                        plugin.callEvent(GameEnd(player,game))

                    }
                    event.damage = 0.0
                }
            }
        }
    }


    @EventHandler
    fun GameEnd(event: GameEnd) {
        event.game.stop()
        event.player.teleport(spawn)
        GameManager().removeGame(event.player)
        server.scheduler.runTaskLater(plugin,{event.player.sendTitle(event.player.name + "님의 점수", event.game.getTimeToStamp(), 60, 100, 60)
            event.player.health = 20.0
            event.player.removePotionEffect(PotionEffectType.LEVITATION)
            event.player.removePotionEffect(PotionEffectType.SLOW)
            event.player.removePotionEffect(PotionEffectType.JUMP)}, 20L)
        LeaderBoard(event.player, event.game.tick, event.game.type)
    }

    @EventHandler
    fun GameStart(event: GameStart) {
        when(event.game.type){
            GameType.JUMP -> {
                event.player.teleport(Location(WorldUtilType.JUMP.world, 0.5, 88.0, 4.5, 180f, 0f))
            }
            GameType.SHULKER -> {
                event.player.teleport(Location(WorldUtilType.SHULKER.world,(event.game.roomnumber)*34 + 17.5,5.0,(event.game.roomnumber)*36 + 19.5))
            }
            GameType.TAKGU -> {
                event.player.teleport(Location(WorldUtilType.TAKGU.world,(event.game.roomnumber)*44+23.0,6.0,(event.game.roomnumber)*44+23.0))
                val potions = event.player.activePotionEffects
                potions.add(PotionEffect(PotionEffectType.SLOW,Int.MAX_VALUE,3,false,false))
                potions.add(PotionEffect(PotionEffectType.JUMP,Int.MAX_VALUE, 128, false,false))
                event.player.addPotionEffects(potions)
            }
            else -> {
                event.player.teleport(CODEX.spawn)
            }
        }

        event.player.inventory.clear()
        event.player.inventory.setItem(7, Item().reset())
        event.player.inventory.setItem(8, Item().leave())
    }

    @EventHandler
    fun GameRespawn(event: GameRespawn) {
        event.game.stop()
        plugin.callEvent(GameStart(event.player, Game(event.player, event.game.type)))
    }

    @EventHandler
    fun GameLeave(event: GameLeave) {
        event.game.stop()
        event.player.teleport(spawn)
        event.player.health = 20.0
        event.player.removePotionEffect(PotionEffectType.LEVITATION)
        event.player.removePotionEffect(PotionEffectType.SLOW)
        event.player.removePotionEffect(PotionEffectType.JUMP)
    }

    @EventHandler
    fun InteractClick(event: PlayerInteractEvent) {
        val game = GameManager().getGame(event.player)
        if (game != null && event.item !=null) {
            if (event.item.type == Material.WATCH) {
                plugin.callEvent(GameRespawn(event.player, game))
            }
            if (event.item.type == Material.BARRIER) {
                plugin.callEvent(GameLeave(event.player,game))
            }
            event.isCancelled = true
        }
    }
}