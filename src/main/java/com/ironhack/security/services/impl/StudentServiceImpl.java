package com.ironhack.security.services.impl;

import com.ironhack.security.controllers.dtos.StudentNameDTO;
import com.ironhack.security.models.Spell;
import com.ironhack.security.models.Student;
import com.ironhack.security.repositories.StudentRepository;
import com.ironhack.security.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }

        return studentOptional.get();
    }

    public Student saveStudent(Student student) {
        //student.setId(null);
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This id: " + student.getId() + " already exists for another Student");
        }
        return studentRepository.save(student);
    }

    public void updateSpellList(Long studentId, List<Spell> spellList) {
        Student student = getStudentById(studentId);

        student.setSpellList(spellList);

        studentRepository.save(student);
    }

    public void updateStudent(Long studentId, StudentNameDTO studentNameDTO) {
        Student student = getStudentById(studentId);

        student.setFirstName(studentNameDTO.getFirstName());
        student.setLastName(studentNameDTO.getLastName());

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Student student = getStudentById(studentId);

        studentRepository.delete(student);
    }
}
