
package dao;

import entities.Student;
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

public class StudentDao {
    private final String URL = "jdbc:mysql://localhost:3306/individualproject?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "uaIngSOm0f";
    private Connection conn;
    
    private final String getStudents = "select * from student;";
    private final String getStudentById = "select * from student where sid = ?";
    private final String insertStudent = "insert ignore into student (sfname,slname,sdob,sfees) values (?,?,?,?)";
    
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public List<Student> getStudents() {
        List<Student> list = new ArrayList();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getStudents);
            //Step 4:
            while (rs.next()) {
                Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),rs.getInt(5));
                list.add(s);
            }
            //Step 5:close connections
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     /*
     Oles oi getById methodoi mou einai asfaleis ws prot in pithanotita na epistrafei null timi
     giati sti main krataw listes me ta id olwn twn entities, opote den yparxei pithanotita na mpei
     ws papametros id eggrafis pou den einai yparxei
     */
     public Student getStudentById(int id) {
        Student s = null;

        try {
            PreparedStatement pst = getConnection().prepareStatement(getStudentById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getInt(5));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
     
     public boolean insertStudent (Student s) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertStudent);
            pst.setString(1, s.getSfname());
            pst.setString(2, s.getSlname());
            Date date = Date.valueOf(s.getSdob()); 
            pst.setDate(3,date);
            pst.setInt(4, s.getSfees());
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Student inserted successfully");
            }else{
                System.out.println("Sorry,seems like you have already inserted "+s.getSfname()+" "+s.getSlname());
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
