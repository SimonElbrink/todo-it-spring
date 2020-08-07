package se.lexicon.todoitspring.dto;

import se.lexicon.todoitspring.entity.AppUser;
import java.time.LocalDateTime;

public class TodoItemDto {

    private String id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private boolean done;
    private AppUser Assignee;

    public TodoItemDto() {
    }

    public TodoItemDto(String id, String title, String description, LocalDateTime deadline, boolean done, AppUser assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        Assignee = assignee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public AppUser getAssignee() {
        return Assignee;
    }

    public void setAssignee(AppUser assignee) {
        Assignee = assignee;
    }
}
