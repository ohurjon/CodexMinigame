package kr.ohurjon.codex.minigame.game.event

import kr.ohurjon.codex.minigame.game.Game
import org.bukkit.entity.Player

class GameStart(val player : Player, val game : Game) : DefaultEvent() {

}