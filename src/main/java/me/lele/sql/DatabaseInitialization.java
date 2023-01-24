package me.lele.sql;

import cc.carm.lib.easysql.EasySQL;
import cc.carm.lib.easysql.api.SQLManager;
import cc.carm.lib.easysql.api.enums.IndexType;
import cc.carm.lib.easysql.api.enums.NumberType;
import cc.carm.lib.easysql.hikari.HikariConfig;
import me.lele.PlayerPointsLook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.ExecutionException;

import static me.lele.util.GetConfig.*;

public class DatabaseInitialization {

    public static SQLManager database1;

    static {

        Plugin plugin = PlayerPointsLook.getProvidingPlugin(PlayerPointsLook.class);

        HikariConfig config1 = new HikariConfig();
        config1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config1.setJdbcUrl("jdbc:mysql://" + address_port1 + "/" + databasename1 + "");
        config1.setUsername(user1);
        config1.setPassword(password1);

        try {
            database1 = EasySQL.createManager(config1);
        } catch (Exception e) {
            plugin.getLogger().warning("内置数据库连接失败,请联系开发人员!");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }

        //判断表是否存在
        Boolean playertransactiondata;
        Boolean playernamedata;
        try {
            playertransactiondata = database1.fetchTableMetadata("playertransactiondata").validateExist().get();
            playernamedata = database1.fetchTableMetadata("playernamedata").validateExist().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (!playertransactiondata) {
            database1.createTable("playertransactiondata")
                    .addAutoIncrementColumn("id", NumberType.BIGINT, true, true)
                    .addColumn("playeruuid", "VARCHAR(36)")
                    .addColumn("transactionhour", "DATETIME")
                    .addColumn("transactionamount", "BIGINT")
                    .addColumn("transactiontype", "BOOLEAN")
                    .addColumn("servername", "VARCHAR(255)")
                    .addColumn("servertype", "VARCHAR(255)")
                    .setIndex(IndexType.INDEX , "playeruuid_index" , "playeruuid")
                    .build().execute(null);
        }

        if (!playernamedata){
            database1.createTable("playernamedata")
                    .addAutoIncrementColumn("id", NumberType.BIGINT, true, true)
                    .addColumn("playername", "VARCHAR(255)")
                    .addColumn("playeruuid", "VARCHAR(36)")
                    .setIndex(IndexType.INDEX , "playeruuid_index" , "playeruuid")
                    .build().execute(null);
        }

    }
}
