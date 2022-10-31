package com.ironhack.security.services.interfaces;

import com.ironhack.security.controllers.dtos.StudentNameDTO;
import com.ironhack.security.models.Spell;
import com.ironhack.security.models.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(Long id);
    Student saveStudent(Student student);

    void updateSpellList(Long studentId, List<Spell> spellList);

    void updateStudent(Long studentId, StudentNameDTO studentNameDTO);

    void deleteStudent(Long studentId);
}
