package com.university;

import com.university.AppServices.ApplicationServiceImpl;
import com.university.Domain.Student;
import com.university.Domain.StudentDAOImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceImplTest extends TestCase {

    @Mock
    private StudentDAOImpl repository;

    @InjectMocks
    private ApplicationServiceImpl service;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void testGetStudentsOlderThanX_XisBig_EmptyResult() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student item = new Student();
            item.setId(i);
            item.setName(String.valueOf(i));
            item.setSurname(String.valueOf(i));
            item.setAge(i);
            list.add(item);
        }
        Mockito.when(repository.listStudents()).thenReturn(list);

        List<Student> result = service.getListOfStudentsOlderThanX(100);

        assertTrue(result.isEmpty());
    }

    @org.junit.Test
    public void testGetStudentsOlderThanX_XisSmall_AllStudentsResult() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student item = new Student();
            item.setId(i);
            item.setName(String.valueOf(i));
            item.setSurname(String.valueOf(i));
            item.setAge(i+1);
            list.add(item);
        }
        Mockito.when(repository.listStudents()).thenReturn(list);

        List<Student> result = service.getListOfStudentsOlderThanX(0);

        assertTrue(result.equals(list));
    }

    @org.junit.Test
    public void testGetStudentsOlderThanX_XisNormal_SomeStudentsResult() {
        int X = 5;

        List<Student> list = new ArrayList<Student>();
        List<Student> targetList = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student item = new Student();
            item.setId(i);
            item.setName(String.valueOf(i));
            item.setSurname(String.valueOf(i));
            item.setAge(i);
            list.add(item);
            if (i > X) targetList.add(item);
        }

        Mockito.when(repository.listStudents()).thenReturn(list);

        List<Student> result = service.getListOfStudentsOlderThanX(X);

        assertTrue(result.equals(targetList));
    }

    @org.junit.Test
    public void testGetStudentsWithNamesStartingFromE_NoStudentsWithNameFromE_EmptyResult() {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("A", "A", 1));
        list.add(new Student("B", "A", 1));
        list.add(new Student("C", "A", 1));
        list.add(new Student("D", "A", 1));

        Mockito.when(repository.listStudents()).thenReturn(list);

        List<Student> result = service.editStudentsWithNamesStartingFromE();

        assertTrue(result.isEmpty());
    }

    @org.junit.Test
    public void testGetStudentsWithNamesStartingFromE_SomeStudentsWithNameFromE_SomeStudentsResult() {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("A", "A", 1));
        list.add(new Student("B", "A", 1));
        list.add(new Student("C", "A", 1));
        list.add(new Student("D", "A", 1));
        list.add(new Student("E", "A", 1));
        list.add(new Student("E", "B", 2));

        List<Student> targetList = new ArrayList<Student>();
        targetList.add(new Student("E_3", "A", 1));
        targetList.add(new Student("E_3", "B", 2));

        Mockito.when(repository.listStudents()).thenReturn(list);

        List<Student> result = service.editStudentsWithNamesStartingFromE();

        assertTrue(result.equals(targetList));
    }

    @org.junit.Test
    public void testGetStudentsWithNamesStartingFromE_AllStudentsWithNameFromE_AllStudentsResult() {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("E", "A", 1));
        list.add(new Student("E", "B", 2));
        list.add(new Student("E", "C", 3));
        list.add(new Student("E", "D", 4));
        list.add(new Student("E", "E", 5));

        List<Student> targetList = new ArrayList<Student>();
        targetList.add(new Student("E_3", "A", 1));
        targetList.add(new Student("E_3", "B", 2));
        targetList.add(new Student("E_3", "C", 3));
        targetList.add(new Student("E_3", "D", 4));
        targetList.add(new Student("E_3", "E", 5));

        Mockito.when(repository.listStudents()).thenReturn(list);

        List<Student> result = service.editStudentsWithNamesStartingFromE();

        assertTrue(result.equals(targetList));
    }

    public static Test suite()
    {
        return new TestSuite( ApplicationServiceImplTest.class );
    }
}
