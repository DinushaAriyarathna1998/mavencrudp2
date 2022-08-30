package control;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.DaoDBUtil;
import model.Student;


@WebServlet("/")
public class Insertcontroller extends HttpServlet {
    private DaoDBUtil DaoDBUtil;


    public void init() {
        DaoDBUtil = new DaoDBUtil();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        try{
            switch (action){
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertStudent(request, response);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateStudent(request, response);
                    break;
                default:
                    listStudent(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> liststudent = DaoDBUtil.selectAllStudents();
        request.setAttribute("liststudent", liststudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentlist.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addnew.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = DaoDBUtil.selectStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addnew.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);

    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        Student newStudent = new Student(name, age, gender);
        DaoDBUtil.insertStudent(newStudent);
        response.sendRedirect("list");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");

        Student stud = new Student(id, name, age, gender);
        DaoDBUtil.updateStudent(stud);
        response.sendRedirect("list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DaoDBUtil.deleteStudent(id);
        response.sendRedirect("list");

    }

}
