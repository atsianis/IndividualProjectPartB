
package dao;

import entities.Assignment;
import entities.Trainer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignmentDao {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getAllAssignments = "select * from assignment";
    private final String getAssignmentsByCourse = "select * from assignment where cid = ?";
    private final String getAssignmentById = "select * from assignment where aid = ?";
    private final String insertAssignment = "insert ignore into assignment(cid,atitle,adescr,adate,aomark,atmark) values (?,?,?,?,?,?)";
    
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    Task #7 // Assignment per course // For All Courses at once
    */
    public List<Assignment> getAllAssignments() {
        List<Assignment> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllAssignments);
           
            CourseDao cdao = new CourseDao();
            while (rs.next()) {
                
                Assignment a = new Assignment (rs.getInt(1), cdao.getCourseById(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getInt(7));
                list.add(a);
            }
           
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Trainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    /*
    Task #7 also // Assignments for a specific Course
    */
    
    public List<Assignment> getAssignmentsByCourse(int id) {
        List<Assignment> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentsByCourse);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourseDao cdao = new CourseDao();
                Assignment a = new Assignment (rs.getInt(1),cdao.getCourseById(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getInt(7));
                list.add(a);
            }
            closeConnections(rs, pst);
            }catch (SQLException ex) {
             Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
        }
    
    public Assignment getAssignmentById(int id) {
        Assignment a = null;

        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            CourseDao cdao = new CourseDao();
            a = new Assignment (rs.getInt(1), cdao.getCourseById(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getInt(7));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(Assignment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    public boolean insertAssignment (Assignment a) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAssignment);
            pst.setInt(1, a.getCid().getCid());
            pst.setString(2, a.getAtitle());
            pst.setString(3,a.getAdescr());
            pst.setDate(4,Date.valueOf(a.getAdate()));
            pst.setInt(5, a.getAomark());
            pst.setInt(6, a.getAtmark());
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Assignment inserted successfully");
            }else{
                System.out.println("Sorry, seems like you have already inserted "+a.getAtitle()+" at course "+a.getCid().getCtitle());
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
