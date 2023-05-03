package com.gradyn.endportalentityexitrelocate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalExitEvent;

public class PortalExitListener implements Listener {
    @EventHandler
    public final void onEntityPortalExitEvent(EntityPortalExitEvent event) {
        if (event.getFrom().getWorld().getEnvironment() != World.Environment.THE_END) return;
        if (event.getTo().getWorld().getEnvironment() != World.Environment.NORMAL) return;
        event.setTo(
                new Location(
                        Bukkit.getWorld(EndPortalEntityExitRelocate.config.getString("world")),
                        EndPortalEntityExitRelocate.config.getDouble("location.x"),
                        EndPortalEntityExitRelocate.config.getDouble("location.y"),
                        EndPortalEntityExitRelocate.config.getDouble("location.z")
                        ));
    }
}
