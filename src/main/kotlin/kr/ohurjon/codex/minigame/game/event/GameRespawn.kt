package kr.ohurjon.codex.minigame.game.event

import kr.ohurjon.codex.minigame.game.Game
import kr.ohurjon.codex.minigame.game.event.DefaultEvent
import org.bukkit.entity.Player

class GameRespawn(val player : Player, val game: Game) : DefaultEvent() {

}