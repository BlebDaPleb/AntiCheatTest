package me.blebdapleb.firstac.checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Check {

    public static void flag(Player suspect, String checkType, String description){

        String suspectName = suspect.getName();
        for (Player staff : Bukkit.getOnlinePlayers()){

            if (staff.hasPermission("anticheat.alerts.view")){

                staff.sendMessage(ChatColor.YELLOW + suspectName + ChatColor.RED
                        +  " has failed " + ChatColor.YELLOW + checkType + ChatColor.RED + " ! " + ChatColor.RED + description + " ! ");

            }

        }

    }

}
