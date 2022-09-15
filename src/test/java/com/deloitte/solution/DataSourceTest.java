package com.deloitte.solution;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;


public class DataSourceTest {
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.204.17.52:48349;encrypt=true;trustServerCertificate=true","ifuser","WElcome12!@")){
			System.out.println(conn);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
}
