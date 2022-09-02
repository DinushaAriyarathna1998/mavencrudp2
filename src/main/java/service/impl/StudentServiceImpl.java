package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Student;
import service.StudentService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void insertStudent(Student student){
        try {
            studentDao = new StudentDaoImpl();
            studentDao.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean updatestudent(Student student){
        boolean rawupdated = false;
        try {
            studentDao = new StudentDaoImpl();
            rawupdated = studentDao.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rawupdated;
    }

    public boolean deletestudent(int id){
        boolean rawDeleted = false;
        try {
            studentDao = new StudentDaoImpl();
            rawDeleted = studentDao.deleteStudent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rawDeleted;
    }

    public Student selectstudent(int id) {
        Student student = null;
        ResultSet resultSet = null;
        try {
            studentDao = new StudentDaoImpl();
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

    public List<Student> selectallstudent(){
        List<Student> student = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            studentDao = new StudentDaoImpl();
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
