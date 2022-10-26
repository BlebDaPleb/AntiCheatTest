package me.blebdapleb.firstac.checks.nofall;

import me.blebdapleb.firstac.checks.Check;
import me.blebdapleb.firstac.checks.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoFallA extends Check implements Listener {

    private static final double groundY = 1 / 64.; // 0.015625

    @EventHandler
    public void onMove(PlayerMoveEvent event){

        Player suspect = event.getPlayer();

        boolean clientGround = event.getPlayer().isOnGround(), serverGround = event.getTo().getY() % groundY < 0.0001;

        if (clientGround != serverGround){

            if (++DataManager.buffer > 1){

                flag(suspect, "NoFall (A)", "Client ground is not equal to server ground.");

            } else if (DataManager.buffer > 0) DataManager.buffer--;

        }

        DataManager.lServerGround = serverGround;

    }

}
