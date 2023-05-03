package com.gradyn.endportalentityexitrelocate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class EndPortalEntityExitRelocate extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onLoad() {
        this.saveDefaultConfig();
        config = this.getConfig();
        Logger.getAnonymousLogger().info("EndPortalEntityExitReloacate Loaded");
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PortalExitListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
