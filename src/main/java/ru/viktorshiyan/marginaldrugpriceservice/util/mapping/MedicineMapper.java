package ru.viktorshiyan.marginaldrugpriceservice.util.mapping;

import org.mapstruct.Mapper;
import ru.viktorshiyan.marginaldrugpriceservice.dto.MedicineDto;
import ru.viktorshiyan.marginaldrugpriceservice.models.Medicine;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    Medicine dtoToEntity(MedicineDto medicineDto);
}
