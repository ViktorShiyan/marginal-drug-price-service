package ru.viktorshiyan.marginaldrugpriceservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;
import ru.viktorshiyan.marginaldrugpriceservice.models.Medicine;
import ru.viktorshiyan.marginaldrugpriceservice.reposytory.MedicineRepository;
import ru.viktorshiyan.marginaldrugpriceservice.util.mapping.MedicineMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicineService {

    final private MedicineMapper medicineMapper;

    final private MedicineRepository medicineRepository;

    public Medicine createMedicine(MedicineDto medicineDto) {
        log.info("Start create Medicine: dto = {}", medicineDto);
        Medicine medicine = medicineMapper.dtoToEntity(medicineDto);
        Medicine saved = medicineRepository.save(medicine);
        log.info("Finish create Medicine: entity = {}", saved);
        return saved;
    }

    public Set<MedicineDto> getMedicineAtBeginWord(String beginWord) {
        log.info("Start  getMedicineAtBeginWord: beginWord = {}", beginWord);
        Set<Medicine> medicinesByMnnStartingWith = medicineRepository.getMedicinesByMnnIgnoreCaseStartingWith(beginWord);
        Set<MedicineDto> medicineDtos = medicinesByMnnStartingWith.stream().map(medicineMapper::entityToDto).collect(Collectors.toSet());
        log.info("Finish  getMedicineAtBeginWord: result = {}", medicineDtos);
        return medicineDtos;
    }

    public Set<MedicineDto> getMedicineAtBeginWordWithForm(String beginWord, String form) {
        log.info("Start  getMedicineAtBeginWordWithForm: beginWord = {}", beginWord);
        Set<Medicine> medicinesByMnnStartingWith = medicineRepository.getMedicinesByMnnIgnoreCaseStartingWithAndDosageFormContaining(beginWord, form);
        Set<MedicineDto> medicineDtos = medicinesByMnnStartingWith.stream().map(medicineMapper::entityToDto).collect(Collectors.toSet());
        log.info("Finish  getMedicineAtBeginWordWithForm: result = {}", medicineDtos);
        return medicineDtos;

    }

    /**
     * @param beginWord   начало названия препората
     * @param form        форма выпуска
     * @param manufacture производитель
     * @return множество препоратов
     */
    public Set<MedicineDto> getMedicineAtBeginWordWithFormWithManufacture(String beginWord, String form, String manufacture) {
        log.info("Start  getMedicineAtBeginWordWithFormWithManufacture: beginWord = {}", beginWord);
        Set<Medicine> medicinesByMnnStartingWith = medicineRepository.getMedicinesByMnnIgnoreCaseStartingWithAndDosageFormContainingIgnoreCaseAndManufacturerContainingIgnoreCase(beginWord, form, manufacture);
        Set<MedicineDto> medicineDtos = medicinesByMnnStartingWith.stream().map(medicineMapper::entityToDto).collect(Collectors.toSet());
        log.info("Finish  getMedicineAtBeginWordWithFormWithManufacture: result = {}", medicineDtos);
        return medicineDtos;

    }
}
