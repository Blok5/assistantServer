package com.sb.api.kudago.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.sb.api.kudago.model.place.Place;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Serializable {
    private String title;
    @JsonProperty(value = "favorites_count")
    private int favoritesCount;
    private String description;
    private String id;
    private EventDate [] dates;
    @JsonProperty(value = "age_restriction")
    private String ageRestriction;
    private String price;
    @JsonProperty(value = "is_free")
    private boolean isFree;
    private EventImage [] images;
    private Place place;
    private List<Category> categoryList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventDate[] getDates() {
        return dates;
    }

    public void setDates(EventDate[] dates) {
        this.dates = dates;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public EventImage[] getImages() {
        return images;
    }

    public void setImages(EventImage[] images) {
        this.images = images;
    }

    @JsonIgnore
    public boolean applyFilters(Date dateFrom, Date dateTo, Location location, String isFree, Category categories){
        boolean dateFromVal=false;
        boolean dateToVal=false;
        boolean locationVal=false;
        boolean isFreeVal=false;
        boolean categoriesVal=false;

        if(dateFrom== null){
            dateFromVal=true;
        }else{
            if(getDates()!=null){
                for(EventDate date:getDates()){
                    if(dateFrom.getTime()/1000L> Long.parseLong(date.getStart())){
                        dateFromVal=true;
                        break;
                    }
                }
            }
        }
        if(dateTo== null){
            dateToVal=true;
        }else{
            if(getDates()!=null){
                for(EventDate date:getDates()){
                    if(dateTo.getTime()/1000L< Long.parseLong(date.getEnd())){
                        dateToVal=true;
                        break;
                    }
                }
            }
        }

        //todo location parse
       /* if(location==null){
            locationVal=true;
        }else if(location.equals(this.)){

        } */

        //todo category parse
        if(categories==null){
            categoriesVal=true;
        }else if(categoryList!=null){
            for (Category cat: categoryList){
                if(cat.equals(categories)){
                    categoriesVal=true;
                }
            }
        }

       if(isFree==null){
           isFreeVal=true;
       }else if(Boolean.parseBoolean(isFree)==isFree()){
           isFreeVal=true;
       }
        return (dateFromVal && dateToVal && categoriesVal && isFreeVal);
    }

    @JsonSetter("categories")
    public void deserializeCategories(List<String> source){
        categoryList=null;
        for(String sourceVal:source){
            try {
                Category cat=Category.fromApiVal(sourceVal);
                if(categoryList==null){
                    categoryList=new ArrayList<>();
                }
                categoryList.add(cat);
            }catch (IllegalArgumentException e){
                // пропускаем
            }
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categories) {
        this.categoryList = categories;
    }


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public EventDate getFirstDateAfterToday(){
        if(dates==null){
            return null;
        }
        long todayUnix=new Date().getTime()/1000L;
        for(EventDate eventDate: dates){
            if(Long.parseLong(eventDate.getEndPlain())>=todayUnix
                    && (Long.parseLong(eventDate.getStartPlain())>=todayUnix ||
                    (LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(eventDate.getStartPlain())),
                            TimeZone.getDefault().toZoneId()).getDayOfMonth()!=
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(eventDate.getEndPlain())),
                                    TimeZone.getDefault().toZoneId()).getDayOfMonth() ||
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(eventDate.getStartPlain())),
                                    TimeZone.getDefault().toZoneId()).getMonthValue()!=
                                    LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(eventDate.getEndPlain())),
                                            TimeZone.getDefault().toZoneId()).getMonthValue()))){
                return eventDate;
            }
        }
        return null;
    }
}
