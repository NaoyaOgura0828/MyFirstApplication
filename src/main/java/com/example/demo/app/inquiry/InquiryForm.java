package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InquiryForm {

    /* 名前入力フォーム */
    @Size(min = 1, max = 20, message = "Please input 20characters or less") // 1文字以上20文字以内かチェックするアノテーション
    private String name;

    /* Emailアドレス入力フォーム */
    @NotNull // nullを許さないアノテーション
    @Email(message = "Invalid Email Format") // Emailアドレスのスタイルかチェックするアノテーション
    private String email;

    /* 問い合わせ内容入力フォーム */
    @NotNull // nullを許さないアノテーション
    private String contents;

    /* コンストラクタ */
    public InquiryForm() {
    }

    /* getter、setter */
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


}
