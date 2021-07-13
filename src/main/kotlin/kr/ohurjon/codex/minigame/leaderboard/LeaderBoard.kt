package kr.ohurjon.codex.minigame.leaderboard

import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.util.Util
import kr.ohurjon.codex.minigame.util.WorldUtilType
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

class LeaderBoard(val name : String, var score : Long,var type : GameType) : Comparable<LeaderBoard> {

    init {
        LeaderBoardManager().addList(this)
        reloadLeaderBoard()
    }

    override fun toString(): String {
        return name +" : "+getTimeToStamp(score)
    }

    override fun compareTo(other: LeaderBoard): Int {
        return when {
            this.score < other.score -> -1
            this.score > other.score -> 1
            else -> 0
        }
    }

    fun getTimeToStamp(tick : Long) : String {
        val time = tick/20
        val sec = (tick%20) * 5
        return String.format("%d.%02d",time,sec)
    }

    fun reloadLeaderBoard() {
        LeaderBoardManager().removeArmorStand()

        val jump = LeaderBoardManager().getList(GameType.JUMP)
        val shulker = LeaderBoardManager().getList(GameType.SHULKER)
        val takgu = LeaderBoardManager().getList(GameType.TAKGU)

        val location = Location(WorldUtilType.DEFAULT.world,0.0,0.0,0.0)

        var i = 1

        location.y = 7.0

        location.z = 20.5
        location.x = 5.5

        Util().ArmorStand("&l&e"+GameType.JUMP.getName(),location)
        location.y -= 0.5

        jump.forEach{ leaderBoard: LeaderBoard -> run {
            location.y -= 0.5
            Util().ArmorStand(i.toString()+". "+leaderBoard.toString(),location)
            i += 1
        }}

        location.x = 0.5
        location.y = 7.0

        Util().ArmorStand("&l&e"+GameType.SHULKER.getName(),location)
        location.y -= 0.5
        i = 1
        shulker.forEach{ leaderBoard: LeaderBoard -> run {
            location.y -= 0.5
            Util().ArmorStand(i.toString()+". "+leaderBoard.toString(),location)
            i += 1
        }}

        location.x = -4.5
        location.y = 7.0

        i = 1
        Util().ArmorStand("&l&e"+GameType.TAKGU.getName(),location)
        location.y -= 0.5
        takgu.forEach{ leaderBoard: LeaderBoard -> run {
            location.y -= 0.5
            Util().ArmorStand(i.toString()+". "+leaderBoard.toString(),location)
            i += 1
        }}

    }
}



