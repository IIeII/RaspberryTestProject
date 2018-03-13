package com.vending.terminal.core.model.proxy;

import org.puremvc.java.patterns.proxy.Proxy;

import java.sql.*;

public class DataBaseProxy extends Proxy {
    public static String NAME = "DataBaseProxy";

    private final String TRANSACTION_DB_NAME = "sales";
    private final String ITEMS_DB_NAME = "item";
    private final String POSITIONS_DB_NAME = "positions";

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
    }

    public String getItemsCount(String itemPosition){
        return sqlRequestSync("positions", "itemsCount", "position", itemPosition);
    }

    public String getPrice(String itemPosition){
        String itemId = sqlRequestSync(POSITIONS_DB_NAME, "itemId", "position", itemPosition);

       return sqlRequestSync(ITEMS_DB_NAME,"price","itemId", itemId);
    }

    public String sqlRequestSync(String databaseName, String interestedIn, String comparedWith, String value){
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

    public void updateItemsCount(int newItemCount, String position) {
        String sql = "UPDATE " + POSITIONS_DB_NAME +" SET count = ? WHERE position = ?";
        PreparedStatement pstmt  = null;
        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, newItemCount);
            pstmt.setString(2, position);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTransactionHistory(String data, Integer itemId, String position, String price, String moneyWasInserted, String bonusMoneyWasUsed){
        String sql = "INSERT INTO " + TRANSACTION_DB_NAME +"(data,itemId,position,price,moneyWasInserted,bonusMoneyWasUsed) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt  = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, data);
            pstmt.setInt(2, itemId);
            pstmt.setString(3, position);
            pstmt.setString(4, price);
            pstmt.setString(5, moneyWasInserted);
            pstmt.setString(6, bonusMoneyWasUsed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
