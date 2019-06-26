
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String validuser = "select * from login where username = ?";
    private final String logIn = "select password from login where username = ?";
    
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public boolean isValidUser(String username) {
        boolean valid = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(validuser);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                valid = true;
            }
            closeConnections(rs,pst);
        } catch (SQLException ex) {
            valid = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return valid;
    }
     
     public boolean login (String password,String username) {
        boolean logged = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(logIn);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                if (rs.getString(1).equals(password)){
                    logged = true;
                }
            }
            closeConnections(rs,pst);
        } catch (SQLException ex) {
            logged = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return logged;
     }

}

