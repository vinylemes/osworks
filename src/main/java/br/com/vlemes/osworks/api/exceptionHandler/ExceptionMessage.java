package br.com.vlemes.osworks.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionMessage {
    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private ArrayList<FieldErrorMessage> fieldErrorMessages;

    public ExceptionMessage(Integer status, OffsetDateTime dateTime, String title, ArrayList<FieldErrorMessage> fieldErrorMessages) {
        this.status = status;
        this.dateTime = dateTime;
        this.title = title;
        this.fieldErrorMessages = fieldErrorMessages;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
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
