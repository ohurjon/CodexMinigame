package kr.ohurjon.codex.minigame.leaderboard

import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.util.Default
import kr.ohurjon.codex.minigame.util.Util
import kr.ohurjon.codex.minigame.util.WorldUtilType
import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.entity.Player


class LeaderBoard(val player : Player, var score : Long, var type : GameType) : Default(),Comparable<LeaderBoard>,ConfigurationSerializable {

    init {
        val leaderBoard = LeaderBoardManager().getLeaderBoard(player,type)
        if( leaderBoard != null) {
            if(type == GameType.JUMP){
                if (leaderBoard.score > score){
                    LeaderBoardManager().addList(this)
                }
            }else {
                if (leaderBoard.score < score){
                    LeaderBoardManager().addList(this)
                }
            }
        } else {
            LeaderBoardManager().addList(this)
        }
        //LeaderBoardManager().saveLeaderBoard()
        LeaderBoardManager().reloadLeaderBoard()
    }

    override fun equals(other: Any?): Boolean {
        val leaderBoard = other as LeaderBoard
        return (leaderBoard.player == player)
    }

    override fun hashCode(): Int {
        return player.hashCode()
    }

    override fun toString(): String {
        return player.name +" : "+getTimeToStamp(score)
    }

    override fun serialize(): HashMap<String, Any> {
        val result : HashMap<String,Any> = HashMap()
        result["name"] = player.name
        result["score"] = score.toString()
        result["type"] = type.title
        return result
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

    fun deserializeObject(map : LinkedHashMap<String,Any>) : LeaderBoard{
        return LeaderBoard(server.getPlayer(map["name"] as String), map["score"] as Long, GameType.valueOf(map["type"] as String))
    }


}





