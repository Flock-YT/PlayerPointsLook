package me.lele.sql.database1;

import me.lele.pojo.database1.PlayerNameData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static me.lele.sql.DatabaseInitialization.database1;

public class SelectPlayerNameData {

    //通过UUID查询查询一条信息
    public PlayerNameData selectOneByPlayerUUID(UUID playerUUID){

        try {
            return database1.createQuery()
                    .inTable("playernamedata")
                    .addCondition("playeruuid" , playerUUID)
                    .setLimit(1)
                    .build().executeFunction(sqlQuery -> {

                        if (!sqlQuery.getResultSet().next()) return null;

                        else {

                            ResultSet resultSet = sqlQuery.getResultSet();

                            PlayerNameData playerNameData = new PlayerNameData();
                            playerNameData.setId(resultSet.getLong("id"));
                            playerNameData.setPlayername(resultSet.getString("playername"));
                            playerNameData.setPlayeruuid(UUID.fromString(resultSet.getString("playeruuid")));

                            return playerNameData;

                        }

                    });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
