package ru.ollyeys.todoapp.model;


import java.time.LocalDate;
import java.util.Date;

public class Task {

    private String taskTitle;

    private String taskDescription;
    private Integer userId;
    private LocalDate targetDate;
    private boolean taskStatus;

    protected Task() {

    }

    public Task(String taskTitle, String taskDescription, Integer userId, LocalDate targetDate, boolean taskStatus) {
        super();
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.userId = userId;
        this.targetDate = targetDate;
        this.taskStatus = taskStatus;
    }




    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }


    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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
