package ru.sberbank.assistant.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sberbank.assistant.model.Place;
import ru.sberbank.assistant.ref.EventType;
import ru.sberbank.assistant.ref.Source;

import java.util.Random;

@Component
public class PlaceToRouteEventConverter implements Converter<doublegis.model.place.Place, ru.sberbank.assistant.model.Event> {

    private final Random random = new Random();

    @Override
    public ru.sberbank.assistant.model.Event convert(doublegis.model.place.Place event) {

        int cost = 500 * (3 + random.nextInt(5 + 1));
        String averageCost = "от " + cost + " рублей";


        return new ru.sberbank.assistant.model.Event.Builder()
                .type(EventType.CAFE)
                .externalId(Long.parseLong(event.getId()))
                .source(Source.doubleGis)
                .price(averageCost)
                .imageUrl((event.getImage() != null) ? event.getImage().getImageUrl() : "")
                .name(event.getName())
                .rating(event.getRating() != null ? event.getRating().getRating() : 0d)
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
