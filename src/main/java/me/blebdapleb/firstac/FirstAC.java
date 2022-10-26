package me.blebdapleb.firstac;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.blebdapleb.firstac.checks.killaura.KillAuraA;
import me.blebdapleb.firstac.checks.nofall.NoFallA;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class FirstAC extends JavaPlugin {

    private static FirstAC instance;
    public static FirstAC getInstance() {return instance;}

    @Override
    public void onEnable() {

        instance  = this;

        // Managers
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        // Packet listeners
        protocolManager.addPacketListener(KillAuraA.killAuraACheck);

        // Bukkit events
        getServer().getPluginManager().registerEvents(new NoFallA(), this);

        System.out.println(ChatColor.GREEN + "FirstAC has been enabled");

    }

    @Override
    public void onDisable() {

        System.out.println(ChatColor.GREEN + "FirstAC has been disabled");

    }
}
