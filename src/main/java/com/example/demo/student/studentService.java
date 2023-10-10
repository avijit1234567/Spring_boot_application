package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class studentService {

    private final studentRepository studentrepository;
    @Autowired
    public studentService(studentRepository studentrepository) {
        this.studentrepository = studentrepository;
    }

    public List<student> getStudents()
    {
        return studentrepository.findAll();
    }


/*    public void addNewStudent(student student) {
        System.out.println(student);
    }*/

    public student createStudent(student stu)
    {
        return studentrepository.save(stu);
    }

    public void delete_student(Long studentId) {
        boolean exist = studentrepository.existsById(studentId);
        if (!exist)
        {
            throw new IllegalStateException("student with Id "+studentId+" doesn't exist");
        }
        studentrepository.deleteById(studentId);
    }
    @Transactional
    public void update_student(Long studentId, String name, String email) {
        student stud = studentrepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("student with Id "+studentId+" doesn't exist"));

        if(name !=null && name.length()>0 && !Objects.equals(stud.getName(),name))
        {
            stud.setName(name);
        }
        if(email!=null && email.length()>0 && !Objects.equals(stud.getEmail(),email))
        {
            Optional<student>studentOptional=studentrepository.findStudentByEmail(email);
            if(studentOptional.isPresent())
            {
                throw new IllegalStateException("email taken");
            }
            stud.setEmail(email);
        }

    }
}
