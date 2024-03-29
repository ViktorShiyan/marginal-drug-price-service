package ru.viktorshiyan.marginaldrugpriceservice.reposytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorshiyan.marginaldrugpriceservice.models.Medicine;

import java.util.Set;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {

    Set<Medicine> getMedicinesByMnnIgnoreCaseStartingWith(String s);

    Set<Medicine> getMedicinesByMnnIgnoreCaseStartingWithAndDosageFormContaining(String name, String form);

    Set<Medicine> getMedicinesByMnnIgnoreCaseStartingWithAndDosageFormContainingIgnoreCaseAndManufacturerContainingIgnoreCase(String name, String form, String manufacturer);
}
