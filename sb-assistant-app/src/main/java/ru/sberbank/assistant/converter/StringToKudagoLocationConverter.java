package ru.sberbank.assistant.converter;

import com.sb.api.kudago.model.ref.Location;
import org.springframework.core.convert.converter.Converter;

public class StringToKudagoLocationConverter implements Converter<String, Location> {

        @Override
        public Location convert(String source) {
            return Location.fromApiVal(source);
        }


}

