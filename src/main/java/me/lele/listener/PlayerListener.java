package me.lele.listener;

import me.lele.pojo.database1.PlayerNameData;
import me.lele.sql.database1.InsertPlayerNameData;
import me.lele.sql.database1.SelectPlayerNameData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerListener implements Listener {

    private static InsertPlayerNameData insertPlayerNameData = new InsertPlayerNameData();
    private static SelectPlayerNameData selectPlayerNameData = new SelectPlayerNameData();

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent playerJoinEvent){

        //获取玩家信息
        String playername = playerJoinEvent.getPlayer().getName();
        UUID playeruuid = playerJoinEvent.getPlayer().getUniqueId();

        //查看玩家是否在数据库内
        PlayerNameData tempplayerNameData = selectPlayerNameData.selectOneByPlayerUUID(playeruuid);
        if (tempplayerNameData != null){
            //玩家已经有数据了,不再插入
            return;
        }

        //构建对象插入数据
        PlayerNameData playerNameData = new PlayerNameData(null , playername , playeruuid);
        insertPlayerNameData.insertOneByPlayerNameData(playerNameData);

    }
}
