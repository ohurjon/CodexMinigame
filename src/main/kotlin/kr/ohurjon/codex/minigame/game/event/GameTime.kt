package kr.ohurjon.codex.minigame.game.event

import kr.ohurjon.codex.minigame.game.Game
import org.bukkit.entity.Player

class GameTime(val player: Player, val game: Game, val time:Long) : DefaultEvent() {
}