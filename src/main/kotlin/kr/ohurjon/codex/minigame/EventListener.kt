package kr.ohurjon.codex.minigame

import kr.ohurjon.codex.minigame.games.Game
import kr.ohurjon.codex.minigame.games.GameType
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerJoinEvent

class EventListener : Listener, Default() {

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

            when (event.currentItem) {
//            GameType.TAKGU.getItem() -> Game(event.whoClicked as Player,)
//            GameType.SHULKER.getItem() ->
                GameType.JUMP.getItem() -> event.whoClicked.teleport(Location(server.getWorld("world-jump"),0.5,88.0,4.5,-180f,0f))
                else -> event.isCancelled = false
            }

    }

}


