package com.university.Domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("studentDAO")
public class StudentDAOImpl implements StudentDAO{

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    public void create(String name, String surname, Integer age) {
        String SQL = "insert into Student (name, surname, age) values (?, ?, ?)";
        jdbcTemplateObject.update(SQL, name, surname, age);
    }

    public Student getStudent(Integer id) {
        String SQL = "select * from Student where id = ?";
        Student student = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new StudentMapper());

        return student;
    }

    public List<Student> listStudents() {
        String SQL = "select * from Student";
        List <Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
        return students;
    }

    public void delete(Integer id) {
        String SQL = "delete from Student where id = ?";
        jdbcTemplateObject.update(SQL, id);
    }

    public void update(Student student){
        String SQL = "update Student set name = ?, surname = ?, age = ? where id = ?";
        jdbcTemplateObject.update(SQL, student.getName(), student.getSurname(), student.getAge(), student.getId());
    }
}