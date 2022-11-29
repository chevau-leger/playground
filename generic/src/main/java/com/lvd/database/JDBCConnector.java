package com.lvd.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnector {

    private Connection connection;

    public void initialize(String driver, String url, String username, String password) throws Exception {

        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet executeQuery(String sql) throws Exception {

        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static void main(String[] args) throws Exception{

        JDBCConnector connector = new JDBCConnector();
        connector.initialize("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/ulmp", "root", "0909");
        ResultSet result = connector.executeQuery("select DISTINCT original_interface_code from tb_capes_cfg_mg_kpi");
        while (result.next()){
            System.out.println(result.getString("original_interface_code"));
        }
    }
}
