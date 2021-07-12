package kr.ohurjon.codex.minigame.game.event.jump

import kr.ohurjon.codex.minigame.game.Game
import kr.ohurjon.codex.minigame.game.event.DefaultEvent
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask

class JumpGameStart(val player : Player, val game : Game) : DefaultEvent() {

}