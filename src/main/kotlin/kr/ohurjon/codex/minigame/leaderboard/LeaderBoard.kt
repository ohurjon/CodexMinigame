package kr.ohurjon.codex.minigame.leaderboard

class LeaderBoard(val student : Student, var score : Int) : Comparable<LeaderBoard> {

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



