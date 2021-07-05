package kr.ohurjon.codex.minigame

import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.InventoryHolder

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

}


