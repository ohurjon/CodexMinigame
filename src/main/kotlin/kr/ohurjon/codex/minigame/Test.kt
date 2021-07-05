package kr.ohurjon.codex.minigame

fun main(){
    LeaderBoard(Student("1",1),123)
    LeaderBoard(Student("3",3),256122512)
    LeaderBoard(Student("2",2), 124151)

    LeaderBoardManager().getList().forEach { leaderBoard: LeaderBoard ->
        println(leaderBoard.score)
    }


}