package com.example.acorutas.Data.models;

import com.google.gson.annotations.SerializedName;

public class RegistroUser {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("password")
    private String password;

    @SerializedName("correo")
    private String correo;

    public RegistroUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public RegistroUser(String nickname, String password, String correo) {
        this.nickname = nickname;
        this.password = password;
        this.correo = correo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "RegistroUser{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
