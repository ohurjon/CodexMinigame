package kr.ohurjon.codex.minigame.util

import org.bukkit.World
import org.bukkit.WorldType

enum class WorldUtilType(val world : World){
    BUILD(WorldUtil("world-build", WorldType.FLAT).world),
    TAKGU(WorldUtil("world-takgu", WorldType.FLAT).world),
    SHULKER(WorldUtil("world-shulker", WorldType.FLAT).world),
    DEFAULT(WorldUtil("world", WorldType.NORMAL).world),
    JUMP(WorldUtil("world-jump",WorldType.NORMAL).world)
}