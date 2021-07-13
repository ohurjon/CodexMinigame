package kr.ohurjon.codex.minigame.util

import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.WorldType

class WorldUtil(val name: String, val type: WorldType) : Default() {

    var world : World

    init {
        server.createWorld(WorldCreator(name).type(type))
        world  = server.getWorld(name)
    }

}

