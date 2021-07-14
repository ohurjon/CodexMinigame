package kr.ohurjon.codex.minigame

import com.google.gson.Gson
import kr.entree.spigradle.annotations.SpigotPlugin
import org.bukkit.event.Event
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.game.listener.GameEventListener
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoard
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoardManager
import kr.ohurjon.codex.minigame.util.Util
import kr.ohurjon.codex.minigame.util.WorldUtil
import org.bukkit.*
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.entity.ArmorStand
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
        lateinit var armorstands : HashSet<ArmorStand>
            private set
    }

    override fun onEnable() {
        instance = this

        armorstands = HashSet()

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


        WorldUtil("world-jump",WorldType.NORMAL)
        WorldUtil("world-shulker",WorldType.FLAT)
        WorldUtil("world-takgu",WorldType.FLAT)
        WorldUtil("world-build",WorldType.FLAT)

        //ConfigurationSerialization.registerClass(this.javaClass)

        //LeaderBoardManager().loadLeaderboard()

        LeaderBoardManager().reloadLeaderBoard()

        server.pluginManager.registerEvents(EventListener(),this)
        server.pluginManager.registerEvents(GameEventListener(),this)

        getCommand("mv").executor = Command()
        getCommand("spawn").executor = Command()
        getCommand("s").executor = Command()

    }

    fun spawnEntity(location: Location,entityType: EntityType): Entity {
        return location.world.spawnEntity(location,entityType)
    }

    override fun onDisable() {
        npc.remove()
        LeaderBoardManager().removeArmorStand()
    }

    fun callEvent(event: Event) {
        server.pluginManager.callEvent(event)
    }

}