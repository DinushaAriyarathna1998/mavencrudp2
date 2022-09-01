package service;

import dao.StudentDao;
import dao.StudentDaoInterface;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentServiceint{

    private StudentDaoInterface studentDao;

    public void insertstudentservice(Student student){
        try {
            studentDao = new StudentDao();
            studentDao.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean updatestudentservice(Student student){
        boolean rawupdated = false;
        try {
            studentDao = new StudentDao();
            rawupdated = studentDao.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rawupdated;
    }

    public boolean deletestudentservice(int id){
        boolean rawDeleted = false;
        try {
            studentDao = new StudentDao();
            rawDeleted = studentDao.deleteStudent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rawDeleted;
    }

    public Student selectstudentservice(int id) {
        Student student = null;
        ResultSet resultSet = null;
        try {
            studentDao = new StudentDao();
            resultSet = studentDao.selectStudent(id);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                student = new Student(id, name, age, gender);
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> selectallstudentsservice(){
        List<Student> student = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            studentDao = new StudentDao();
            resultSet = studentDao.selectAllStudents();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                student.add(new Student(id, name, age, gender));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
