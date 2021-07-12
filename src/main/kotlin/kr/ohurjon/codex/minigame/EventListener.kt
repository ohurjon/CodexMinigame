package kr.ohurjon.codex.minigame

import kr.ohurjon.codex.minigame.game.Game
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.inventory.InventoryClickEvent
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
    fun GUIclick(event: InventoryClickEvent){
        when (event.currentItem) {
            GameType.JUMP.getGuiItem() -> Game(event.whoClicked as Player,GameType.JUMP)
            else -> event.isCancelled = false
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent){
        event.player.teleport(Location(server.getWorld("world"),0.5,4.0,3.5,-180f,0f))
        event.joinMessage = "CODEX 서버에 "+event.player.name+"님이 접속 하셨습니다!"
        event.player.sendTitle("CODEX 서버에 오신걸 환영합니다.","스폰 앞 주민에 모든 게임이 담겨있습니다!",60,100,10)
    }

}


