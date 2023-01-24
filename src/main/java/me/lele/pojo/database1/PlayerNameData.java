package me.lele.pojo.database1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerNameData {
    //主键ID
    private Long id;
    //玩家ID
    private String playername;
    //玩家UUID
    private UUID playeruuid;
}
