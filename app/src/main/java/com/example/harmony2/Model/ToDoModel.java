package com.example.harmony2.Model;

public class ToDoModel extends TaskID {
    private String task, due;
    private int status;

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
