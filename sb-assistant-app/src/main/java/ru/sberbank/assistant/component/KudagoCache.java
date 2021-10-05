package ru.sberbank.assistant.component;

import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.model.event.Event;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class KudagoCache {

    private final KudagoClient kudagoClient;
    private CopyOnWriteArrayList<Event> eventCurrentList;
    private CopyOnWriteArrayList<Event> eventNewList;

    public KudagoCache(KudagoClient kudagoClient) {
        this.kudagoClient = kudagoClient;
    }

    public CopyOnWriteArrayList<Event> getEventCurrentList() {
        return eventCurrentList;
    }

    public void setEventCurrentList(CopyOnWriteArrayList<Event> eventCurrentList) {
        this.eventCurrentList = eventCurrentList;
    }

    public CopyOnWriteArrayList<Event> getEventNewList() {
        return eventNewList;
    }

    public void setEventNewList(CopyOnWriteArrayList<Event> eventNewList) {
        if(this.getEventNewList()==null) {
            this.eventNewList=eventNewList;
        }else {
            appendEventNewList(eventNewList);
        }
    }

    public void appendEventNewList(CopyOnWriteArrayList<Event> eventNewList) {
        this.eventNewList.addAll(eventNewList);
    }

    public Event lucky(){
        if(hasCache()){
            double luckyPos= Math.random()*eventCurrentList.size();
            return eventCurrentList.get((int) luckyPos);
        }
        return kudagoClient.lucky();

    }

    public boolean hasCache(){
        if(eventCurrentList!= null && eventCurrentList.size()>0){
            return true;
        }
        return false;
    }



    public CopyOnWriteArrayList<Event> searchEvents(Date dateFrom, Date dateTo, Location location, String isFree, Category categories){
            if(hasCache()) {
                return eventCurrentList.stream().filter(p -> p.applyFilters(dateFrom, dateTo, location, isFree, categories)).collect(Collectors.toCollection(CopyOnWriteArrayList::new));

            }
            return new CopyOnWriteArrayList<>(
                    kudagoClient.searchEvents(dateTo,dateFrom,location,isFree,categories,20));
    }

}
