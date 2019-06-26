
package dao;

import entities.Homework;
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

public class HomeworkDao {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getAllHomework = "select * from homework";
    private final String getHomeworkByStudent = "select * from homework where sid = ?";
    private final String getHomeworkByCourse = "select hwid,sid,a.aid,omark,tmark from homework as h inner join assignment as a inner join course as c on h.aid = a.aid and a.cid = c.cid and c.cid = ? order by h.hwid;";
    private final String insertHomework = "insert ignore into homework (sid,aid,omark,tmark) values (?,?,?,?)"; 
    
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
    /*
    Task #8 // Assignments per student // For All Students
    */
    public List<Homework> getAllHomeWork(){
        List<Homework> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllHomework);
            StudentDao sdao = new StudentDao();
            AssignmentDao adao = new AssignmentDao();
            while (rs.next()) {
                Homework hw = new Homework(rs.getInt(1),sdao.getStudentById(rs.getInt(2)),adao.getAssignmentById(rs.getInt(3)),rs.getInt(4),rs.getInt(5));
                list.add(hw);
            }
            closeConnections(rs, st);
            }catch (SQLException ex) {
             Logger.getLogger(HomeworkDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
        }
    /*
    Task #8 also // Assignments of a specific student
    */
     public List<Homework> getHomeworkByStudent(int id) {
        List<Homework> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getHomeworkByStudent);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            StudentDao sdao = new StudentDao();
            AssignmentDao adao = new AssignmentDao();
            while (rs.next()) {
                Homework hw = new Homework(rs.getInt(1),sdao.getStudentById(rs.getInt(2)),adao.getAssignmentById(rs.getInt(3)),rs.getInt(4),rs.getInt(5));
                list.add(hw);
            }
            closeConnections(rs, pst);
            }catch (SQLException ex) {
             Logger.getLogger(HomeworkDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
        }
     /*
     Task #8 also // Assignments of students for a specific course
     */
     public List<Homework> getHomeworkByCourse(int id){
        List<Homework> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getHomeworkByCourse);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            StudentDao sdao = new StudentDao();
            AssignmentDao adao = new AssignmentDao();
            while (rs.next()) {
                Homework hw = new Homework(rs.getInt(1),sdao.getStudentById(rs.getInt(2)),adao.getAssignmentById(rs.getInt(3)),rs.getInt(4),rs.getInt(5));
                list.add(hw);
            }
            closeConnections(rs, pst);
            }catch (SQLException ex) {
             Logger.getLogger(HomeworkDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
     }
    
     public boolean insertHomework (int sid, int aid, int omark, int tmark) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertHomework);
            pst.setInt(1, sid);
            pst.setInt(2, aid);
            pst.setInt(3, omark);
            pst.setInt(4, tmark);
            
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Homework inserted successfully");
            }else{
                System.out.println("Sorry,seems like you have already inserted this combination of student-assignment");
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
    
    
    
    
    

