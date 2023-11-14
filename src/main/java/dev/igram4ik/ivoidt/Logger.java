package dev.igram4ik.ivoidt;

import org.bukkit.Bukkit;

public class Logger {
    public static final Logger Logger = new Logger();

    public void log(String string, Object... objects) {
        Bukkit.getLogger().info(String.format(string, objects).replace("&", "§"));
    }
    public void logp(String string, Object... objects) {
        log("&7<&6/&7> &f" + string, objects);
    }
    public void logdp(String string, Object... objects) {
        if (Settings.IMP.DEBUG)
            logp(string, objects);
    }
    public void logd(String string, Object... objects) {
        if (Settings.IMP.DEBUG)
            log(string, objects);
    }
}
