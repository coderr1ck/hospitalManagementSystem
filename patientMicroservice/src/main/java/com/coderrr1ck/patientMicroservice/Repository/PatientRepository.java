package com.coderrr1ck.patientMicroservice.Repository;

import com.coderrr1ck.patientMicroservice.Model.Patient;
//import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

//    this is manage by jpa and provides out of the box querying capabilities around the entity,
//    if we want customization we can write our own methods in this to get what we want .
//    we can also use spring derived queries which automatically creates sql equivalent queries for us
      boolean existsByEmail(String email);
      boolean existsByEmailAndIdNot(String email,UUID id);
}
