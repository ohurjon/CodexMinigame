package kr.ohurjon.codex.minigame.game

import kr.ohurjon.codex.minigame.game.event.GameTime
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar


class Game( val player: Player , val type: GameType) : Default() {

    val task : BukkitTask = start()

    var tick : Long = 0L

    var cancelled = false

    init {
        GameManager().addGame(player,this)
        task.cancel()
    }

    fun start() : BukkitTask {
        return server.scheduler.runTaskTimerAsynchronously(plugin, {
            if(!cancelled) {
                tick++
                plugin.callEvent(GameTime(player, this, tick))
            }
        },0L, 1L)
    }

    fun stop() {
        task.cancel()
        cancelled = true
        GameManager().removeGame(player)
    }

    fun getTimeToStamp() : String {
        val time = tick/20
        val sec = (tick%20) * 5
        return String.format("%d.%02d",time,sec)
    }
}