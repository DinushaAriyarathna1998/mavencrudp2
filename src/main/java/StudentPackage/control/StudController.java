package StudentPackage.control;


import StudentPackage.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import StudentPackage.service.StudentService;
import StudentPackage.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class StudController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(value ="/", method = RequestMethod.GET)
    private ModelAndView listStudent(HttpServletRequest request, HttpServletResponse response)
    {
        studentService = new StudentServiceImpl();
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
    @RequestMapping(value="/new", method = RequestMethod.GET)
    private ModelAndView showNewForm(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addnew");
        return mv;
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    private ModelAndView showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService = new StudentServiceImpl();
        Student existingStudent = studentService.selectstudent(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addnew");
        mv.addObject("student" , existingStudent);
        return mv;
    }

    @RequestMapping(value="/insert", method = RequestMethod.GET)
    private ModelAndView insertStudent(HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        Student newStudent = new Student(name, age, gender);
        System.out.println(newStudent);
        studentService = new StudentServiceImpl();
        studentService.insertStudent(newStudent);
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
    @RequestMapping("/update")
    private ModelAndView updateStudent(HttpServletRequest request, HttpServletResponse response)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");

        Student stud = new Student(id, name, age, gender);
        studentService = new StudentServiceImpl();
        studentService.updatestudent(stud);
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    private ModelAndView deleteStudent(HttpServletRequest request, HttpServletResponse response)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deletestudent(id);
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
}
