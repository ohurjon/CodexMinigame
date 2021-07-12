package kr.ohurjon.codex.minigame.game.listener.jump

import kr.ohurjon.codex.minigame.game.GameManager
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.game.event.jump.JumpGameEnd
import kr.ohurjon.codex.minigame.game.event.jump.JumpGameStart
import kr.ohurjon.codex.minigame.game.event.jump.JumpGameTime
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoard
import kr.ohurjon.codex.minigame.util.Default
import kr.ohurjon.codex.minigame.util.Item
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack

class JumpEventListener : Listener, Default() {
    @EventHandler
    fun JumpEmerald(event : PlayerMoveEvent){
        val game = GameManager().getGame(event.player)
        if(event.to.block.getRelative(BlockFace.DOWN).type == Material.EMERALD_BLOCK) {
            if(game!!.type == GameType.JUMP){
                plugin.callEvent(JumpGameEnd(event.player,game,game.tick))
            }
        }
    }
    @EventHandler
    fun JumpGameEnd(event: JumpGameEnd){
        event.player.sendTitle("성공 하셨습니다!",event.game.getTimeToStamp(),60,200,60)
        event.game.task.cancel()
        event.player.teleport(Location(server.getWorld("world"),0.5,4.0,3.5,-180f,0f))
        GameManager().removeGame(event.player)
        LeaderBoard(event.player.name,event.time, GameType.JUMP)
    }
    @EventHandler
    fun JumpGameTime(event: JumpGameTime){
        event.game.bossBar.title = event.game.tick.toString()
    }

    @EventHandler
    fun JumpGameStart(event: JumpGameStart){
        val world = server.getWorld("world-jump")
        event.player.teleport(Location(world,0.5,88.0,4.5,180f,0f))
        event.player.inventory.clear()
        event.player.inventory.setItem(8, Item.reset)
    }

    @EventHandler
    fun JumpRespawnClick(event: PlayerInteractEvent){
        val game = GameManager().getGame(event.player)
        if(event.item.type == Material.WATCH) {
            if(game!!.type == GameType.JUMP){
                plugin.callEvent(JumpGameEnd(event.player,game,game.tick))
            }
        }
    }
}