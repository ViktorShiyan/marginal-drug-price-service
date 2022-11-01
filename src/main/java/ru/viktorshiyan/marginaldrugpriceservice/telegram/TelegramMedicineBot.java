package ru.viktorshiyan.marginaldrugpriceservice.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;
import ru.viktorshiyan.marginaldrugpriceservice.services.MedicineService;

import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class TelegramMedicineBot extends TelegramLongPollingBot {

    private final static String botName = "medicine_price_bot";
    @Value("${bot.token}")
    private String botToken;
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
            String text = update.getMessage().getText();
            String[] s = text.split(" ");
            Set<MedicineDto> medicineAtBeginWord = medicineService.getMedicineAtBeginWordWithFormWithManufacture(s[0], s[1], s[2]);
            Set<StringJoiner> stringJoiners = medicineAtBeginWord.stream()
                    .map(medicineDto -> new StringJoiner("\n\n")
                            .add(medicineDto.getMnn() + " " + medicineDto.getTradeName())
                            .add(medicineDto.getDosageForm())
                            .add(medicineDto.getManufacturer())
                            .add(medicineDto.getLimitPriceWithoutVat()))
                    .collect(Collectors.toSet());
            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            for (StringJoiner stringJoiner : stringJoiners) {
                try {
                    message.setText(stringJoiner.toString());
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
