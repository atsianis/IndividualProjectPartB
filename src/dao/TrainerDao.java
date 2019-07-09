
package dao;

import entities.Trainer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainerDao {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getTrainers = "select * from trainer;";
    private final String getTrainerById = "select * from trainer where tid = ?";
    private final String insertTrainer = "insert ignore into trainer(tfname,tlname) values (?,?)";
    
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (Exception ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Trainer> getTrainers() {
        List<Trainer> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTrainers);
            //Step 4:
            while (rs.next()) {
                Trainer t = new Trainer (rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(t);
            }
            //Step 5:close connections
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Trainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Trainer getTrainerById(int id) {
        Trainer t = null;

        try {
            PreparedStatement pst = getConnection().prepareStatement(getTrainerById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            t = new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(Trainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    public boolean insertTrainer (Trainer t) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertTrainer);
            pst.setString(1, t.getTfname());
            pst.setString(2, t.getTlname());
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Trainer inserted successfully");
            }else{
                System.out.println("Sorry, seems like you have already inserted "+t.getTfname()+" "+t.getTlname());
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return inserted;
    }
    
}
