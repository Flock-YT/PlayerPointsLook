package me.lele.sql.database1;

import me.lele.pojo.database1.PlayerTransactionData;

import static me.lele.sql.DatabaseInitialization.database1;

public class InsertPlayerTransactionData {

    //通过对象插入一条数据
    public int insertOneByPlayerTransactionData(PlayerTransactionData playerTransactionData){

        return database1.createInsert("playertransactiondata")
                .setColumnNames("id" , "playeruuid" , "transactionhour" , "transactionamount" , "transactiontype" , "servername" , "servertype")
                .setParams(playerTransactionData.getId() , playerTransactionData.getPlayeruuid() , playerTransactionData.getTransactionhour() , playerTransactionData.getTransactionamount() , playerTransactionData.getTransactiontype() , playerTransactionData.getServername() , playerTransactionData.getServertype())
                .execute(null);

    }
}
