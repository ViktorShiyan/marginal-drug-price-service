package ru.viktorshiyan.marginaldrugpriceservice.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;
import ru.viktorshiyan.marginaldrugpriceservice.services.MedicineService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class TelegramMedicineBot extends TelegramLongPollingBot {

    private final static String botToken = "5731557838:AAFKKSiuCTgeIM3YKJv5ZWvGV0WHeXfkR5A";
    private final static String botName = "medicine_price_bot";

    private final MedicineService medicineService;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            Set<MedicineDto> medicineAtBeginWord = medicineService.getMedicineAtBeginWord(update.getMessage().getText());
            List<String> stringList = medicineAtBeginWord.stream().map(MedicineDto::getDosageForm).distinct().collect(Collectors.toList());
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            stringList.forEach(
                    (string) -> {
                        try {
                            message.setText(string);
                            execute(message);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }
}
