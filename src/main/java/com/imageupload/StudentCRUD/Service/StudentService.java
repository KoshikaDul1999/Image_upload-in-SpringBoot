package com.imageupload.StudentCRUD.Service;

import com.imageupload.StudentCRUD.Entity.Student;

public interface StudentService {

    Iterable<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Student student);
    void deleteStudentById(Long id);
}
