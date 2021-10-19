package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){

        return studentRepository.findAll();
    }


    @Transactional
    public void addNewStudent(Student student) {

        //Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        /*if (studentOptional.isPresent()){

            throw new IllegalStateException("email Taken");

        }*/

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exist = studentRepository.existsById(studentId);
        if (!exist){

                throw  new IllegalStateException(
                        "stundet con id "+studentId+"no existe"
                );
        }
        studentRepository.deleteById(studentId);

    }


    public Optional<Student> ListarId(Long id){
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        System.out.println("hola!!!!!"+studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()-> new IllegalStateException("stundemt con id "+studentId+"no existe")
        );

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){

                student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){

            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email Taken");

            }
            student.setEmail(email);

        }


    }
}
