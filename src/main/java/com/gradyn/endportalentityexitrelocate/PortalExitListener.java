package com.gradyn.endportalentityexitrelocate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalExitEvent;

import java.util.logging.Logger;

public class PortalExitListener implements Listener {
    @EventHandler
    public final void onEntityPortalExitEvent(EntityPortalExitEvent event) {
        if (event.getFrom().getWorld().getEnvironment() != World.Environment.THE_END) return;
        if (event.getTo().getWorld().getEnvironment() != World.Environment.NORMAL) return;
        double y = 2032;
        if (EndPortalEntityExitRelocate.config.getString("location.y").toLowerCase().equals("auto")) {
            while (y > -2032) {
                Block block = new Location(
                        Bukkit.getWorld(EndPortalEntityExitRelocate.config.getString("world")),
                        EndPortalEntityExitRelocate.config.getDouble("location.x"),
                        y,
                        EndPortalEntityExitRelocate.config.getDouble("location.z")
                ).getBlock();
                if (block.getType() != Material.AIR && block.getType() != Material.VOID_AIR && block.getType() != Material.CAVE_AIR) {
                    break;
                }
                y--;
            }
            if (y == -2035) {
                Logger.getAnonymousLogger().warning("No safe block at end entity teleport location found. Cancelling teleportation");
                event.setCancelled(true);
                return;
            }
        } else {
            y = EndPortalEntityExitRelocate.config.getDouble("location.y");
        }
        event.setTo(
                new Location(
                        Bukkit.getWorld(EndPortalEntityExitRelocate.config.getString("world")),
                        EndPortalEntityExitRelocate.config.getDouble("location.x"),
                        y,
                        EndPortalEntityExitRelocate.config.getDouble("location.z")
                        ));
    }
}
