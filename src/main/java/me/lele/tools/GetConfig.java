package me.lele.tools;

import static me.lele.PlayerPointsLook.ymlconfig;

public class GetConfig {

    public static String servername;
    public static String servertype;
    public static String address_port1;
    public static String user1;
    public static String password1;
    public static String databasename1;

    public void getconfig() {

        servername = ymlconfig.getString("Servername");
        servertype = ymlconfig.getString("Servertype");
        address_port1 = ymlconfig.getString("address-port");
        user1 = ymlconfig.getString("user");
        password1 = ymlconfig.getString("password");
        databasename1 = ymlconfig.getString("database");

    }

}
