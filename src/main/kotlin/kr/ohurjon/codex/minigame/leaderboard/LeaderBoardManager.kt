package kr.ohurjon.codex.minigame.leaderboard

import kr.ohurjon.codex.minigame.CODEX
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.util.Default
import kr.ohurjon.codex.minigame.util.Util
import kr.ohurjon.codex.minigame.util.WorldUtilType
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList


class LeaderBoardManager : Default() {

    companion object {
        var shulker = HashMap<Player,LeaderBoard>()
        var takgu = HashMap<Player,LeaderBoard>()
        var jump = HashMap<Player,LeaderBoard>()
            private set
    }

    fun getList(type: GameType): ArrayList<LeaderBoard> {
        val list : ArrayList<LeaderBoard>

        when(type) {
            GameType.JUMP -> list = ArrayList(jump.values)
            GameType.SHULKER -> list = ArrayList(shulker.values)
            GameType.TAKGU -> list = ArrayList(takgu.values)
            else -> list = ArrayList()
        }

        Collections.sort(list, reverseOrder())

        if(type == GameType.JUMP){
            list.reverse()
        }

        val i : Int
        if(list.size <= 7){
            i = list.size
        } else {
            i = 7
        }

        return ArrayList(list.slice(0 until i))
    }

    fun removeArmorStand() {
        for (ar : ArmorStand in CODEX.armorstands){
            ar.remove()
        }
    }

    fun addList(score : LeaderBoard) {
        when(score.type) {
            GameType.JUMP -> jump.put(score.player,score)
            GameType.SHULKER -> shulker.put(score.player,score)
            GameType.TAKGU -> takgu.put(score.player,score)
            else -> {}
        }
    }

    fun getLeaderBoard(player: Player,type: GameType) : LeaderBoard? {
        when(type){
            GameType.TAKGU -> return takgu.get(player)
            GameType.SHULKER -> return shulker.get(player)
            GameType.JUMP -> return jump.get(player)
            else -> return null
        }
    }

//    fun saveLeaderBoard(){
//        plugin.config.set("LeaderBoard.shulker", ArrayList<LeaderBoard>(shulker.values))
//        plugin.config.set("LeaderBoard.jump",ArrayList<LeaderBoard>(jump.values))
//        plugin.config.set("LeaderBoard.takgu",ArrayList<LeaderBoard>(takgu.values))
//        plugin.saveConfig()
//    }

    fun reloadLeaderBoard() {
        removeArmorStand()

        val jump = getList(GameType.JUMP)
        val shulker = getList(GameType.SHULKER)
        val takgu = getList(GameType.TAKGU)

        val location = Location(WorldUtilType.DEFAULT.world, 0.0, 0.0, 0.0)

        var i = 1

        location.y = 7.0

        location.z = 20.5
        location.x = 5.5

        Util().ArmorStand("&l&e" + GameType.JUMP.getName(), location)
        location.y -= 0.5

        jump.forEach { leaderBoard: LeaderBoard ->
            run {
                location.y -= 0.5
                Util().ArmorStand(i.toString() + ". " + leaderBoard.toString(), location)
                i += 1
            }
        }

        location.x = 0.5
        location.y = 7.0

        Util().ArmorStand("&l&e" + GameType.SHULKER.getName(), location)
        location.y -= 0.5
        i = 1
        shulker.forEach { leaderBoard: LeaderBoard ->
            run {
                location.y -= 0.5
                Util().ArmorStand(i.toString() + ". " + leaderBoard.toString(), location)
                i += 1
            }
        }

        location.x = -4.5
        location.y = 7.0

        i = 1
        Util().ArmorStand("&l&e" + GameType.TAKGU.getName(), location)
        location.y -= 0.5
        takgu.forEach { leaderBoard: LeaderBoard ->
            run {
                location.y -= 0.5
                Util().ArmorStand(i.toString() + ". " + leaderBoard.toString(), location)
                i += 1
            }
        }
    }


//    fun loadLeaderboard(){
//        print(plugin.config.getList("LeaderBoard.shulker"))
//    }
}


