package kr.ohurjon.codex.minigame

import kr.entree.spigradle.annotations.SpigotPlugin
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.Event
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin

@SpigotPlugin
class CODEX : JavaPlugin() {

    companion object {
        lateinit var instance: CODEX
        lateinit var npc : Entity
        lateinit var gui : Inventory
            private set
    }

    override fun onEnable() {
        instance = this
        this.saveDefaultConfig()
        val world = server.getWorld("world")
        npc = world.spawnEntity(Location(world,28.5,4.0,0.5,90.0f,0.0f),EntityType.VILLAGER)
        gui = server.createInventory(npc as InventoryHolder, InventoryType.CHEST, "GUI")
        (npc as LivingEntity).setAI(false)
        npc.customName = "CODEX NPC"
        npc.isCustomNameVisible = true
        server.pluginManager.registerEvents(EventListener(),this)
    }

    override fun onDisable() {
        npc.remove()
    }

    fun callEvent(event : Event) {
        server.pluginManager.callEvent(event)
    }

}