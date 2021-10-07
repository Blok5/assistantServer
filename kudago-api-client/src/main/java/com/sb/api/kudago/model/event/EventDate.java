package com.sb.api.kudago.model.event;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class EventDate implements Serializable {
    private String start;
    private String end;

    public String getStart() {
        Date today = new Date();
        if(Long.parseLong(start)<today.getTime()/1000L){
            Date roundToday= new Date(today.getYear(),today.getMonth(),today.getDate(),today.getHours()
            , (today.getMinutes()/10)*10);
            Calendar c=Calendar.getInstance();
            c.setTime(roundToday);
            c.add(Calendar.HOUR,2);
            return String.valueOf(c.getTime().getTime()/1000L);
        }
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStartPlain() {
        return start;
    }

    public String getEndPlain() {
        return end;
    }

    public String getEnd() {

        Date todayStart=Date.from(Instant.ofEpochSecond(Long.parseLong(getStart())));
        Calendar c=Calendar.getInstance();
        c.setTime(todayStart);
        c.add(Calendar.HOUR,2);

        if(LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(getStart())),
                TimeZone.getDefault().toZoneId()).getDayOfMonth()!=
                LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(end)),
                TimeZone.getDefault().toZoneId()).getDayOfMonth() ||
                LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(getStart())),
                TimeZone.getDefault().toZoneId()).getMonthValue()!=
                LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(end)),
                        TimeZone.getDefault().toZoneId()).getMonthValue()){

            return String.valueOf(c.getTime().getTime()/1000L);
        }else{
            if(start.equals(end)){
                return String.valueOf(c.getTime().getTime()/1000L);
            }
        }
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
