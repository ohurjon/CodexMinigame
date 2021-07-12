package kr.ohurjon.codex.minigame.game.listener

import kr.ohurjon.codex.minigame.game.Game
import kr.ohurjon.codex.minigame.game.GameManager
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.game.event.*
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoard
import kr.ohurjon.codex.minigame.util.Default
import kr.ohurjon.codex.minigame.util.Item
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent


class GameEventListener : Listener, Default() {
    @EventHandler
    fun GameTime(event: GameTime) {
        event.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("Time : "+event.game.getTimeToStamp()))
    }

    @EventHandler
    fun JumpEmerald(event: PlayerMoveEvent) {
        val game = GameManager().getGame(event.player)
        if (game != null) {
            if (game.type == GameType.JUMP) {
                if (event.to.block.getRelative(BlockFace.DOWN).type == Material.EMERALD_BLOCK) {
                    plugin.callEvent(GameEnd(event.player, game, game.tick))
                    event.player.sendTitle("성공 하셨습니다!", game.getTimeToStamp(), 60, 200, 60)
                }
            }
        }
    }

    @EventHandler
    fun GameEnd(event: GameEnd) {
        event.game.stop()
        event.player.teleport(spawn)
        GameManager().removeGame(event.player)
        LeaderBoard(event.player.name, event.time, event.game.type)
    }


    @EventHandler
    fun GameStart(event: GameStart) {
        event.game.start()
        val world = server.getWorld("world-jump")
        event.player.teleport(Location(world, 0.5, 88.0, 4.5, 180f, 0f))
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
    }

    @EventHandler
    fun InteractClick(event: PlayerInteractEvent) {
        val game = GameManager().getGame(event.player)
        if (game != null) {
            if (event.item.type == Material.WATCH) {
                plugin.callEvent(GameRespawn(event.player, game))
            }
            if (event.item.type == Material.BARRIER) {
                plugin.callEvent(GameLeave(event.player,game))
            }
        }
    }
}