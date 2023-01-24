package me.lele;

import me.lele.listener.PlayerListener;
import me.lele.listener.PlayerPointsListener;
import me.lele.sql.DatabaseInitialization;
import me.lele.util.GetConfig;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PlayerPointsLook extends JavaPlugin {

    public static File ymlconfigFile = null;
    public static YamlConfiguration ymlconfig = null;

    @Override
    public void onEnable() {

        getLogger().info("====================================");
        getLogger().info("[PlayerPointsLook] 本插件由 Eric.乐乐 开发,定制插件请联系QQ:3288732918");
        getLogger().info("====================================");

        //判断是否安装PlayerPoints
        if (setupPlayerPoints()){
            getLogger().severe(String.format("未安装PlayerPoints依赖!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        ymlconfigFile = new File(this.getDataFolder(), "config.yml");
        ymlconfig = YamlConfiguration.loadConfiguration(ymlconfigFile);
        new GetConfig().getconfig();

        //初始化数据库
        new DatabaseInitialization();

        //注册监听器
        getServer().getPluginManager().registerEvents(new PlayerListener() , this);
        getServer().getPluginManager().registerEvents(new PlayerPointsListener() , this);



        getLogger().info("插件加载成功");
    }

    @Override
    public void onDisable() {


        getLogger().info("插件已关闭");
    }




    //判断是否安装PlayerPoints
    private boolean setupPlayerPoints(){
        Plugin plugin = getServer().getPluginManager().getPlugin("PlayerPoints");
        return plugin == null;
    }
}
