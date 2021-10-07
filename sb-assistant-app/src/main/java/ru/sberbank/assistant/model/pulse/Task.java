package ru.sberbank.assistant.model.pulse;

import java.time.LocalDateTime;

public class Task {
    private Task(Builder builder) {
        setName(builder.name);
        setDescription(builder.description);
        setAssignee(builder.assignee);
        setDeadline(builder.deadline);
        setTags(builder.tags);
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

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    private String name;
    private String description;
    private String assignee;
    private LocalDateTime deadline;
    private String[] tags;

    public static final class Builder {
        private String name;
        private String description;
        private String assignee;
        private LocalDateTime deadline;
        private String[] tags;

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

        public Builder assignee(String val) {
            assignee = val;
            return this;
        }

        public Builder deadline(LocalDateTime val) {
            deadline = val;
            return this;
        }

        public Builder tags(String[] val) {
            tags = val;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
