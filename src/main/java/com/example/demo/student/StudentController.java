package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/listar")
    public String getStudents(Model model){
        List<Student> students = studentService.getStudents();
        model.addAttribute("students" , students);
        return "index";
    }

    @GetMapping("/nuevo")
    public String agregar(Model model){
        model.addAttribute("student" , new Student());
        return "formulario";
    }



    @PostMapping("/guardar")
    public String registerNewStudent(@Validated Student student,Model model ,BindingResult bindingResult){

        /*if (bindingResult.hasErrors()){
            model.addAttribute("titulo","Formulario Cliente");
            return "formulario";
        }*/
        String debug= (student.getId() != null)? "Student editado con éxito!" : "Student creado con éxito!";
        studentService.addNewStudent(student);

        return "redirect:/listar";
    }



    @PutMapping(path = "{studentId}")
    public String  updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){

        studentService.updateStudent(studentId,name,email);

        return "no existe";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,Model model){
        Optional<Student> student = null;
        if (id>0) {
            student=studentService.ListarId(id);
            if (student==null) {
                //flash.addFlashAttribute("error", "El ID del Cliente no exite en la Base de Datos !!");
                return "redirect:/listar";
            }

        } else {
            //flash.addFlashAttribute("error", "El ID del Cliente no puede ser 0 !!");
            return "redirect:/listar";
        }
        model.addAttribute("titulo","Editar Student");
        model.addAttribute("student",student);
        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteStudent(@PathVariable Long id, Model model){
        studentService.deleteStudent(id);
        return "redirect:/listar";
    }


}
