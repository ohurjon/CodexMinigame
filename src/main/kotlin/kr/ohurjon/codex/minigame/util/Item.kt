package kr.ohurjon.codex.minigame.util

import kr.ohurjon.codex.minigame.CODEX
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.time.Clock

class Item : Default() {
    companion object{
        lateinit var reset: ItemStack
        lateinit var leave : ItemStack
        private set
    }
    init {
        reset = reset()


    }
    fun reset() : ItemStack {
        val item = ItemStack(Material.WATCH,1)
        val meta = item.itemMeta
        val lore = meta.lore
        meta.displayName = "돌아가기"
        lore.add(0,"해당 아이템을 눌러 게임을 재시작 할 수 있습니다.")

        item.itemMeta = meta

        return item
    }
    fun leave() : ItemStack {
        val item = ItemStack(Material.BARRIER,1)
        val meta = item.itemMeta
        val lore = meta.lore
        meta.displayName = "나가기"
        lore.add(0,"해당 아이템을 눌러 게임을 나갈 수 있습니다.")

        item.itemMeta = meta

        return item
    }
}
