
package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;


public class StudentDAO {
    public static ArrayList<Student> getAllStudent() {
        DBContext db = DBContext.getInstance();
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = """
                         select * 
                         from Students
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Student student = new Student(
                        rs.getString("StudentID"),
                        rs.getString("FullName"),
                        rs.getString("BirthDate"),
                        rs.getString("Gender"),
                        rs.getString("English1"),
                        rs.getString("English2"),
                        rs.getString("English3"),
                        rs.getString("English4"),
                        rs.getString("Specialization")
                );
                students.add(student);
            }
        } catch (Exception e) {
            return null;
        }
        if(students.isEmpty()) return null;
        else return students;
    }
    
    public static Student getStudentBySearchingStudentID(String studentId) {
        DBContext db = DBContext.getInstance();
        Student student = new Student();
        try {
            String sql = """
                         select * 
                         from Students
                         where StudentID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, studentId);
            
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                student = new Student(
                        rs.getString("StudentID"),
                        rs.getString("FullName"),
                        rs.getString("BirthDate"),
                        rs.getString("Gender"),
                        rs.getString("English1"),
                        rs.getString("English2"),
                        rs.getString("English3"),
                        rs.getString("English4"),
                        rs.getString("Specialization")
                );

            }
        } catch (Exception e) {
            return null;
        }
        
        return student;
    }
    
    public static ArrayList<Student> getListStudentBySearchingName(String search) {
        DBContext db = DBContext.getInstance();
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = """
                         select * 
                         from Students
                         where FullName like ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, "%"+search+"%");
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Student student = new Student(
                        rs.getString("StudentID"),
                        rs.getString("FullName"),
                        rs.getString("BirthDate"),
                        rs.getString("Gender"),
                        rs.getString("English1"),
                        rs.getString("English2"),
                        rs.getString("English3"),
                        rs.getString("English4"),
                        rs.getString("Specialization")
                );
                students.add(student);
            }
        } catch (Exception e) {
            return null;
        }
        if(students.isEmpty()) return null;
        else return students;
    }
    
    public static ArrayList<Student> getListStudentByOrderByGender(String ops) {
        DBContext db = DBContext.getInstance();
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = """
                         select * 
                         from Students
                         where Gender = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, ops);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Student student = new Student(
                        rs.getString("StudentID"),
                        rs.getString("FullName"),
                        rs.getString("BirthDate"),
                        rs.getString("Gender"),
                        rs.getString("English1"),
                        rs.getString("English2"),
                        rs.getString("English3"),
                        rs.getString("English4"),
                        rs.getString("Specialization")
                );
                students.add(student);
            }
        } catch (Exception e) {
            return null;
        }
        if(students.isEmpty()) return null;
        else return students;
    }
    
    public static Student addingStudent(Student stu) {
        DBContext db = DBContext.getInstance();
        int n=0;
        
        try {
            String sql = """
                         INSERT INTO [dbo].[Students]
                                    ([StudentID]
                                    ,[FullName]
                                    ,[BirthDate]
                                    ,[Gender]
                                    ,[English1]
                                    ,[English2]
                                    ,[English3]
                                    ,[English4]
                                    ,[Specialization])
                              VALUES
                                    (?,?,?,?,?,?,?,?,?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, stu.getStudentID());
            statment.setString(2, stu.getFullName());
            statment.setString(3, stu.getBirthDate());
            statment.setString(4, stu.getGender());
            statment.setString(5, stu.getE1());
            statment.setString(6, stu.getE2());
            statment.setString(7, stu.getE3());
            statment.setString(8, stu.getE4());
            statment.setString(9, stu.getSpecId());
            
            n = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if(n==0) return null;
        else return stu;
    }

    public static Student editingStudent(Student stu) {
        DBContext db = DBContext.getInstance();
        int n=0;
        
        try {
            String sql = """
                         UPDATE [dbo].[Students]
                                       SET [FullName] = ?
                                          ,[BirthDate] = ?
                                          ,[Gender] = ?
                                          ,[English1] = ?
                                          ,[English2] = ?
                                          ,[English3] = ?
                                          ,[English4] = ?
                                          ,[Specialization] = ?
                                     WHERE [StudentID] = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            
            statment.setString(1, stu.getFullName());
            statment.setString(2, stu.getBirthDate());
            statment.setString(3, stu.getGender());
            statment.setString(4, stu.getE1());
            statment.setString(5, stu.getE2());
            statment.setString(6, stu.getE3());
            statment.setString(7, stu.getE4());
            statment.setString(8, stu.getSpecId());
            statment.setString(9, stu.getStudentID());
            n = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if(n==0) return null;
        else return stu;
    }

    public static String deletingStudent(String stuId) {
        DBContext db = DBContext.getInstance();
        int n=0;
        
        try {
            String sql = """
                         DELETE FROM [dbo].[Students]
                               WHERE [StudentID] = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, stuId);
            
            
            n = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if(n==0) return null;
        else return stuId;
    }

}
