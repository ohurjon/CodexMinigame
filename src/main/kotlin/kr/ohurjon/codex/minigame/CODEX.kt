package kr.ohurjon.codex.minigame

import kr.entree.spigradle.annotations.SpigotPlugin
import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import org.bukkit.event.Event
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin
import kr.ohurjon.codex.minigame.Npc
import kr.ohurjon.codex.minigame.games.GameType
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

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
        npc = Npc(Location(world,0.5,4.0,0.5)).npc
        gui = server.createInventory(npc as InventoryHolder, InventoryType.CHEST, "GUI")

        gui.setItem(GameType.JUMP.index,GameType.JUMP.getItem())
        gui.setItem(GameType.SHULKER.index,GameType.SHULKER.getItem())
        gui.setItem(GameType.TAKGU.index,GameType.TAKGU.getItem())


        server.pluginManager.registerEvents(EventListener(),this)
    }


    override fun onDisable() {
        npc.remove()
    }

    fun callEvent(event : Event) {
        server.pluginManager.callEvent(event)
    }

}