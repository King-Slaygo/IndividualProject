/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.StudentList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBAldarei {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<StudentList> listStudents() {
      List<StudentList> resultList = new ArrayList<StudentList>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> students = session.createQuery("FROM StudentList").list();
         for (Iterator<?> iterator = students.iterator(); iterator.hasNext();) {
            StudentList student = (StudentList) iterator.next();
            resultList.add(student);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<StudentList> listStudents(String keyword) {
      List<StudentList> resultList = new ArrayList<StudentList>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((StudentList)session.get(StudentList.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM Student");
         List<?> students = session.createQuery("FROM StudentList").list();
         for (Iterator<?> iterator = students.iterator(); iterator.hasNext();) {
            StudentList student = (StudentList) iterator.next();
            if (student.getLastName().startsWith(keyword)) {
               resultList.add(student);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createStudents(String firstName, String lastName, String age, String email, String gpa) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new StudentList(firstName, lastName, Integer.valueOf(age), email, Double.valueOf(gpa)));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
