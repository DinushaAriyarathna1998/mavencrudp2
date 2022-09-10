package StudentPackage.dao.impl;

import java.sql.*;
import java.util.List;


import StudentPackage.dao.StudentDBUtil;
import StudentPackage.dao.StudentDao;
import StudentPackage.model.Student;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class StudentDaoImpl implements StudentDao {


    @Autowired
    private SessionFactory sessionFactory;





   /* private boolean rowDeleted;
    private boolean rowUpdated;
    private static Connection con = null;
    private static PreparedStatement prep = null;
    private static ResultSet rs = null;*/


    /*private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (name, age, gender) VALUES " + " (?, ?, ?);";
    private static final String SELECT_STUDENT_BY_ID = "select id,name,age,gender from student where id =?";
    private static final String SELECT_ALL_STUDENTS = "select * from student";
    private static final String DELETE_STUDENTS_SQL = "delete from student where id = ?;";
    private static final String UPDATE_STUDENTS_SQL = "update student set name = ?,age= ?, gender =? where id = ?;";*/

 /*   public StudentDaoImpl() {}*/



    /*public void insertStudent(Student student)throws SQLException{
        System.out.println(INSERT_STUDENT_SQL);
        // try-with-resource statement will auto close the connection.
        try {
            con = StudentDBUtil.getConnection();
            prep = con.prepareStatement(INSERT_STUDENT_SQL);
            prep.setString(1, student.getName());
            prep.setInt(2, student.getAge());
            prep.setString(3, student.getGender());
            System.out.println(prep);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public void insertStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(student);
    }

    /*public boolean deleteStudent(int id) throws SQLException {

        System.out.println(DELETE_STUDENTS_SQL);
        try {
            con = StudentDBUtil.getConnection();
            prep = con.prepareStatement(DELETE_STUDENTS_SQL);
            prep.setInt(1, id);
            rowDeleted = prep.executeUpdate() > 0;
            System.out.println(prep);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }*/

    public void deleteStudent(int id){
        Session session = sessionFactory.getCurrentSession();
        Student s = session.load( Student.class, new Integer(id));
        if(null != s){
            session.delete(s);
        }
/*
        logger.info("Student deleted successfully, Student details="+s);
*/
    }

    /*public boolean updateStudent(Student student) throws SQLException {

        try {
            con = StudentDBUtil.getConnection();
            prep = con.prepareStatement(UPDATE_STUDENTS_SQL);
            prep.setString(1, student.getName());
            prep.setInt(2, student.getAge());
            prep.setString(3, student.getGender());
            prep.setInt(4, student.getId());
            rowUpdated = prep.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }*/

    public void updateStudent(Student student){
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
/*
        logger.info("Person updated successfully, Person Details="+student);
*/
    }

    /*public ResultSet selectAllStudents()throws SQLException{
        //establish connection
        try { con = StudentDBUtil.getConnection();
             // Step 2:Create a statement using connection object
            prep = con.prepareStatement(SELECT_ALL_STUDENTS);
            System.out.println(prep);
            // Step 3: Execute the query or update query
            rs = prep.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }*/

    public List<Student> selectAllStudents(){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< Student > cq = cb.createQuery(Student.class);
        Root< Student > root = cq.from(Student.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    /*public ResultSet selectStudent(int id)throws SQLException {

        // Step 1: Establishing a Connection
        try  {con = StudentDBUtil.getConnection();
             // Step 2:Create a statement using connection object
             prep = con.prepareStatement(SELECT_STUDENT_BY_ID);
             prep.setInt(1, id);
             System.out.println(prep);
             // Step 3: Execute the query or update query
             rs = prep.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }*/

    public Student selectStudent(int id){
        Session session = sessionFactory.getCurrentSession();
        Student stud = session.load(Student.class, new Integer(id));
        return stud;
    }
}

