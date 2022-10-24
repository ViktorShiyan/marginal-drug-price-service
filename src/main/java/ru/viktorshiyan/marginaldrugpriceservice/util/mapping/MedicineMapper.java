package ru.viktorshiyan.marginaldrugpriceservice.util.mapping;

import org.mapstruct.Mapper;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;
import ru.viktorshiyan.marginaldrugpriceservice.models.Medicine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    Medicine dtoToEntity(MedicineDto medicineDto);

    MedicineDto entityToDto(Medicine medicine);

    default LocalDate stringToLocalDate(String stringDate) {
        return LocalDate.parse(stringDate, formatter);
    }

    default String localDateToString(LocalDate stringDate) {
        return stringDate.format(formatter);
    }
}
