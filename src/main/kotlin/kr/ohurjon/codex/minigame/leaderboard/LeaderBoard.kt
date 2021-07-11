package kr.ohurjon.codex.minigame.leaderboard

import org.bukkit.entity.Player

class LeaderBoard(val student : Player, var score : Int) : Comparable<LeaderBoard> {

    init {
        LeaderBoardManager().addList(this)

    }

    override fun compareTo(other: LeaderBoard): Int {
        return when {
            this.score < other.score -> -1
            this.score > other.score -> 1
            else -> 0
        }
    }
}



