package com.coderrr1ck.patientMicroservice.Service;

import com.coderrr1ck.patientMicroservice.DTOs.PatientRequestDTO;
import com.coderrr1ck.patientMicroservice.DTOs.PatientResponseDTO;
import com.coderrr1ck.patientMicroservice.Exception.ServiceLayerExceptionHandler;
import com.coderrr1ck.patientMicroservice.Model.Patient;
import com.coderrr1ck.patientMicroservice.Repository.PatientRepository;
import com.coderrr1ck.patientMicroservice.mapper.PatientObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientService {
//    custom validators are written here.
// this is where you write business logic code , seperation of concerns.
//    using final ensures that it is immutable and we are using constructor injection, so dependency will be satisfied.
    private final PatientRepository patientRepository;
    private final String MESSAGE = "message";
    private Map errors = new HashMap<>();

    PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
//        System.out.println(patients);
        List<PatientResponseDTO> patients_list=  patients.stream()
                .map(patient -> PatientObjectMapper.toDTO(patient)).toList();
        return patients_list;
    }

    public PatientResponseDTO createNewPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            errors.put(MESSAGE,"Email address already exists.");
            throw new ServiceLayerExceptionHandler(errors);
        }
        Patient patient_new = patientRepository.save(
                PatientObjectMapper.toEntityModel(patientRequestDTO)
        );
        PatientResponseDTO response = PatientObjectMapper.toDTO(patient_new);
        return response;
    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO) {
        Optional<Patient> patient_exist = patientRepository.findById(id);
        if(patient_exist.isPresent()){
            Patient newPatient = patient_exist.get();
            if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
                errors.put(MESSAGE,"Patient email is already taken");
                throw new ServiceLayerExceptionHandler(errors);
            }
            newPatient.setName(patientRequestDTO.getName());
            newPatient.setEmail(patientRequestDTO.getEmail());
            newPatient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
            newPatient.setAddress(patientRequestDTO.getAddress());
            Patient updated_patient = patientRepository.save(newPatient);
            return  PatientObjectMapper.toDTO(updated_patient);
        }else {
            errors.put(MESSAGE,"Patient not found");
            throw new ServiceLayerExceptionHandler(errors);
        }
    }

    public void deletePatient(UUID id){
        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
        }else{
            errors.put(MESSAGE,"User not found.");
            throw new ServiceLayerExceptionHandler(errors);
        }
    }


}
