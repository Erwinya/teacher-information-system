package com.RestfulApi.TeacherInformationSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String surname;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @Size(max = 30)
    private String phoneNumber;

    @Size(max = 100)
    private String department;
}
