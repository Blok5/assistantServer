package ru.sberbank.assistant.model.pulse;

import java.time.LocalDate;
import java.util.List;

public class CalendarPulse {
    private List<MeetingPulse> meetings;
    private LocalDate date;

    private CalendarPulse(Builder builder) {
        setMeetings(builder.meetings);
        setDate(builder.date);
    }

    public List<MeetingPulse> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingPulse> meetings) {
        this.meetings = meetings;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static final class Builder {
        private List<MeetingPulse> meetings;
        private LocalDate date;

        public Builder() {
        }

        public Builder meetings(List<MeetingPulse> val) {
            meetings = val;
            return this;
        }

        public Builder date(LocalDate val) {
            date = val;
            return this;
        }

        public CalendarPulse build() {
            return new CalendarPulse(this);
        }
    }
}
