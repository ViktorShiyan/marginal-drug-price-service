package ru.viktorshiyan.marginaldrugpriceservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;

import static ru.viktorshiyan.marginaldrugpriceservice.util.Constants.GROUP_ID;
import static ru.viktorshiyan.marginaldrugpriceservice.util.Constants.TOPIC_MEDICINE;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    private final MedicineService medicineService;

    @KafkaListener(topics = TOPIC_MEDICINE, groupId = GROUP_ID, containerFactory = "medicineKafkaListenerFactory")
    public void listenGroupFoo(MedicineDto message) {
        log.info("Receive message: message = {}", message);
        medicineService.createMedicine(message);
    }
}


