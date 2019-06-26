
package dao;

import entities.Enrollment;
import entities.Student;
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

public class EnrollmentDao {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getStudentsByCourse = "select * from enrollments where cid = ?";
    private final String getAllEnrollments = "select * from enrollments";
    private final String getBusyStudents = "select s.sid,sfname,slname,sdob,sfees,count(*) from enrollments as e, student as s where e.sid = s.sid group by s.sid having count(*)>1";
    private final String insertEnrollment = "insert ignore into enrollments (sid,cid) values (?,?)";
    
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Used for task #5 --> Select students per course // For a specific course
    */
    public List<Student> getStudentsByCourse(int id) {
        List<Student> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getStudentsByCourse);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            StudentDao sdao = new StudentDao();
            while (rs.next()) { 
                Student s = sdao.getStudentById(rs.getInt(2));
                list.add(s);
            }
            closeConnections(rs, pst);
            }catch (SQLException ex) {
             Logger.getLogger(EnrollmentDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
        }
    /*
    task #5 also // Select student per course // For all courses at once
    */
    public List<Enrollment> getEnrollments(){
        List<Enrollment> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAllEnrollments);
            StudentDao sdao = new StudentDao();
            CourseDao cdao = new CourseDao();
            while (rs.next()) { 
                Enrollment e = new Enrollment (rs.getInt(1),sdao.getStudentById(rs.getInt(2)),cdao.getCourseById(rs.getInt(3)));
                list.add(e);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /*
    Task #9 // students who attend multiple courses
     */
    public void printBusyStudents() {
        List<Student> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getBusyStudents);
            while (rs.next()) {
                Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),rs.getInt(5),rs.getInt(6));
                list.add(s);
            }
            closeConnections(rs, st);
            }catch (SQLException ex) {
             Logger.getLogger(EnrollmentDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        for (Student s: list){
            System.out.println("Student "+s.getSfname()+" "+s.getSlname()+" with sid:"+s.getSid()+" attends "+s.getNumOfCourses()+" courses");
        }
    }
    
    public boolean insertEnrollment (int sid, int cid) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertEnrollment);
            pst.setInt(1, sid);
            pst.setInt(2, cid);
            
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Enrollment inserted successfully");
            }else{
                System.out.println("Sorry,seems like you have already inserted this combination of student-course");
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

    

    
