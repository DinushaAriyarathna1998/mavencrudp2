package StudentPackage.dao.impl;
import java.util.List;
import StudentPackage.dao.StudentDao;
import StudentPackage.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class StudentDaoImpl implements StudentDao {


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void insertStudent(Student student) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(student);
    }


    @Override
    public void deleteStudent(int id){
        Session session = sessionFactory.getCurrentSession();
        Student stud = session.byId(Student.class).load(id);
        session.delete(stud);

    }


    @Override
    public void updateStudent(Student student){
        Session session = sessionFactory.getCurrentSession();
        session.update(student);

    }


    @Override
    public List selectAllStudents(){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< Student > cq = cb.createQuery(Student.class);
        Root< Student > root = cq.from(Student.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Student selectStudent(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.load(Student.class, id );
    }
}

