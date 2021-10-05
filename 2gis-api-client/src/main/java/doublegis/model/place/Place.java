package doublegis.model.place;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import doublegis.model.place.schedule.Schedule;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place implements Serializable {
    private String id;
    @JsonProperty(value = "address_name")
    private String address;
    @JsonProperty(value = "address_comment")
    private String addressComment;
    private String name;
    private PlaceImage image;
    private Point point;
    @JsonProperty(value = "reviews")
    private PlaceRating rating;
    private Schedule schedule;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressComment() {
        return addressComment;
    }

    public void setAddressComment(String addressComment) {
        this.addressComment = addressComment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaceImage getImage() {
        return image;
    }

    public void setImage(PlaceImage image) {
        this.image = image;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public PlaceRating getRating() {
        return rating;
    }

    public void setRating(PlaceRating rating) {
        this.rating = rating;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @JsonSetter("external_content")
    public void deserializeImage(List<PlaceImage> source){
        if(source!=null&& source.size()>0){
            image=new PlaceImage();
            image.setImageUrl(source.get(0).getImageUrl());
        }
    }
}
