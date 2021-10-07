package ru.sberbank.assistant.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sberbank.assistant.model.Place;
import ru.sberbank.assistant.ref.EventType;
import ru.sberbank.assistant.ref.Source;

@Component
public class PlaceToRouteEventConverter implements Converter<doublegis.model.place.Place, ru.sberbank.assistant.model.Event> {


    @Override
    public ru.sberbank.assistant.model.Event convert(doublegis.model.place.Place event) {

        return new ru.sberbank.assistant.model.Event.Builder()
                .type(EventType.CAFE)
                .externalId(Long.parseLong(event.getId()))
                .source(Source.doubleGis)
//                .price(event.getPrice())
                .imageUrl((event.getImage()!=null)?event.getImage().getImageUrl():"")
                .name(event.getName())
                .rating(event.getRating()!=null?event.getRating().getRating():0d)
                .place(new Place.Builder()
                        .externalId(Long.parseLong(event.getId()))
                        .lat(event.getPoint().getLat())
                        .lon(event.getPoint().getLon())
                        .address(event.getAddress())
                        .source(Source.doubleGis)
                        .build())
                .build();
    }
}
