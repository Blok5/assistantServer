package ru.sberbank.assistant.model.pulse;

import java.time.LocalDate;

public class ProfilePulse {
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String[] skills;
    private String[] interests;
    private SocialPulse[] social;
    private String imageUrl;

    private ProfilePulse(Builder builder) {
        setName(builder.name);
        setDateOfBirth(builder.dateOfBirth);
        setAddress(builder.address);
        setSkills(builder.skills);
        setInterests(builder.interests);
        setSocial(builder.social);
        setImageUrl(builder.imageUrl);
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public SocialPulse[] getSocial() {
        return social;
    }

    public void setSocial(SocialPulse[] social) {
        this.social = social;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static final class Builder {
        private String name;
        private LocalDate dateOfBirth;
        private String address;
        private String[] skills;
        private String[] interests;
        private SocialPulse[] social;
        private String imageUrl;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder dateOfBirth(LocalDate val) {
            dateOfBirth = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder skills(String[] val) {
            skills = val;
            return this;
        }

        public Builder interests(String[] val) {
            interests = val;
            return this;
        }

        public Builder social(SocialPulse[] val) {
            social = val;
            return this;
        }

        public Builder imageUrl(String val) {
            imageUrl = val;
            return this;
        }

        public ProfilePulse build() {
            return new ProfilePulse(this);
        }
    }
}
