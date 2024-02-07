package com.csye5.CloudComputing;

import com.csye5.CloudComputing.service.DBHealthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

@SpringBootTest
class CloudComputingApplicationTests {
    @Mock
    private DataSource dataSource;
	@Mock
	private Connection connection;


    @Test
    void contextLoads() {
    }


    @Test
    void DBHealthServiceTestSuccess() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
		when(connection.isValid(1000)).thenReturn(true);
		DBHealthService dbHealthService = new DBHealthService(dataSource);
		Assertions.assertDoesNotThrow(dbHealthService::checkHealth);
    }
	@Test
	void DBHealthServiceTestFailure() throws SQLException {
		when(dataSource.getConnection()).thenReturn(connection);
		when(connection.isValid(1000)).thenThrow(new SQLException("Connection failed"));
		DBHealthService dbHealthService = new DBHealthService(dataSource);
		Assertions.assertThrows(SQLException.class, dbHealthService::checkHealth);
	}

}
