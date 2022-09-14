package com.dinusha.controller;


import com.dinusha.model.Student;
import com.dinusha.service.StudentService;
import com.dinusha.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class StudController {

    @Autowired
    private StudentService studentService = new StudentServiceImpl();


    @RequestMapping(value = "/list")
    private ModelAndView listStudent(HttpServletRequest request, HttpServletResponse response) {
        studentService = new StudentServiceImpl();
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "studentlist" );
        mv.addObject( "liststudent", liststudent );
        return mv;
    }
    /*@GetMapping("/list")
    public String listStudent(Model theModel) {
        studentService = new StudentServiceImpl();
       List<Student> listStudent = StudentService.selectallstudent();
        theModel.addAttribute("listUser", listUser);
        return "user-list";
    }*/


    @RequestMapping(value = "/new")
    private ModelAndView showNewForm(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "/WEB-INF/view/addnew" );
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
    private ModelAndView insertStudent(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter( "name" );
        int age = Integer.parseInt( request.getParameter( "age" ) );
        String gender = request.getParameter( "gender" );
        Student newStudent = new Student( name, age, gender );
        System.out.println( newStudent );
        studentService.insertStudent( newStudent );
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "studentlist" );
        mv.addObject( "liststudent", liststudent );
        return mv;
    }

    @RequestMapping("/update")
    private ModelAndView updateStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        String name = request.getParameter( "name" );
        int age = Integer.parseInt( request.getParameter( "age" ) );
        String gender = request.getParameter( "gender" );

        Student stud = new Student( id, name, age, gender );
        studentService.updatestudent( stud );
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "/WEB-INF/view/studentlist" );
        mv.addObject( "liststudent", liststudent );
        return mv;
    }

    @RequestMapping(value = "/delete")
    private ModelAndView deleteStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        studentService.deletestudent( id );
        List<Student> liststudent = studentService.selectallstudent();
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "/WEB-INF/view/studentlist" );
        mv.addObject( "liststudent", liststudent );
        return mv;
    }
}
