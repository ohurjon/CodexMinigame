package kr.ohurjon.codex.minigame

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Command : CommandExecutor {
    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if( ( sender as Player ).isOp || label.equals("codex")){
            if (args != null) {
                return args.size >=2
            }

        }
        return false
    }
}