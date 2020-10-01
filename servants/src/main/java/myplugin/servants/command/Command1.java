package myplugin.servants.command;

import myplugin.servants.Servants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command1 implements CommandExecutor {
    private final Servants servants;

    public Command1(Servants servants) {
        this.servants=servants;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("creatServant")) {
            sender.sendMessage("creatServant!");
            return true;
        }
        return false;
    }
}
