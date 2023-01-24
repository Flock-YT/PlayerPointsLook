package me.lele.pojo.database1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerTransactionData {
    //主键ID
    private Long id;
    //玩家UUID
    private UUID playeruuid;
    //玩家交易时间
    private Timestamp transactionhour;
    //玩家交易额度
    private Long transactionamount;
    //玩家交易类型 (true = 获得点券 false = 失去点券)
    private Boolean transactiontype;
    //玩家交易服务器
    private String servername;
    //玩家交易服务器类型
    private String servertype;
}
