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
        Set<Medicine> medicinesByMnnStartingWith = medicineRepository.getMedicinesByMnnStartingWith(beginWord);
        Set<MedicineDto> medicineDtos = medicinesByMnnStartingWith
                .stream().map(medicineMapper::entityToDto).collect(Collectors.toSet());
        log.info("Finish  getMedicineAtBeginWord: result = {}", medicineDtos);
        return medicineDtos;
    }
}
