package service;

import dao.DaoDBUtil;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceStudent {

    DaoDBUtil daoDBUtil;



    public void insertstudentservice(Student student){
        try {
            daoDBUtil = new DaoDBUtil();
            daoDBUtil.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean updatestudentservice(Student student){
        boolean rawupdated = false;
        try {
            daoDBUtil = new DaoDBUtil();
            rawupdated = daoDBUtil.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rawupdated;
    }

    public boolean deletestudentservice(int id){
        boolean rawDeleted = false;
        try {
            daoDBUtil = new DaoDBUtil();
            rawDeleted = daoDBUtil.deleteStudent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rawDeleted;
    }

    public Student selectstudentservice(int id) {
        Student student = null;
        ResultSet resultSet = null;
        try {
            daoDBUtil = new DaoDBUtil();
            resultSet = daoDBUtil.selectStudent(id);

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
            daoDBUtil = new DaoDBUtil();
            resultSet = daoDBUtil.selectAllStudents();

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
