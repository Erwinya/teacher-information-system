package com.RestfulApi.TeacherInformationSystem.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.RestfulApi.TeacherInformationSystem.model.SchoolClass;
import com.RestfulApi.TeacherInformationSystem.model.Teacher;
import com.RestfulApi.TeacherInformationSystem.repository.SchoolClassRepository;
import com.RestfulApi.TeacherInformationSystem.service.SchoolClassService;
import lombok.AllArgsConstructor;
import com.RestfulApi.TeacherInformationSystem.exception.SchoolClassNotFoundException;
import com.RestfulApi.TeacherInformationSystem.exception.TeacherNotFoundException;

@Service
@AllArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;
    private final com.RestfulApi.TeacherInformationSystem.repository.TeacherRepository teacherRepository;
    
    @Override
    public SchoolClass createClass(SchoolClass schoolClass) {
        schoolClass.setTeacher(resolveTeacher(schoolClass.getTeacher()));
        return schoolClassRepository.save(schoolClass);
    }
    
    @Override
    public SchoolClass updateClass(String id, SchoolClass schoolClass) {
        SchoolClass existing = schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException("Class not found with id: " + id));
        existing.setName(schoolClass.getName());
        existing.setTeacher(resolveTeacher(schoolClass.getTeacher()));
        existing.setStudents(schoolClass.getStudents());
        return schoolClassRepository.save(existing);
    }

    private Teacher resolveTeacher(Teacher teacherRef) {
        if (teacherRef == null || teacherRef.getId() == null || teacherRef.getId().isBlank()) {
            throw new IllegalArgumentException("teacher.id is required");
        }
        return teacherRepository.findById(teacherRef.getId())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + teacherRef.getId()));
    }
    
    @Override
    public void deleteClass(String id) {
        if (!schoolClassRepository.existsById(id)) {
            throw new SchoolClassNotFoundException("Class not found with id: " + id);
        }
        schoolClassRepository.deleteById(id);
    }
    
    @Override
    public SchoolClass getClassById(String id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(() -> new SchoolClassNotFoundException("Class not found with id: " + id));
    }

    @Override
    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }
    
    @Override
    public List<SchoolClass> getClassesByTeacherId(String teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + teacherId));
        return schoolClassRepository.findByTeacher(teacher);
    }
}