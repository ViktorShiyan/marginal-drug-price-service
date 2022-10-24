package ru.viktorshiyan.marginaldrugpriceservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;
import ru.viktorshiyan.marginaldrugpriceservice.services.MedicineService;

import java.util.Set;

@RestController
@RequestMapping("/medicine")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    public Set<MedicineDto> getMedicineStartWith(@RequestParam String start) {
        return medicineService.getMedicineAtBeginWord(start);
    }
}
