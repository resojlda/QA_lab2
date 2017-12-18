package com.university.Domain;


public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;

    public Student() {
    }

    /**
     *
     * @param name Student's name
     * @param surname Student's surname
     * @param age Student's age
     */
    public Student(String name, String surname, Integer age) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Student(int id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Student ID = ");
        res.append(this.id);
        res.append("\nName = ");
        res.append(this.name);
        res.append("\nSurname = ");
        res.append(this.surname);
        res.append("\nAge = ");
        res.append(this.age);
        return res.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null) {
            Student student = (Student) object;
            if (!this.age.equals(student.getAge()) ||
                !this.name.equals(student.getName()) ||
                !this.surname.equals(student.getSurname()) ||
                !this.id.equals(student.getId())) return false;
            return true;
        }
        return false;
    }
}