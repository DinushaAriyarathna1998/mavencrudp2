package StudentPackage.service.impl;

import StudentPackage.dao.StudentDao;
import StudentPackage.dao.impl.StudentDaoImpl;
import StudentPackage.model.Student;
import StudentPackage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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

            studentDao = new StudentDaoImpl();
            studentDao.insertStudent(student);

    }

    public void updatestudent(Student student){

            studentDao = new StudentDaoImpl();
            studentDao.updateStudent(student);
    }

    public void deletestudent(int id){

            studentDao = new StudentDaoImpl();
            studentDao.deleteStudent(id);
    }

    public Student selectstudent(int id) {
            studentDao = new StudentDaoImpl();
            return studentDao.selectStudent(id);

    }

    public List<Student> selectallstudent(){

            studentDao = new StudentDaoImpl();
            return studentDao.selectAllStudents();

    }
}
