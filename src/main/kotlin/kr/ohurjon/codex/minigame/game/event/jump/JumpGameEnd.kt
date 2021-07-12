package kr.ohurjon.codex.minigame.game.event.jump

import kr.ohurjon.codex.minigame.game.Game
import kr.ohurjon.codex.minigame.game.event.DefaultEvent
import org.bukkit.entity.Player

class JumpGameEnd(val player : Player, val game : Game, val time: Long) : DefaultEvent() {
}