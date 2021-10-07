package ru.sberbank.assistant.model.pulse;

public class SocialPulse {
    private String name;
    private String link;

    private SocialPulse(Builder builder) {
        setName(builder.name);
        setLink(builder.link);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static final class Builder {
        private String name;
        private String link;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder link(String val) {
            link = val;
            return this;
        }

        public SocialPulse build() {
            return new SocialPulse(this);
        }
    }
}
