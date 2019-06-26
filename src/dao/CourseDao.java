
package dao;

import entities.Course;
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

public class CourseDao {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getCourses = "select * from course;";
    private final String getCourseById = "select * from course where cid=?";
    private final String insertCourse = "insert ignore into course (ctitle,ctype,cstream,csdate,cedate) values (?,?,?,?,?)";
    
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
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
    
    public List<Course> getCourses() {
        List<Course> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getCourses);
            //Step 4:
            while (rs.next()) {
                Course c= new Course (rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),rs.getDate(6).toLocalDate());
                list.add(c);
            }
            //Step 5:close connections
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Course getCourseById(int id) {
        Course c = null;

        try {
            PreparedStatement pst = getConnection().prepareStatement(getCourseById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            c = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate());
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    public boolean insertCourse (Course c) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertCourse);
            pst.setString(1, c.getCtitle());
            pst.setString(2, c.getCtype());
            pst.setString(3,c.getCstream()); 
            pst.setDate(4,Date.valueOf(c.getCsdate()));
            pst.setDate(5, Date.valueOf(c.getCedate()));
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Course inserted successfully");
            }
            else{
                System.out.println("Sorry,seems like you have already inserted "+c.getCtitle()+" "+c.getCtype());
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
