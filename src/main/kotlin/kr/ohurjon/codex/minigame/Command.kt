package kr.ohurjon.codex.minigame

import kr.ohurjon.codex.minigame.util.Default
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class Command : CommandExecutor,Default() {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        when(label.toLowerCase()) {
            "mv" -> move(sender,args)
            "spawn" -> spawn(sender, args)
            "s" -> shulker(sender,args)
            else -> return false
        }
        return true
    }

    fun spawn(sender: CommandSender,args: Array<out String>){
        if( sender is Player) (sender).teleport(spawn)
    }

    fun shulker(sender: CommandSender,args: Array<out String>){
        val loc =  (sender as Player).location
        val entity = loc.world.spawnEntity(loc,EntityType.SHULKER)
        (entity as LivingEntity).setAI(false)
    }

    fun move(sender: CommandSender,args: Array<out String>){
        if(args.size >= 1 && sender is Player){
            val world = server.getWorld(args[0])
            if(world != null){
                val loc = sender.location
                loc.world = world
                sender.teleport(loc)
            }
        }
    }
}