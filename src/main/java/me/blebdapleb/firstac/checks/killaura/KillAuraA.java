package me.blebdapleb.firstac.checks.killaura;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedEnumEntityUseAction;
import me.blebdapleb.firstac.FirstAC;
import me.blebdapleb.firstac.checks.Check;
import me.blebdapleb.firstac.checks.data.DataManager;
import org.bukkit.entity.Player;

public class KillAuraA extends Check {

    private static final ProtocolManager manager = ProtocolLibrary.getProtocolManager();

    public static PacketAdapter killAuraACheck = new PacketAdapter(FirstAC.getInstance(),
            PacketType.Play.Client.POSITION,
            PacketType.Play.Client.LOOK,
            PacketType.Play.Client.POSITION_LOOK,
            PacketType.Play.Client.FLYING,
            PacketType.Play.Client.USE_ENTITY) {
        @Override
        public void onPacketReceiving(PacketEvent event) {

            PacketContainer packet = event.getPacket();
            Player player = event.getPlayer();

            if (packet.getType().equals(PacketType.Play.Client.USE_ENTITY)) {

                WrappedEnumEntityUseAction wrappedAction = packet.getEnumEntityUseActions().read(0);
                EnumWrappers.EntityUseAction clickType = wrappedAction.getAction();
                if (clickType == EnumWrappers.EntityUseAction.ATTACK){

                    if (System.currentTimeMillis() - DataManager.lastFlying < 5) {

                        if (DataManager.killAuraAVerbose++ > 10) {

                            flag(player, "KillAura (A)", "Sent flying packet too late");

                        }

                    }

                }

            } else {

                DataManager.lastFlying = System.currentTimeMillis();

            }

        }
    };

}
