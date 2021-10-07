package ru.sberbank.assistant.converter;

import com.sb.api.kudago.model.event.Event;
import com.sb.api.kudago.model.event.EventDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sberbank.assistant.model.Place;
import ru.sberbank.assistant.ref.Source;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
public class EventToRouteEventConverter implements Converter<Event, ru.sberbank.assistant.model.Event> {



    @Override
    public ru.sberbank.assistant.model.Event convert(Event event) {
        EventDate date=event.getFirstDateAfterToday();
        return new ru.sberbank.assistant.model.Event.Builder()
                .dateStart((event.getDates()!=null&& event.getDates().length>0)?
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(date.getStart())),
                                TimeZone.getDefault().toZoneId()):null)
                .dateEnd((event.getDates()!=null&& event.getDates().length>0)?
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(date.getEnd())),
                                TimeZone.getDefault().toZoneId()):null)
                .externalId(Long.parseLong(event.getId()))
                .source(Source.kudago)
                .ageRestriction(event.getAgeRestriction())
                .price(event.getPrice())
                .imageUrl((event.getImages()!=null && event.getImages().length>0)?event.getImages()[0].getImageUrl():
                        "")
                .name(event.getTitle())
                .place(new Place.Builder()
                        .externalId((event.getPlace().getId()!=null)?Long.parseLong(event.getPlace().getId()):1L)
                        .lat(event.getPlace().getCoords().getLat())
                        .lon(event.getPlace().getCoords().getLon())
                        .address(event.getPlace().getAddress())
                        .source(Source.kudago)
                .build())
                .build();
    }
}
