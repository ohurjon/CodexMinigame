package kr.ohurjon.codex.minigame.leaderboard

import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

class LeaderBoardManager {

    companion object {
        var map = HashMap<String, LeaderBoard>()
        private set
    }

    fun getList(): MutableCollection<LeaderBoard> {
        val list = ArrayList(map.values)
        Collections.sort(list, reverseOrder<LeaderBoard>())

        return list
    }

    fun addList(name: String, score : LeaderBoard) {
        map[name] = score
    }
}


