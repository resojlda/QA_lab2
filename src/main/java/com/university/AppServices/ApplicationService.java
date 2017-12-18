package com.university.AppServices;

import java.util.List;

public interface ApplicationService {

    public List getListOfStudents();

    public List editStudentsWithNamesStartingFromE();

    public List getListOfStudentsOlderThanX(Integer X);
}
