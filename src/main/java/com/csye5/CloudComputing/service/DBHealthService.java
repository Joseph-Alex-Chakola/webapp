package com.csye5.CloudComputing.service;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBHealthService{
    DataSource dataSource;
    public DBHealthService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void checkHealth() throws SQLException {
        try{
            Connection connection = dataSource.getConnection();
            connection.isValid(1000);
            connection.close();
        } catch (SQLException e) {
            throw new SQLException("Database Error: " + e.getMessage());
        }
    }
}
