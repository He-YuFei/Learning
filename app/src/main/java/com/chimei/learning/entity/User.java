package com.chimei.learning.entity;

import com.google.gson.annotations.SerializedName;

public class User {
    private int id;
    private String username;
    /**
     * 如果服务器返回的 json 数据名称和实体类不符, 可通过 SerializedName 注解解决.
     */
    @SerializedName("pwd")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
