package com.example.demo.app.inquiry;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryNotFoundException;
import com.example.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Inquiry> list = inquiryService.getAll();


//        /* 例外処理テストの為のダミーレコード */
//        Inquiry inquiry = new Inquiry();
//        inquiry.setId(4);
//        inquiry.setName("Jamie");
//        inquiry.setEmail("sample4@example.com");
//        inquiry.setContents("Hello.");
//
//        inquiryService.update(inquiry);


        /* try、catchによる例外処理 */
//        try {
//            inquiryService.update(inquiry);
//        } catch (InquiryNotFoundException e) {
//            model.addAttribute("message", e);
//            return "error/CustomPage";
//        }


        model.addAttribute("inquiryList", list);
        model.addAttribute("title", "Inquiry Index");

        return "inquiry/index";
    }


    /* フォームの入力値を取得する */
    @GetMapping("/form")
    public String form(InquiryForm inquiryForm,
                       Model model,
                       @ModelAttribute("complete") String complete) {
        model.addAttribute("title", "Inquiry Form");
        return "inquiry/form";
    }

    /* "戻るボタン"が押された時に入力値をフォームに戻す（form.htmlへ遷移） */
    @PostMapping("/form")
    public String formGoBack(InquiryForm inquiryForm, Model model) {
        model.addAttribute("title", "Inquiry Form");
        return "inquiry/form";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated InquiryForm inquiryForm,
                          BindingResult result,
                          Model model) {
        /* 入力内容にエラーがあった場合は入力値をフォームに戻す（form.htmlへ遷移） */
        if (result.hasErrors()) {
            model.addAttribute("title", "Inquiry Form");
            return "inquiry/form";
        }
        /* 入力内容を表示する（confirm.htmlへ遷移） */
        model.addAttribute("title", "Confirm Page");
        return "inquiry/confirm";
    }

    @PostMapping("/complete")
    public String complete(@Validated InquiryForm inquiryForm,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Inquiry Form");
            return "inquiry/form";
        }

        Inquiry inquiry = new Inquiry();
        inquiry.setName(inquiryForm.getName());
        inquiry.setEmail(inquiryForm.getEmail());
        inquiry.setContents(inquiryForm.getContents());
        inquiry.setCreated(LocalDateTime.now());

        inquiryService.save(inquiry);
        redirectAttributes.addFlashAttribute("complete", "Registered!");
        return "redirect:/inquiry/form"; // ※HTMLファイルではなくURLを指している
    }

//    /* メソッドによる例外処理 */
//    /** 同じController内での同じ名称の例外に対して共通処理出来る */
//    @ExceptionHandler(InquiryNotFoundException.class)
//    public String handleException(InquiryNotFoundException e, Model model) {
//        model.addAttribute("message", e);
//        return "error/CustomPage";
//    }

}
