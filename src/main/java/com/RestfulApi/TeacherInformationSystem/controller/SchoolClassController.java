package com.RestfulApi.TeacherInformationSystem.controller;

import com.RestfulApi.TeacherInformationSystem.dto.SchoolClassDTO;
import com.RestfulApi.TeacherInformationSystem.response.CustomResponse;
import com.RestfulApi.TeacherInformationSystem.service.SchoolClassService;
import com.RestfulApi.TeacherInformationSystem.mapper.SchoolClassMapper;
import com.RestfulApi.TeacherInformationSystem.util.ApiResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/classes")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    @PostMapping
    public CustomResponse<SchoolClassDTO> createClass(@Valid @RequestBody SchoolClassDTO schoolClassDTO) {
        var createdClass = schoolClassService.createClass(SchoolClassMapper.toEntity(schoolClassDTO));
        return ApiResponseUtil.success(SchoolClassMapper.toDto(createdClass));
    }

    @GetMapping("/{id}")
    public CustomResponse<SchoolClassDTO> getClassById(@PathVariable String id) {
        var schoolClass = schoolClassService.getClassById(id);
        return ApiResponseUtil.success(SchoolClassMapper.toDto(schoolClass));
    }

    @GetMapping
    public CustomResponse<List<SchoolClassDTO>> getAllClasses() {
        List<SchoolClassDTO> classDTOs = schoolClassService.getAllClasses()
                .stream()
                .map(SchoolClassMapper::toDto)
                .collect(Collectors.toList());
        return ApiResponseUtil.success(classDTOs);
    }

    @PutMapping("/{id}")
    public CustomResponse<SchoolClassDTO> updateClass(@PathVariable String id, @Valid @RequestBody SchoolClassDTO schoolClassDTO) {
        var updatedClass = schoolClassService.updateClass(id, SchoolClassMapper.toEntity(schoolClassDTO));
        return ApiResponseUtil.success(SchoolClassMapper.toDto(updatedClass));
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Void> deleteClass(@PathVariable String id) {
        schoolClassService.deleteClass(id);
        return ApiResponseUtil.success(null);
    }

    @GetMapping("/teacher/{teacherId}")
    public CustomResponse<List<SchoolClassDTO>> getClassesByTeacherId(@PathVariable String teacherId) {
        List<SchoolClassDTO> classDTOs = schoolClassService.getClassesByTeacherId(teacherId)
                .stream()
                .map(SchoolClassMapper::toDto)
                .collect(Collectors.toList());
        return ApiResponseUtil.success(classDTOs);
    }
}
