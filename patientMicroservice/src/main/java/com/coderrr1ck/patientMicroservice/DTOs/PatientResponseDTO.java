package com.coderrr1ck.patientMicroservice.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
    @NotNull
    private String patient_id;
    @NotNull
    private String patient_name;
    @Email
    private String patient_email;
    @NotNull
    private String patient_address;
    @NotNull
    private String patient_dob;
}
