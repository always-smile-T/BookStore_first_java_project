/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaontt.DAO;

import java.io.Serializable;
import thaontt.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sun.security.util.Password;

/**
 *
 * @author Thao
 */
// DAO dung try throw
// Servlet dung try catch
public class RegistrationDAO implements Serializable{
    
    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect database
            con = DBHelper.makeConnection();
            //2.creat SQL String
            if (con != null) {
                String sql = "Select userID "
                        + "From tblUsers "
                        + "Where userID = ? And password = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Execute Query
                rs = stm.executeQuery();
                //5. Xử lý kết quả
                if(rs.next()){
                    return true;
                }
            }//end if connection is connected
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }
    
    
    
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    
    
    public void searchLatname (String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect database
            con = DBHelper.makeConnection();
            //2.creat SQL String
            if (con != null) {
                String sql = "Select userID, password, fullName, roleID "
                        + "From tblUsers "
                        + "Where fullName Like ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Execute Query
                rs = stm.executeQuery();
                //5. Xử lý kết quả
                while (rs.next()){
                    String username = rs.getString("userID");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    boolean role = rs.getBoolean("roleID");
                    //create DTO instance
                    RegistrationDTO dto = new RegistrationDTO(
                    username, password, fullName, role);
                    //add to account list
                    if(this.accountList == null){
                       this.accountList = new ArrayList<>();
                    }// end if        
                    //account is avalible
                    this.accountList.add(dto);
                }//end while 
            }//end if connection is connected
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public boolean deleteAccount (String username)
    throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. connect database
            con = DBHelper.makeConnection();
            //2.creat SQL String
            if (con != null) {
                String sql = "Delete From tblUsers "
                        +"Where userID = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. execute query
                int affectedRows = stm.executeUpdate();
                //4. process data
                if(affectedRows > 0){
                    return true;
                }
            }//end if connection is connected
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }
    public boolean updateAccount(String username, String password, boolean role)
    throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            //connect database
            con = DBHelper.makeConnection();
            //create SQL string
            if(con != null){
                String sql = "Update tblUsers "
                        +"Set password = ?, roleID = ? "
                        +"Where userID = ?";
                //create stament
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //execute query
                int row = stm.executeUpdate();
                // proccess data
                if(row > 0){
                    return true;
                }
            }// end if connection is connected
            
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }
    
    public boolean insertAccount(String username, String fullname, String password, boolean role)
    throws SQLException, NamingException{
        // quy tat la truyen tat ca duoi data base
        // neu khong se tao rat nhieu overloading
        // cai nao k co thi truyen null
        Connection con = null;
        PreparedStatement stm = null;
        try{
            //connect database
            con = DBHelper.makeConnection();
            //create SQL string
            if(con != null){
                String sql = "Insert Into tblUsers(userID, fullName, password, roleID)"
                        +" Values(?, ?, ?, ?)"; // sua data base lai last name
                //create stament
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, fullname);
                stm.setString(3, password);
                stm.setBoolean(4, role);
                //execute query
                int row = stm.executeUpdate();
                // proccess data
                if(row > 0){
                    return true;
                }
            }// end if connection is connected
            
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }
}
