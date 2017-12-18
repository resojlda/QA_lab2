package com.university.AppServices;

import com.university.Domain.Student;
import com.university.Domain.StudentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private StudentDAOImpl studentDAO;

    public List getListOfStudents() {
        return studentDAO.listStudents();
    }


    public List editStudentsWithNamesStartingFromE() {
        List<Student> students = studentDAO.listStudents();
        List<Student> result = new ArrayList<Student>();
        for (Student student : students)
            if (student.getName().charAt(0) == 'E') {
                student.setName(student.getName() + "_3");
                //studentDAO.update(student);
                result.add(student);
            }
        return result;
    }

    public List getListOfStudentsOlderThanX(Integer X) {
        List<Student> students = studentDAO.listStudents();
        List<Student> result = new ArrayList<Student>();
        for (Student student : students)
            if (student.getAge() > X)
                result.add(student);
        return result;
    }
}
