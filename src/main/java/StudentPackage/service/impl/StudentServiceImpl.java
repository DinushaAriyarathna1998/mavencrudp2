package StudentPackage.service.impl;

import StudentPackage.dao.StudentDao;
import StudentPackage.dao.impl.StudentDaoImpl;
import StudentPackage.model.Student;
import StudentPackage.service.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
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

    public void updatestudent(Student student){

        try {
            studentDao = new StudentDaoImpl();
            studentDao.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletestudent(int id){
        boolean rawDeleted = false;
        try {
            studentDao = new StudentDaoImpl();
            studentDao.deleteStudent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Student selectstudent(int id) {
        Student student = null;

        try {
            studentDao = new StudentDaoImpl();
            student = studentDao.selectStudent(id);
            System.out.println(student);
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> selectallstudent(){
        List<Student> student = new ArrayList<>();
        try {
            studentDao = new StudentDaoImpl();
            student = studentDao.selectAllStudents();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
