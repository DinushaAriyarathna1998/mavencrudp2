package com.dinusha.service.impl;

import com.dinusha.dao.StudentDao;
import com.dinusha.model.Student;
import com.dinusha.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void insertStudent(Student student) {


        this.studentDao.insertStudent( student );

    }

    public void updatestudent(Student student) {


        this.studentDao.updateStudent( student );
    }

    public void deletestudent(int id) {


        this.studentDao.deleteStudent( id );
    }

    public Student selectstudent(int id) {

        return studentDao.selectStudent( id );

    }

    public List<Student> selectallstudent() {


        return studentDao.selectAllStudents();

    }
}
