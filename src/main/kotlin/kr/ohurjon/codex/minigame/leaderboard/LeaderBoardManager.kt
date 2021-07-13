package kr.ohurjon.codex.minigame.leaderboard

import kr.ohurjon.codex.minigame.CODEX
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class LeaderBoardManager : Default() {

    companion object {
        var list = ArrayList<LeaderBoard>()
        private set
    }

    fun getList(type: GameType): ArrayList<LeaderBoard> {
        val typelist = ArrayList(list.filter{ leaderBoard -> leaderBoard.type == type })


        Collections.sort(typelist, reverseOrder<LeaderBoard>())

        if(type == GameType.JUMP){
            typelist.reverse()
        }

        var i : Int= 0
        if(typelist.size <= 7){
            i = typelist.size
        } else {
            i = 7
        }

        return ArrayList(typelist.slice(0 until i))
    }

    fun removeArmorStand() {
        for (ar : ArmorStand in CODEX.armorstands){
            ar.remove()
        }
    }

    fun addList(score : LeaderBoard) {
        list.add(score)
    }
}


