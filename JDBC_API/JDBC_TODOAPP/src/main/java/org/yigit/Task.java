package org.yigit;

public class Task {
    private int id;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\nTask" +
                "\nId= " + id +
                "\nName= " + name;
    }
}
