package kr.ohurjon.codex.minigame

import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Command : CommandExecutor,Default() {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if (label != null) {
            when(label.toLowerCase()) {
                "mv" -> move(sender,args)
                "spawn" -> spawn(sender, args)
            }
            return true
        }
        return false
    }

    fun spawn(sender: CommandSender?,args: Array<out String>?){
        if( sender is Player) (sender).teleport(server.getWorld("world").spawnLocation)
    }

    fun move(sender: CommandSender?,args: Array<out String>?){
        when((sender as Player).world.name){
            "world" -> {
                val loc = sender.location
                loc.world = server.getWorld("world-takgu")
                sender.teleport(loc)
            }
            "world-takgu" -> {
                val loc = sender.location
                loc.world = server.getWorld("world")
                sender.teleport(loc)
            }
        }
    }
}