package br.com.vlemes.osworks.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionMessage {
    private Integer status;
    private LocalDateTime dateTime;
    private String title;
    private ArrayList<FieldErrorMessage> fieldErrorMessages = new ArrayList<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<FieldErrorMessage> getFieldErrorMessages() {
        return fieldErrorMessages;
    }

    public void setFieldErrorMessages(ArrayList<FieldErrorMessage> fieldErrorMessages) {
        this.fieldErrorMessages = fieldErrorMessages;
    }
}
