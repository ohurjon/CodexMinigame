package kr.ohurjon.codex.minigame

import kr.ohurjon.codex.minigame.games.Game
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoard
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoardManager
import kr.ohurjon.codex.minigame.leaderboard.Student
import org.bukkit.Bukkit

fun main(){
    LeaderBoard(Student("1",1),123)
    LeaderBoard(Student("3",3),256122512)
    LeaderBoard(Student("2",2), 124151)

    LeaderBoardManager().getList().forEach { leaderBoard: LeaderBoard ->
        println(leaderBoard.score)
    }

    val game : Game = Game(Bukkit.getPlayer("ohurjon"),Student("김룡현",20403))


}