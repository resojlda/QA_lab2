package com.university.Representation;

/**
 * Hello world!
 *
 */

import com.university.AppServices.ApplicationServiceImpl;
import com.university.Configuration.DatabaseConfig;
import com.university.Domain.Student;
import com.university.Domain.StudentDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        ApplicationServiceImpl service = context.getBean(ApplicationServiceImpl.class);

        StudentDAOImpl studentDAO = context.getBean(StudentDAOImpl.class);

        System.out.println("All students: \n");
        List<Student> res = service.getListOfStudents();
        for (Student student : res) {
            System.out.println(student + "\n");
        }

        System.out.println("Students older than 17: \n");
        res = service.getListOfStudentsOlderThanX(17);
        for (Student student : res) {
            System.out.println(student + "\n");
        }

        service.editStudentsWithNamesStartingFromE();

        System.out.println("All students after editing: \n");
        res = service.getListOfStudents();
        for (Student student : res) {
            System.out.println(student + "\n");
        }
    }
}
