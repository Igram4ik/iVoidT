package dev.igram4ik.ivoidt.events;


import dev.igram4ik.ivoidt.Settings;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.annotation.Nullable;

import java.util.Objects;

import static dev.igram4ik.ivoidt.Logger.Logger;
public class PlayerFallEvent implements Listener {
    public PlayerFallEvent() {

    }

    @EventHandler
    public void onFall(PlayerMoveEvent PME) {
        var player = PME.getPlayer();
        var loc = PME.getTo();
        var min_y = Settings.IMP.Y_VOID;
        var worlds = Settings.IMP.WORLDS;

        if (Settings.IMP.ENABLED && !Settings.IMP.BLACKLIST.contains(player.getName())) {
            if (worlds.contains(loc.getWorld().getName()))
                if (loc.getBlockY() <= min_y) {
                    Logger.logdp("&fОбнаружен игрок %s, выпольняю действие &7[%s]", player.getName(), Settings.IMP.TYPE);
                    switch (Settings.IMP.TYPE) {
                        case COMMAND -> {
                            for (String command : Settings.IMP.COMMANDS) {
                                if (Settings.IMP.PSENDER) {
                                    Bukkit.dispatchCommand(player, command);
                                } else {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                                }
                            }
                        }
                        case TELEPORT -> {
                            var tp = Settings.IMP.TELEPORT;
                            var world = Bukkit.getWorld(tp.WORLD);
                            var x = tp.X;
                            var y = tp.Y;
                            var z = tp.Z;
                            var yaw = tp.YAW;
                            var pitch = tp.PITCH;
                            var tp_location = new Location(world, z, y, x, yaw, pitch);

                            if (tp.SAME_WORLD)
                                teleport(player, tp_location, true);
                            else
                                teleport(player, tp_location, false);


                            Logger.logdp("&fИгрок был телепортирован. &7[%s]", player.getName());
                        }
                    }
                }

        }
    }

    public void teleport(Player player, Location loc, boolean same) {
        int maxEntities = Objects.requireNonNull(loc.getWorld().getGameRuleValue(GameRule.MAX_ENTITY_CRAMMING));

        if (same) {

            if (Settings.IMP.TELEPORT.NEAR && loc.getNearbyLivingEntities(1).size() >= maxEntities) {
                loc.add(1, 0 ,0);
                loc.setWorld(player.getWorld());
                player.teleport(loc);
            } else {
                loc.setWorld(player.getWorld());
                player.teleport(loc);
            }
        } else {

            if (Settings.IMP.TELEPORT.NEAR && loc.getNearbyLivingEntities(1).size() >= maxEntities) {
                loc.add(1, 0, 0);

                player.teleport(loc);
            } else {

                player.teleport(loc);
            }
        }
    }
}
