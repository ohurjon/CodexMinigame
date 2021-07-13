package kr.ohurjon.codex.minigame

import kr.ohurjon.codex.minigame.game.Game
import kr.ohurjon.codex.minigame.game.GameType
import kr.ohurjon.codex.minigame.game.event.GameStart
import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerJoinEvent

class EventListener : Listener, Default() {

    @EventHandler
    fun Npc(event: PlayerInteractEntityEvent) {
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
    fun nohung(event: FoodLevelChangeEvent){
        event.isCancelled = true
    }
    @EventHandler
    fun GUIclick(event: InventoryClickEvent){
        val player = event.whoClicked as Player
        when (event.currentItem) {
            GameType.JUMP.getGuiItem() -> {
                plugin.callEvent(GameStart(player,Game(player,GameType.JUMP)))
            }
            GameType.SHULKER.getGuiItem() -> {
                plugin.callEvent(GameStart(player,Game(player,GameType.SHULKER)))
            }
            GameType.TAKGU.getGuiItem() -> {
                plugin.callEvent(GameStart(player,Game(player,GameType.TAKGU)))
            }
            else -> event.isCancelled = false
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent){
        event.player.teleport(spawn)
        event.joinMessage = "CODEX 서버에 "+event.player.name+"님이 접속 하셨습니다!"
        event.player.sendTitle("CODEX 서버에 오신걸 환영합니다.","스폰 앞 주민에 모든 게임이 담겨있습니다!",60,100,10)
    }

}


