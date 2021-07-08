package kr.ohurjon.codex.minigame

import kr.ohurjon.codex.minigame.games.Game
import kr.ohurjon.codex.minigame.games.GameType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEntityEvent

class EventListener : Listener {
    private val plugin = CODEX.instance

    private val server = plugin.server

    private val npc = CODEX.npc

    private val gui = CODEX.gui


    private val config = plugin.config

    @EventHandler
    fun npc(event: PlayerInteractEntityEvent) {
        if(event.rightClicked == npc){
            event.player.openInventory(gui)
            event.isCancelled = true
        }
    }

    @EventHandler
    fun nodamage(event : EntityDamageByEntityEvent){
        if(event.entity == npc){
            event.isCancelled = true
        }
    }

    @EventHandler
    fun noInven(event: InventoryClickEvent){
        when(event.currentItem){
            GameType.TAKGU.getItem() -> Game(event.whoClicked as Player,)
            GameType.SHULKER.getItem() ->
            GameType.JUMP.getItem() ->
            else -> event.isCancelled = true
        }
    }

}


