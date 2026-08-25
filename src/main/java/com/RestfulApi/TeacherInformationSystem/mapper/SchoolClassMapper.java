package com.RestfulApi.TeacherInformationSystem.mapper;

import java.util.stream.Collectors;

import com.RestfulApi.TeacherInformationSystem.dto.SchoolClassDTO;
import com.RestfulApi.TeacherInformationSystem.model.SchoolClass;
import com.RestfulApi.TeacherInformationSystem.model.Teacher;

public class SchoolClassMapper {
    public static SchoolClassDTO toDto(SchoolClass schoolClass) {
        if (schoolClass == null) {
            return null;
        }
        SchoolClassDTO dto = new SchoolClassDTO();
        dto.setName(schoolClass.getName());
        dto.setTeacher(schoolClass.getTeacher());
        if (schoolClass.getStudents() != null) {
            dto.setStudents(schoolClass.getStudents().stream()
                .map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList()));
        }
        return dto;
    }

    public static SchoolClass toEntity(SchoolClassDTO dto) {
        if (dto == null) {
            return null;
        }
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setName(dto.getName());
        if (dto.getTeacher() != null && dto.getTeacher().getId() != null) {
            Teacher teacher = new Teacher();
            teacher.setId(dto.getTeacher().getId());
            schoolClass.setTeacher(teacher);
        }
        return schoolClass;
    }
}
