package com.RestfulApi.TeacherInformationSystem.controller;

import com.RestfulApi.TeacherInformationSystem.dto.TeacherDTO;
import com.RestfulApi.TeacherInformationSystem.response.CustomResponse;
import com.RestfulApi.TeacherInformationSystem.service.TeacherService;
import com.RestfulApi.TeacherInformationSystem.mapper.TeacherMapper;
import com.RestfulApi.TeacherInformationSystem.util.ApiResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "Create a new teacher", description = "Creates a new teacher with unique email.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Teacher created successfully"),
            @ApiResponse(responseCode = "409", description = "Duplicate email")
    })
    @PostMapping
    public CustomResponse<TeacherDTO> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        var createdTeacher = teacherService.createTeacher(TeacherMapper.toEntity(teacherDTO));
        return ApiResponseUtil.success(TeacherMapper.toDto(createdTeacher));
    }

    @Operation(summary = "Get teacher by ID", description = "Returns a teacher by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Teacher found"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @GetMapping("/{id}")
    public CustomResponse<TeacherDTO> getTeacherById(@PathVariable String id) {
        var teacher = teacherService.getTeacherById(id);
        return ApiResponseUtil.success(TeacherMapper.toDto(teacher));
    }

    @Operation(summary = "Get all teachers", description = "Returns a list of all teachers.")
    @ApiResponse(responseCode = "200", description = "List of teachers returned")
    @GetMapping
    public CustomResponse<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teacherDTOs = teacherService.getAllTeachers()
                .stream()
                .map(TeacherMapper::toDto)
                .collect(Collectors.toList());
        return ApiResponseUtil.success(teacherDTOs);
    }

    @Operation(summary = "Update teacher", description = "Updates an existing teacher by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Teacher updated successfully"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "409", description = "Duplicate email")
    })
    @PutMapping("/{id}")
    public CustomResponse<TeacherDTO> updateTeacher(@PathVariable String id, @Valid @RequestBody TeacherDTO teacherDTO) {
        var updatedTeacher = teacherService.updateTeacher(id, TeacherMapper.toEntity(teacherDTO));
        return ApiResponseUtil.success(TeacherMapper.toDto(updatedTeacher));
    }

    @Operation(summary = "Delete teacher", description = "Deletes a teacher by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Teacher deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @DeleteMapping("/{id}")
    public CustomResponse<Void> deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
        return ApiResponseUtil.success(null);
    }
}
