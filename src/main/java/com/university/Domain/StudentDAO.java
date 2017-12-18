package com.university.Domain;

import java.util.List;

public interface StudentDAO {

    public void create(String name, String surname, Integer age);

    public Student getStudent(Integer id);

    public List<Student> listStudents();

    public void delete(Integer id);

    public void update(Student student);
}