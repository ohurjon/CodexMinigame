package kr.ohurjon.codex.minigame

import kr.entree.spigradle.annotations.SpigotPlugin
import org.bukkit.event.Event
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.game.listener.GameEventListener
import org.bukkit.*
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity

@SpigotPlugin
class CODEX : JavaPlugin() {

    companion object {
        lateinit var instance: CODEX
        lateinit var gui : Inventory
        lateinit var npc : Entity
        lateinit var spawn : Location
            private set
    }

    override fun onEnable() {
        instance = this

        this.saveDefaultConfig()

        val world = server.getWorld("world")

        spawn = Location(world,0.5,4.0,3.5,180f,0f)

        npc = world.spawnEntity(Location(world,0.5,4.0,0.5),EntityType.VILLAGER)
        (npc as LivingEntity).setAI(false)
        npc.customName = "CODEX NPC"

        gui = server.createInventory(npc as InventoryHolder, InventoryType.CHEST, "GUI")

        gui.setItem(GameType.JUMP.index,GameType.JUMP.getGuiItem())
        gui.setItem(GameType.SHULKER.index,GameType.SHULKER.getGuiItem())
        gui.setItem(GameType.TAKGU.index,GameType.TAKGU.getGuiItem())



        val jump = server.getWorld("world-jump")

        if( jump == null) {
            server.createWorld(WorldCreator("world-jump"))
        }

        val play = server.getWorld("world-play")

        if( play == null) {
            server.createWorld(WorldCreator("world-play").type(WorldType.FLAT))
        }

        server.createWorld(WorldCreator("world-takgu").environment(World.Environment.NETHER))

        server.pluginManager.registerEvents(EventListener(),this)
        server.pluginManager.registerEvents(GameEventListener(),this)

        getCommand("mv").executor = Command()
        getCommand("spawn").executor = Command()


    }


    override fun onDisable() {
        npc.remove()
    }

    fun callEvent(event: Event) {
        server.pluginManager.callEvent(event)
    }

}