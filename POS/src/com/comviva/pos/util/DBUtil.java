package com.comviva.pos.util;
import java.sql.*;
public class DBUtil {
	public static Connection con=getDBConnection("com.mysql.cj.jdbc.Driver");
public static Connection getDBConnection(String driverType)
{
	try
	{
	Class.forName(driverType);
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","admin12");
	System.out.println("connection established");
	}
	catch(ClassNotFoundException cnf)
	{
		
	}
	catch(SQLException sql)
	{
		
	}
	return con;
}
}
