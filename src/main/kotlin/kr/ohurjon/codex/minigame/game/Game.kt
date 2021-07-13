package kr.ohurjon.codex.minigame.game

import kr.ohurjon.codex.minigame.game.event.GameTime
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Entity


class Game( val player: Player , val type: GameType) : Default() {

    val task : BukkitTask = start()

    var tick : Long = 0L

    var cancelled = false

    var entitys = ArrayList<Entity>()

    var roomnumber: Int = getRoomNumber()

    init {
        GameManager().addGame(player,this)
        if(type == GameType.SHULKER)  GameManager.shulker.add(roomnumber)
        if(type == GameType.TAKGU) GameManager.takgu.add(roomnumber)
    }

    fun start() : BukkitTask {
        return server.scheduler.runTaskTimer(plugin, {
            if(!cancelled) {
                tick += 1
                plugin.callEvent(GameTime(player,this))
            }
        },0L, 1L)
    }

    fun stop() {

        if(type == GameType.SHULKER) GameManager.shulker.remove(roomnumber)
        if(type == GameType.TAKGU) GameManager.takgu.remove(roomnumber)
        player.inventory.clear()
        task.cancel()
        cancelled = true
        GameManager().removeGame(player)
        entitys.forEach { entity:Entity ->
            entity.remove()
        }

    }

    fun getTimeToStamp() : String {
        val time = tick/20
        val sec = (tick%20) * 5
        return String.format("%d.%02d",time,sec)
    }

    fun getRoomNumber(): Int {
        if(type == GameType.SHULKER) {
            return lower(GameManager.shulker,0)
        }
        if(type == GameType.TAKGU) {
            return lower(GameManager.takgu,0)
        }
        return -1
    }

    fun lower(set : HashSet<Int>,i : Int) : Int{
        if(!set.contains(i)){
            return i
        }
        return lower(set,i + 1)
    }
}