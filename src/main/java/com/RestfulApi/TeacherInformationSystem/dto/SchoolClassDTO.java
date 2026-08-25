package com.RestfulApi.TeacherInformationSystem.dto;

import java.util.List;
import com.RestfulApi.TeacherInformationSystem.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolClassDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    private Teacher teacher;
    private List<StudentDTO> students;
}
