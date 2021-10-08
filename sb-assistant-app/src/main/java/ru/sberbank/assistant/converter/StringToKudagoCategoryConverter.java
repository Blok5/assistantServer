package ru.sberbank.assistant.converter;

import com.sb.api.kudago.model.ref.Category;
import org.springframework.core.convert.converter.Converter;

public class StringToKudagoCategoryConverter implements Converter<String, Category> {

    @Override
    public Category convert(String source) {
        return Category.fromApiVal(source);
    }


}

