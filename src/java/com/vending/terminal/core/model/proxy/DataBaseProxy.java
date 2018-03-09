package com.vending.terminal.core.model.proxy;

import com.sun.org.apache.regexp.internal.RE;
import org.puremvc.java.patterns.proxy.Proxy;

import java.sql.*;

public class DataBaseProxy extends Proxy {
    public static String NAME = "DataBaseProxy";

    private Connection connection;

    public DataBaseProxy() {
        super(NAME);
    }

    @Override
    public void onRegister() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:sqlite:E:\\My WP\\venMachine\\src\\res\\db\\main.db";
        try {
            connection = DriverManager.getConnection(url);
            log("Connection to DataBase has been established.");
        } catch (SQLException e1) {
           err("Connection to database fail! \n" + e1);
        }
        getPrice("a3");
    }

    public String getPrice(String itemPosition){
        String itemId = sqlRequest("positions", "itemId", "position", itemPosition);
        String price = sqlRequest("item","price","itemId", itemId);

       return price;
    }


    public String sqlRequest(String databaseName, String interestedIn, String comparedWith, String value){
        PreparedStatement pstmt  = null;
        try {
            pstmt = connection.prepareStatement("SELECT " + interestedIn +" FROM " + databaseName + " WHERE " + comparedWith + " == ?");
            pstmt.setString(1, value);
            ResultSet rs  = pstmt.executeQuery();
            rs.next();
            return rs.getString(interestedIn);
        } catch (SQLException e1) {
            err("DataBase processing error. Unable to get " + interestedIn + " for database "+ databaseName +" where " + comparedWith + " is equal " + value + "\n" + e1);
        }
        return null;
    }
}
