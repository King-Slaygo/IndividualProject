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
      out.println("</table>\n");
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<StudentList> listStudents, PrintWriter out) {
	   out.println("<table>\n" + //
	    		  "<tr>\n" + //
	    		  "<th>No</th>\n" + //
	    		  "<th>First Name</th>\n" + //
	    		  "<th>Last Name</th>\n" + //
	    		  "<th>Email</th>\n" + //
	    		  "<th>Age</th>\n" + //
	    		  "<th>GPA</th>\n" + //
	    		  "</tr>");
      for (StudentList student : listStudents) {
         System.out.println("[DBG] " + student.getId() + ", " //
               + student.getFirstName() + ", " //
               + student.getLastName() + ", " // 
         	   + student.getEmail() + ", " //
         	   + student.getAge() + ", " //
         	   + student.getGPA());

         out.println("<style>\n" + 
         		"table {\n" + 
         		"  font-family: arial, sans-serif;\n" + 
         		"  border-collapse: collapse;\n" + 
         		"  width: 100%;\n" + 
         		"}\n" + 
         		"\n" + 
         		"td, th {\n" + 
         		"  border: 1px solid #dddddd;\n" + 
         		"  text-align: left;\n" + 
         		"  padding: 8px;\n" + 
         		"}\n" + 
         		"\n" + 
         		"tr:nth-child(even) {\n" + 
         		"  background-color: #dddddd;\n" + 
         		"}\n" + 
         		"</style>");
         out.println("<tr>\n" + "<td>" // 
          	   + student.getId() + "</td>\n" //
               + "<td>" + student.getFirstName() + "</td>\n" //
               + "<td>" + student.getLastName() + "</td>\n" // 
           	   + "<td>" + student.getEmail() + "</td>\n" //
           	   + "<td>" + student.getAge() + "</td>\n" //
           	   + "<td>" + student.getGPA() + "</td>\n" //
           	   + "/<tr>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
