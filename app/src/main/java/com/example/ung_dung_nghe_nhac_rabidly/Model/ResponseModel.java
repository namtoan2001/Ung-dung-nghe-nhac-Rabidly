package com.example.ung_dung_nghe_nhac_rabidly.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;


    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
