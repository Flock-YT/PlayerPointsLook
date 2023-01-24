package me.lele.listener;

import me.lele.pojo.database1.PlayerTransactionData;
import me.lele.sql.database1.InsertPlayerTransactionData;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.Timestamp;
import java.util.UUID;

import static me.lele.tools.GetConfig.servername;
import static me.lele.tools.GetConfig.servertype;

public class PlayerPointsListener implements Listener {

    private static InsertPlayerTransactionData insertPlayerTransactionData = new InsertPlayerTransactionData();

    @EventHandler
    public void playerPointsChangeEvent(PlayerPointsChangeEvent playerPointsChangeEvent){

        //获取交易数据
        UUID playeruuid = playerPointsChangeEvent.getPlayerId();
        Timestamp transactionhour = new Timestamp(System.currentTimeMillis());
        int transactionamount = playerPointsChangeEvent.getChange();
        Boolean transactiontype = false;

        //判断交易类型
        if (transactionamount > 0){
            transactiontype = true;
        }

        //构建对象/插入数据库
        PlayerTransactionData playerTransactionData = new PlayerTransactionData(null, playeruuid , transactionhour , (long) transactionamount, transactiontype , servername , servertype);

        insertPlayerTransactionData.insertOneByPlayerTransactionData(playerTransactionData);
    }
}
