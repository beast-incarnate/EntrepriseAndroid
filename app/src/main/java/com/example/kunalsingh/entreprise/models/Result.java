package com.example.kunalsingh.entreprise.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by kunalsingh on 11/06/17.
 */

public class Result {

    @SerializedName("data")
    @Expose
    private HashMap<String,String> data;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("disabled")
    @Expose
    private String disabled;

    @SerializedName("update")
    @Expose
    private String update;

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getUpdate() {

        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getDisabled() {
        return disabled;
    }
}
