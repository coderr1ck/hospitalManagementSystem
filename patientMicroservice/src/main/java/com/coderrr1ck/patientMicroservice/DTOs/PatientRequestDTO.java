package com.coderrr1ck.patientMicroservice.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {
        @NotBlank(message = "Name is required.")
        private String name;
        @NotBlank(message = "Email is required.")
        @Email(message = "Email is not valid.")
        private String email;
        @NotBlank(message = "Address is required.")
        private String address;
        @NotBlank(message = "Date of Birth is required.")
        private String dateOfBirth;
        @NotBlank(groups = CreatePatientValidationGroup.class,message = "Registered date is required.")
        private String registeredDate;
}
