package kr.ohurjon.codex.minigame.game

import org.bukkit.entity.Player

class GameManager {
    companion object {
        var map = HashMap<Player,Game>()
        var shulker = HashSet<Int>()
        var takgu = HashSet<Int>()
        private set
    }

    fun addGame(player:Player,game:Game){
        map.put(player,game)
    }

    fun getGame(player: Player) : Game? {
        return map[player]
    }

    fun removeGame(player: Player) : Game? {
        return map.remove(player)
    }
}