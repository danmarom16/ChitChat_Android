package com.example.chitchat.javaclasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ApiTypeMessage {
    private String id;
    private String content;
    private String created;
    private boolean sent;

    public ApiTypeMessage(String id, String content, String created, boolean sent) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public static String fTime(String toConvert) { // must be of type: "yyyy-MM-ddTHH:mm:ss.fffffff"
        if (toConvert.length() > 16) {
            return toConvert.substring(11, 16);
        }
        return toConvert;
    }

    public static void formatList(List<ApiTypeMessage> messages){
        for (ApiTypeMessage m: messages){
            m.setCreated(fTime(m.getCreated()));
        }
    }

    public static ApiTypeMessage createSenderMessage(String content) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        String currentTime = timeFormat.format(new Date());
        return new ApiTypeMessage("0", content, currentTime, true);
    }
}
