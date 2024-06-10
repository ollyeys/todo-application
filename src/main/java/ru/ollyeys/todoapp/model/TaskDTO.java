package ru.ollyeys.todoapp.model;



import java.sql.SQLException;
import java.time.LocalDate;

public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer userId;

    private LocalDate targetDate;
    private boolean taskStatus;


    public TaskDTO() {
    }

    public TaskDTO(Integer id, String title, String description, Integer userId, LocalDate targetDate, boolean taskStatus) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.taskStatus = taskStatus;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUser_id() {
        return userId;
    }

    public void setUser_id(Integer user_id) {
        this.userId = userId;
    }

    public Integer getByUserId() {
        return userId;
    }








//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + (int)(id ^ (id >>> 32));
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Task other = (Task) obj;
//        if (id != other.id)
//            return false;
//        return true;
//    }

}
