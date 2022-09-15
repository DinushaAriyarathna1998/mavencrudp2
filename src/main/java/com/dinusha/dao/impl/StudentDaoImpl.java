package com.dinusha.dao.impl;

import com.dinusha.dao.StudentDao;
import com.dinusha.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public class StudentDaoImpl implements StudentDao {

    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertStudent(Student student) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate( student );
    }


    @Override
    @Transactional
    public void deleteStudent(int id) {
        Session session = this.getSession();
        Student student = (Student) session.load(Student.class, id);
        if (null != student) {
            session.delete(student);
        }

    }


    @Override
    public void updateStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update( student );

    }


    @Override
    public List<Student> selectAllStudents() {

        Session session = this.getSession();
        List<Student> studentList = session.createQuery("from Student").list();
        return studentList;
    }

    @Override
    public Student selectStudent(int id) {
        Session session = this.getSession();
        Student student = (Student) session.load(Student.class, id);
        return student;
    }
}

