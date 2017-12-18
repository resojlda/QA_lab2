package com.university;

import com.university.AppServices.ApplicationServiceImpl;
import com.university.Configuration.DatabaseConfig;
import com.university.Domain.Student;

import static org.junit.Assert.*;

import com.university.Domain.StudentDAOImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class ApplicationServiceImplIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    private static ApplicationServiceImpl service;
    private static StudentDAOImpl studentDAO;

    @BeforeClass
    public static void initService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        service = context.getBean(ApplicationServiceImpl.class);
        studentDAO = context.getBean(StudentDAOImpl.class);
    }

    @Before
    public void initTable(){
        String SQL = "DROP TABLE Student IF EXISTS;";
        jdbcTemplateObject.update(SQL);
        SQL = "CREATE TABLE Student (" +
                "ID INT NOT NULL IDENTITY," +
                "NAME VARCHAR(50) NOT NULL," +
                "SURNAME VARCHAR(50) NOT NULL," +
                "AGE INT NOT NULL);";
        jdbcTemplateObject.update(SQL);
    }

    @Test
    public void testGetStudentsOlderThanX_XisBig_EmptyResult() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student item = new Student();
            item.setId(i);
            item.setName(String.valueOf(i));
            item.setSurname(String.valueOf(i));
            item.setAge(i);
            studentDAO.create(String.valueOf(i), String.valueOf(i), i);
            list.add(item);
        }

        List<Student> result = service.getListOfStudentsOlderThanX(100);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetStudentsOlderThanX_XisSmall_AllStudentsResult() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student item = new Student();
            item.setId(i);
            item.setName(String.valueOf(i));
            item.setSurname(String.valueOf(i));
            item.setAge(i+1);
            studentDAO.create(String.valueOf(i), String.valueOf(i), i+1);
            list.add(item);
        }

        List<Student> result = service.getListOfStudentsOlderThanX(0);

        assertTrue(result.equals(list));
    }

    @Test
    public void testGetStudentsOlderThanX_XisNormal_SomeStudentsResult() {
        int X = 5;

        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student item = new Student();
            item.setId(i);
            item.setName(String.valueOf(i));
            item.setSurname(String.valueOf(i));
            item.setAge(i);
            studentDAO.create(String.valueOf(i), String.valueOf(i), i);
            if (i > X) list.add(item);
        }

        List<Student> result = service.getListOfStudentsOlderThanX(X);

        assertTrue(result.equals(list));
    }

    @Test
    public void testGetStudentsWithNamesStartingFromE_NoStudentsWithNameFromE_EmptyResult() {
        studentDAO.create("A", "A", 1);
        studentDAO.create("B", "A", 1);
        studentDAO.create("C", "A", 1);
        studentDAO.create("D", "A", 1);

        List<Student> result = service.editStudentsWithNamesStartingFromE();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetStudentsWithNamesStartingFromE_SomeStudentsWithNameFromE_SomeStudentsResult() {
        studentDAO.create("A", "A", 1);
        studentDAO.create("B", "A", 1);
        studentDAO.create("C", "A", 1);
        studentDAO.create("D", "A", 1);
        studentDAO.create("E", "A", 1);
        studentDAO.create("E", "B", 2);

        List<Student> targetList = new ArrayList<Student>();
        targetList.add(new Student(4,"E_3", "A", 1));
        targetList.add(new Student(5,"E_3", "B", 2));

        List<Student> result = service.editStudentsWithNamesStartingFromE();

        assertTrue(result.equals(targetList));
    }

    @Test
    public void testGetStudentsWithNamesStartingFromE_AllStudentsWithNameFromE_AllStudentsResult() {
        studentDAO.create("E", "A", 1);
        studentDAO.create("E", "B", 2);
        studentDAO.create("E", "C", 3);
        studentDAO.create("E", "D", 4);
        studentDAO.create("E", "E", 5);

        List<Student> targetList = new ArrayList<Student>();
        targetList.add(new Student(0,"E_3", "A", 1));
        targetList.add(new Student(1,"E_3", "B", 2));
        targetList.add(new Student(2,"E_3", "C", 3));
        targetList.add(new Student(3,"E_3", "D", 4));
        targetList.add(new Student(4,"E_3", "E", 5));

        List<Student> result = service.editStudentsWithNamesStartingFromE();

        for(Student student:targetList) {
            System.out.println(student);
        }

        System.out.println("\n\n");

        for(Student student:result) {
            System.out.println(student);
        }

        assertTrue(result.equals(targetList));
    }
}
