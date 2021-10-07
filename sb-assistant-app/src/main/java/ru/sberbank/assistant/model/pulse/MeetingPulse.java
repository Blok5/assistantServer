package ru.sberbank.assistant.model.pulse;

import java.time.LocalDateTime;

public class MeetingPulse {
    private MeetingPulse(Builder builder) {
        setPlace(builder.place);
        setTime(builder.time);
        setName(builder.name);
        setDescription(builder.description);
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    private String place;
    private LocalDateTime time;

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

    private String name;
    private String description;

    public static final class Builder {
        private String place;
        private LocalDateTime time;
        private String name;
        private String description;


        public Builder() {
        }

        public Builder place(String val) {
            place = val;
            return this;
        }

        public Builder time(LocalDateTime val) {
            time = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public MeetingPulse build() {
            return new MeetingPulse(this);
        }
    }
}
