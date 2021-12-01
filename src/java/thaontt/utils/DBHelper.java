/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaontt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Thao
 */
public class DBHelper implements Serializable{
    public static  Connection makeConnection() 
            throws /*ClassNotFoundException*/ NamingException, SQLException{
            
            Context currentContext = new InitialContext();
            Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
            DataSource ds = (DataSource) tomcatContext.lookup("ThuThao");
            Connection con = ds.getConnection();
            
            return con;
//        //1. load Driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. tạo chuỗi kết nối url string
//            String url ="jdbc:sqlserver://localhost:1433;databaseName=PRJManagement";
//        //3. Mở kết nối
//        Connection con = DriverManager.getConnection(url, "sa", "11");
//       return con;
   }
}
