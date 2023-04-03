package test.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("tasks")
public class Task {
    @Id
    private String id;

    @Field
    private String text;

    @Field
    private String day;

    @Field
    private Boolean reminder;

    public Task() { }

    public Task(String text, String day, Boolean reminder) {
        this.text = text;
        this.day = day;
        this.reminder = reminder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getReminder() {
        return reminder;
    }

    public void setReminder(Boolean reminder) {
        this.reminder = reminder;
    }

    public String toString() {
        return "Task(text='%s', day='%s', reminder='%b')".format(text, day, reminder);
    }
}
