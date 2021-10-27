import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDBAldarei;

@WebServlet("/StudentInfoInsert")
public class StudentInfoInsert extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public StudentInfoInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String firstName = request.getParameter("firstName").trim();
      String lastName = request.getParameter("lastName").trim();
      String age = request.getParameter("age").trim();
      String email = request.getParameter("email").trim();
      String gpa = request.getParameter("gpa").trim();
      UtilDBAldarei.createStudents(firstName, lastName, age, email, gpa);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Student List";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li> First Name: " + firstName);
      out.println("<li> Last Name: " + lastName);
      out.println("<li> Age: " + age);
      out.println("<li> Email: " + email);
      out.println("<li> GPA: " + gpa);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Student</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
