package com.coderrr1ck.patientMicroservice.mapper;


import com.coderrr1ck.patientMicroservice.DTOs.PatientRequestDTO;
import com.coderrr1ck.patientMicroservice.DTOs.PatientResponseDTO;
import com.coderrr1ck.patientMicroservice.Model.Patient;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

public class PatientObjectMapper {
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patient_dto = new PatientResponseDTO();
        patient_dto.setPatient_id(String.valueOf(patient.getId()));
        patient_dto.setPatient_name(String.valueOf(patient.getName()));
        patient_dto.setPatient_dob(String.valueOf(patient.getDateOfBirth()));
        patient_dto.setPatient_address(String.valueOf(patient.getAddress()));
        patient_dto.setPatient_email(String.valueOf(patient.getEmail()));
        return patient_dto;
    }
    public static Patient toEntityModel(PatientRequestDTO patient){
        Patient patient_new = new Patient();
        patient_new.setName(patient.getName());
        patient_new.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));
        patient_new.setAddress(patient.getAddress());
        patient_new.setEmail(patient.getEmail());
        patient_new.setRegisteredDate(LocalDate.parse(patient.getRegisteredDate()));
        return patient_new;
    }
}
