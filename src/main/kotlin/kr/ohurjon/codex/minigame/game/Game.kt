package kr.ohurjon.codex.minigame.game

import kr.ohurjon.codex.minigame.game.event.jump.JumpGameEnd
import kr.ohurjon.codex.minigame.game.event.jump.JumpGameStart
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask
import kr.ohurjon.codex.minigame.game.event.jump.JumpGameTime
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar


class Game( val player: Player , val type: GameType) : Default() {

    lateinit var task : BukkitTask

    var maxTick = type.getMaxTick()

    var bossBar : BossBar = server.createBossBar("타이틀",BarColor.GREEN,BarStyle.SEGMENTED_10)

    var tick : Long = 0L

    init {
        if(type == GameType.JUMP){
            task = jump()
            plugin.callEvent(JumpGameStart(player,this))
        }
        GameManager().addGame(player,this)
        bossBar.addPlayer(player)
    }

    fun jump(): BukkitTask {

        return server.scheduler.runTaskTimerAsynchronously(plugin, {
            tick++
            plugin.callEvent(JumpGameTime(player, this, tick))
        },0L, 1L)

    }

    fun getTimeToStamp() : String {
        val min = tick / 60 * 20
        val sec = tick % 60 * 20 / 60
        val tick = tick % sec
        return String.format("%02d:%02d:%02d",min,sec,tick)
    }
}