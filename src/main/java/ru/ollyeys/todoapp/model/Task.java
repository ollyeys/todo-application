package ru.ollyeys.todoapp.model;


public class Task {
    private Long id;
    private String title;

    private String description;
    private Long user_id;

    protected Task() {

    }

    public Task(Long id, String title, String description, Long user_id) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.user_id = user_id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getByUserId() {
        return user_id;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
