package StudentPackage.control;


import StudentPackage.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private StudentService studentService;

    //how to add a default path in RequestMapping?
    @RequestMapping("/")
    private ModelAndView listStudent(HttpServletRequest request, HttpServletResponse response)
    {
        studentService = new StudentServiceImpl();
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist.jsp");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
    @RequestMapping("/new")
    private ModelAndView showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addnew.jsp");
        return mv;
    }

    @RequestMapping("/edit")
    private ModelAndView showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentService.selectstudent(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addnew.jsp");
        mv.addObject("student" , existingStudent);
        return mv;
    }

    @RequestMapping("/insert")
    private ModelAndView insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        Student newStudent = new Student(name, age, gender);
        studentService.insertStudent(newStudent);
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist.jsp");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
    @RequestMapping("/update")
    private ModelAndView updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");

        Student stud = new Student(id, name, age, gender);
        studentService.updatestudent(stud);
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist.jsp");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
    @RequestMapping("/delete")
    private ModelAndView deleteStudent(HttpServletRequest request, HttpServletResponse response)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deletestudent(id);
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentlist.jsp");
        mv.addObject("liststudent" , liststudent);
        return mv;
    }
}
