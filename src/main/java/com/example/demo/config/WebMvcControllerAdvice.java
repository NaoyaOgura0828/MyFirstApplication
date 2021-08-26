package com.example.demo.config;

import com.example.demo.service.InquiryNotFoundException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * 全てのControllerで共通処理を定義
 */
@ControllerAdvice
public class WebMvcControllerAdvice {

    /*
     * This method changes empty character to null
     * こちらのメソッドを用意しておくと送信された空文字はnullに変換されます
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /* 1つもSQLのレコードが見つからない場合の例外処理 */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleException(EmptyResultDataAccessException e, Model model) {
            model.addAttribute("message", e);
            return "error/CustomPage";
    }

    /* 当該のSQLのレコードが見つからない場合の例外処理 */
    /** 全てのController内での同じ名称の例外に対して共通処理出来る */
    @ExceptionHandler(InquiryNotFoundException.class)
    public String handleException(InquiryNotFoundException e, Model model) {
        model.addAttribute("message", e);
        return "error/CustomPage";
    }

}
