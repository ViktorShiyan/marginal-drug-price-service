package ru.viktorshiyan.marginaldrugpriceservice.reposytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorshiyan.marginaldrugpriceservice.models.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {
}