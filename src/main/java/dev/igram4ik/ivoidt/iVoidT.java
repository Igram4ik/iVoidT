package dev.igram4ik.ivoidt;

import dev.igram4ik.ivoidt.events.PlayerFallEvent;
import org.apache.commons.lang3.time.StopWatch;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.igram4ik.ivoidt.Logger.Logger;
public final class iVoidT extends JavaPlugin {

    private final StopWatch stopWatch = new StopWatch();
    @Override
    public void onEnable() {
        Logger.logdp("&6iVoidT загружается...");
        stopWatch.start();

        if (reload())
            Logger.logp("&aiVoidT успешно загрузился. &7[%s]", stopWatch);
        else
            Logger.logp("&fiVoidT загрузился &7[%s]", stopWatch);
    }

    public boolean reload() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        var fileConfig = getDataFolder().toPath().resolve("config.yml").toFile();
        Settings.IMP.reload(fileConfig);

        Bukkit.getPluginManager().registerEvents(new PlayerFallEvent(), this);

        if (stopWatch.isStarted())
            stopWatch.stop();
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
