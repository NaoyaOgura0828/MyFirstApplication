package com.example.demo.app.survey;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SurveyForm {

    /* 年齢入力フォーム */
    @Min(0)
    @Max(150)
    private int age;

    /* 満足度入力ボタン */
    @Min(1)
    @Max(5)
    private int satisfaction;

    /* コメント入力フォーム */
    @NotNull
    @Size(min = 1, max = 200, message = "Please input 200characters or less")
    private String comment;


    /* コンストラクタ */
    public SurveyForm() {
    }


    /* getter、setter */
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
