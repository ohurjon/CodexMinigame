package kr.ohurjon.codex.minigame.util

import kr.ohurjon.codex.minigame.CODEX
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.time.Clock

class Item : Default() {
    fun reset() : ItemStack {
        val item = ItemStack(Material.WATCH,1)
        val meta = item.itemMeta
        meta.displayName = "다시하기"
        item.itemMeta = meta

        return item
    }
    fun leave() : ItemStack {
        val item = ItemStack(Material.BARRIER,1)
        val meta = item.itemMeta
        meta.displayName = "나가기"
        item.itemMeta = meta

        return item
    }
}
