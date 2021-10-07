package ru.sberbank.assistant.model.pulse;

public class GrowthPulse {
    private String name;
    private String description;
    private long duration;
    private String [] tags;
    private String type;
    private double rating;
    private int numOfViews;
    private String imageUrl;

    private GrowthPulse(Builder builder) {
        setName(builder.name);
        setDescription(builder.description);
        setDuration(builder.duration);
        setTags(builder.tags);
        setType(builder.type);
        setRating(builder.rating);
        setNumOfViews(builder.numOfViews);
        setImageUrl(builder.imageUrl);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumOfViews() {
        return numOfViews;
    }

    public void setNumOfViews(int numOfViews) {
        this.numOfViews = numOfViews;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static final class Builder {
        private String name;
        private String description;
        private long duration;
        private String[] tags;
        private String type;
        private double rating;
        private int numOfViews;
        private String imageUrl;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder duration(long val) {
            duration = val;
            return this;
        }

        public Builder tags(String[] val) {
            tags = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder rating(double val) {
            rating = val;
            return this;
        }

        public Builder rating(int val) {
            rating = val;
            return this;
        }

        public Builder numOfViews(int val) {
            numOfViews = val;
            return this;
        }

        public Builder imageUrl(String val) {
            imageUrl = val;
            return this;
        }

        public GrowthPulse build() {
            return new GrowthPulse(this);
        }
    }
}
