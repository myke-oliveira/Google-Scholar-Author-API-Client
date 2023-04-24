package com.myke.googlescholarauthorapiclient;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    private String database;
    private String user;
    private String password;
    private String host;
    private String port;


    public DBUtil() {

        this.database = System.getenv("DB_NAME");
        this.user = System.getenv("DB_USER");
        this.password = System.getenv("DB_PASSWORD");
        this.host = System.getenv("DB_HOST");
        this.port = System.getenv("DB_PORT");

        String url = String.format(
                "jdbc:mysql://%s:%s/%s",
                this.host,
                this.port,
                this.database
        );

        try {
            this.connection = (Connection) DriverManager.getConnection(url, this.user, this.password);
            this.statement = (Statement) connection.createStatement();
        } catch (SQLException e) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    
    public ResultSet executeQuery(String query, List<String> binds) throws NotFound {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            for (int i = 0; i < binds.size(); i++) {
                String bind = binds.get(i);
                preparedStatement.setString(i+1, bind);
            }
            
            this.resultSet = preparedStatement.executeQuery();
            return this.resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotFound();
        }
    }
    
    public void executeManipulation(String sql, List<String> binds) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < binds.size(); i++) {
                String bind = binds.get(i);
                preparedStatement.setString(i+1, bind);
            }
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
