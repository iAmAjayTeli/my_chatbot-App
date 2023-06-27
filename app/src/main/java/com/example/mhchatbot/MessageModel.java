package com.example.mhchatbot;

public class MessageModel {
    public static String Sent_By_Me="me";
   public static String Sent_By_Bot="Bot";
    String message;
    String SentBy;

    public MessageModel(String message, String sentBy) {
        this.message = message;
        SentBy = sentBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return SentBy;
    }

    public void setSentBy(String sentBy) {
        SentBy = sentBy;
    }
}
