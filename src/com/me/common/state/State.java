package com.me.common.state;

public class State {
    private int id;
    private int timestamp;

    public State(int id, int timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    synchronized public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}
