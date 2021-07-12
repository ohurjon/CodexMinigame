package kr.ohurjon.codex.minigame.game.event

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

open class DefaultEvent : Event() {

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }
}