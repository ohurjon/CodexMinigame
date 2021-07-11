package kr.ohurjon.codex.minigame.game

import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.entity.Player


class Game( val player: Player , val game: GameType) : Default() {

    init {
        when(game){
            GameType.JUMP -> jump()
        }
    }

    fun jump(){
        val time = 0
        server.scheduler.runTaskTimerAsynchronously(plugin, {

        },1L,20L)
    }






}