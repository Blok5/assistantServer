package ru.sberbank.assistant.converter;

import org.springframework.core.convert.converter.Converter;
import ru.sberbank.assistant.ref.PlaceType;

public class StringToPlaceTypeConverter implements Converter<String, PlaceType> {

    @Override
    public PlaceType convert(String source) {
        return PlaceType.fromFrontVal(source);
    }


}

