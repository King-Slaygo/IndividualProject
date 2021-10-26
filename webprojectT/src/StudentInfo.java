import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.StudentList;
import util.Info;
import util.UtilDBAldarei;

@WebServlet("/StudentInfo")
public class StudentInfo extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public StudentInfo() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

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

      List<StudentList> listStudents = null;
      if (keyword != null && !keyword.isEmpty()) {
         listStudents = UtilDBAldarei.listStudents(keyword);
      } else {
         listStudents = UtilDBAldarei.listStudents();
      }
      display(listStudents, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<StudentList> listStudents, PrintWriter out) {
      for (StudentList student : listStudents) {
         System.out.println("[DBG] " + student.getId() + ", " //
               + student.getFirstName() + ", " //
               + student.getLastName() + ", " // 
         	   + student.getEmail() + ", " //
         	   + student.getAge());

         out.println("<li>" + student.getId() + ", " //
               + student.getFirstName() + ", " //
               + student.getLastName() + ", " // 
               + student.getEmail() + ", " //
               + student.getAge() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
