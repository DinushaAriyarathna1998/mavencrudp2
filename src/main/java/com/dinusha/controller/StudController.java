package com.dinusha.controller;


import com.dinusha.model.Student;
import com.dinusha.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class StudController {

    private StudentService studentService;

    @Autowired
    public StudController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @RequestMapping(value = "/list")
//    public ModelAndView listStudent(HttpServletRequest request, HttpServletResponse response) {
//
//        List<Student> liststudent = studentService.selectallstudent();
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName( "studentlist" );
//        mv.addObject( "liststudent", liststudent );
//        return mv;
//    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listStudents(Model model) {
        model.addAttribute("liststudent", this.studentService.selectallstudent());
        return "/studentlist.jsp";
    }

    /*@GetMapping("/list")
    public String listStudent(Model theModel) {
        studentService = new StudentServiceImpl();
       List<Student> listStudent = StudentService.selectallstudent();
        theModel.addAttribute("listUser", listUser);
        return "user-list";
    }*/


    @RequestMapping(value = "/new")
    public ModelAndView showNewForm(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/addnew.jsp");
        return mv;
    }

    @RequestMapping(value = "/edit")
    private ModelAndView showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Student existingStudent = studentService.selectstudent( id );
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "/WEB-INF/views/addnew" );
        mv.addObject( "student", existingStudent );
        return mv;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insertStudent(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter( "name" );
        int age = Integer.parseInt( request.getParameter( "age" ) );
        String gender = request.getParameter( "gender" );
        Student newStudent = new Student( name, age, gender );
        System.out.println( newStudent );
        studentService.insertStudent( newStudent );
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/studentlist.jsp");
        mv.addObject( "liststudent", liststudent );
        return mv;
    }

    @RequestMapping("/update")
    public ModelAndView updateStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        String name = request.getParameter( "name" );
        int age = Integer.parseInt( request.getParameter( "age" ) );
        String gender = request.getParameter( "gender" );

        Student stud = new Student( id, name, age, gender );
        studentService.updatestudent( stud );
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "/WEB-INF/views/studentlist" );
        mv.addObject( "liststudent", liststudent );
        return mv;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        studentService.deletestudent( id );
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "/WEB-INF/views/studentlist" );
        mv.addObject( "liststudent", liststudent );
        return mv;
    }
}
