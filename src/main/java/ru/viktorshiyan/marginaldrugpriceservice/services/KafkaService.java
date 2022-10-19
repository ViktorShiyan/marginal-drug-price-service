package ru.viktorshiyan.marginaldrugpriceservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;

import static ru.viktorshiyan.marginaldrugpriceservice.util.Constants.GROUP_ID;
import static ru.viktorshiyan.marginaldrugpriceservice.util.Constants.TOPIC_MEDICINE;

@Service
public class KafkaService {

    @KafkaListener(topics = TOPIC_MEDICINE, groupId = GROUP_ID, containerFactory = "medicineKafkaListenerFactory")
    public void listenGroupFoo(MedicineDto message) {
        System.out.println("Received Message in group foo: " + message.getMnn());
    }
}
