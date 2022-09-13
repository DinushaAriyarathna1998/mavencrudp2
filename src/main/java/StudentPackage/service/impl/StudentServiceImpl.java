package StudentPackage.service.impl;

import StudentPackage.dao.StudentDao;
import StudentPackage.model.Student;
import StudentPackage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    public void insertStudent(Student student){


        this.studentDao.insertStudent(student);

    }

    public void updatestudent(Student student){


        this.studentDao.updateStudent(student);
    }

    public void deletestudent(int id){


        this.studentDao.deleteStudent(id);
    }

    public Student selectstudent(int id) {

        return studentDao.selectStudent(id);

    }

    public List<Student> selectallstudent(){


       return studentDao.selectAllStudents();

    }
}
