package kr.ohurjon.codex.minigame.leaderboard

import kr.ohurjon.codex.minigame.game.GameType
import org.bukkit.entity.Player

class LeaderBoard(val name : String, var score : Long,var type : GameType) : Comparable<LeaderBoard> {

    init {
        LeaderBoardManager().addList(name,this)
    }

    override fun toString(): String {
        return name +" : "+score
    }

    override fun compareTo(other: LeaderBoard): Int {
        return when {
            this.score < other.score -> -1
            this.score > other.score -> 1
            else -> 0
        }
    }
}



