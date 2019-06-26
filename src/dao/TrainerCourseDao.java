
package dao;

import entities.Trainer;
import entities.TrainerCourse;
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

public class TrainerCourseDao {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getTrainersByCourse = "select * from trainercourse where cid = ?";
    private final String getTrainersByAllCourses = "select * from trainercourse";
    private final String insertTrainerToCourse = "insert ignore into trainercourse (cid,tid) values (?,?)";
    
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(TrainerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Used for task #6 --> Select trainers per course // For a specific course
    */
    public List<Trainer> getTrainersByCourse(int id) {
        List<Trainer> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getTrainersByCourse);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            TrainerDao tdao = new TrainerDao();
            while (rs.next()) { 
                Trainer t = tdao.getTrainerById(rs.getInt(3));
                list.add(t);
            }
            closeConnections(rs, pst);
            }catch (SQLException ex) {
             Logger.getLogger(TrainerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
        }
    
    /*
    Select trainers per course // For all courses at once
    */
    public List<TrainerCourse> getTrainersByAllCourses(){
        List<TrainerCourse> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTrainersByAllCourses);
            TrainerDao tdao = new TrainerDao();
            CourseDao cdao = new CourseDao();
            while (rs.next()) { 
                TrainerCourse tc = new TrainerCourse(rs.getInt(1),cdao.getCourseById(rs.getInt(2)),tdao.getTrainerById(rs.getInt(3)));
                list.add(tc);
            }
            closeConnections(rs, st);
            }catch (SQLException ex) {
             Logger.getLogger(TrainerCourse.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
        }
    
    public boolean insertTrainerToCourse (int cid, int tid) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertTrainerToCourse);
            pst.setInt(1, cid);
            pst.setInt(2, tid);
            
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Trainer matched successfully");
            }
            else{
                System.out.println("Sorry,seems like you have already inserted this combination of trainer-course");
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
