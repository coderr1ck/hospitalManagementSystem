package com.coderrr1ck.patientMicroservice.Controller;

import com.coderrr1ck.patientMicroservice.DTOs.CreatePatientValidationGroup;
import com.coderrr1ck.patientMicroservice.DTOs.PatientRequestDTO;
import com.coderrr1ck.patientMicroservice.DTOs.PatientResponseDTO;
import com.coderrr1ck.patientMicroservice.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/patients")
@Tag(name="Patient Management Api",description = "Api for managing patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    @Operation(summary = "Get list of all patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);
    }
    @PostMapping()
    @Operation(summary = "Add new patient to db")
    public ResponseEntity<PatientResponseDTO> createNewPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO newPatient = patientService.createNewPatient(patientRequestDTO);
        return ResponseEntity.ok().body(newPatient);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a patient in db")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable(name = "id") UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO updatedPatient = patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(updatedPatient);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient from db")
    public ResponseEntity<Void> deletePatient(@PathVariable(name = "id") UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
