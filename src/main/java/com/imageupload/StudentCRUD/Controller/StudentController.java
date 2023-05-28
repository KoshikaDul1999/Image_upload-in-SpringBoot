package com.imageupload.StudentCRUD.Controller;

import com.imageupload.StudentCRUD.Entity.Student;
import com.imageupload.StudentCRUD.Service.StudentServiceImpl;
import com.imageupload.StudentCRUD.Util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/sudents/new")
    public String createStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(Student student,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()){
            String filename= StringUtils.cleanPath(multipartFile.getOriginalFilename());
            student.setImageName(filename);
            Student student1=studentService.saveStudent(student);
            String upload="images/"+student.getId();

            FileUploadUtil.saveFile(upload,filename,multipartFile);
        }else{
            if (student.getImageFile().isEmpty()){
                student.setImageFile(null);
                studentService.saveStudent(student);
            }
        }
        studentService.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
//    public String updateStudent(@PathVariable Long id,
//                                @ModelAttribute("student") Student student,
//                                Model model) {
//
//        // get student from database by id
//        Student existingStudent = studentService.getStudentById(id);
//        existingStudent.setId(id);
//        existingStudent.setVehicle_id(student.getVehicle_id());
//        existingStudent.setDescription(student.getDescription());
//        existingStudent.setDamage_date(student.getDamage_date());
//        existingStudent.setImage(student.getImage());
//        existingStudent.setAmount(student.getAmount());
//
//
//        // save updated student object
//        studentService.updateStudent(existingStudent);
//        return "redirect:/students";
//    }

    // handler method to handle delete student request

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
