package com.example.demo.app.inquiry;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
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
}
