package kr.ohurjon.codex.minigame.games

import kr.ohurjon.codex.minigame.CODEX
import kr.ohurjon.codex.minigame.leaderboard.Student
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.entity.Player


class Game( player: Player, student : Student ) : Default() {
    val type : GameType = GameType.USIGN


    init {
        server.scheduler.runTaskTimerAsynchronously(plugin, Runnable {
                                                                     print("test")
        },20L,config.getLong(type.title+ ".time")*20)
    }






}