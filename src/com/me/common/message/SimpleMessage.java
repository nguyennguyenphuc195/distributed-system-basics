package com.me.common.message;

public class SimpleMessage implements java.io.Serializable {
    private int id;
    private int timestamp;
    private String type;
    private String content;

    public SimpleMessage(int id, int timestamp, String type, String content) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
