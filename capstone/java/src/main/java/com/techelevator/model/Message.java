package com.techelevator.model;

import java.time.LocalDateTime;


public class Message {

    public int userId;
    public int bandId;
    public String message;
    public LocalDateTime date;
    public int messageId;

public Message() {

}

    public Message(int userId, int bandId, String message, LocalDateTime date, int messageId) {
        this.userId = userId;
        this.bandId = bandId;
        this.message = message;
        this.date = date;
        this.messageId = messageId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
