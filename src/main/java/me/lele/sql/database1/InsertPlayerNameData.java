package me.lele.sql.database1;

import me.lele.pojo.database1.PlayerNameData;

import static me.lele.sql.DatabaseInitialization.database1;

public class InsertPlayerNameData {

    //通过对象插入一条数据
    public int insertOneByPlayerNameData(PlayerNameData playerNameData){

        return database1.createInsert("playernamedata")
                .setColumnNames("id" , "playername" , "playeruuid")
                .setParams(playerNameData.getId() , playerNameData.getPlayername() , playerNameData.getPlayeruuid())
                .execute(null);

    }
}
