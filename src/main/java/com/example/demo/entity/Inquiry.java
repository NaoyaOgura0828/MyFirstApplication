package com.example.demo.entity;

import java.time.LocalDateTime;

public class Inquiry {

    /* データベーステーブルとの紐付け */
    private int id; // ID
    private String name; // 名前
    private String email; // Email
    private String contents; // 問い合わせ内容
    private LocalDateTime created; // 投稿時間


    /* コンストラクタ */
    public Inquiry() {
    }


    /* getter、setter */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
