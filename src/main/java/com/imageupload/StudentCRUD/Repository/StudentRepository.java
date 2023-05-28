package com.imageupload.StudentCRUD.Repository;

import com.imageupload.StudentCRUD.Entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
