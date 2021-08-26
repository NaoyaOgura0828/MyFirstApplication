package com.example.demo.app.survey;


import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;
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
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) { this.surveyService = surveyService; }


    /* アンケート一覧の取得 */
    @GetMapping
    public String index(Model model) {
        List<Survey> list = surveyService.getAll();

        model.addAttribute("surveyList", list);
        model.addAttribute("title", "Survey Index");

        return "survey/index";
    }


    /* フォームの入力値を取得する */
    @GetMapping("/form")
    public String form(SurveyForm surveyForm,
                       Model model,
                       @ModelAttribute("complete") String complete) {
        model.addAttribute("title", "Survey Form");
        return "survey/form";
    }

    /* "戻るボタン"が押された時に入力値をフォームに戻す（form.htmlへ遷移） */
    @PostMapping("/form")
    public String formGoBack(SurveyForm surveyForm, Model model) {
        model.addAttribute("title", "Survey Form");
        return "survey/form";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated SurveyForm surveyForm,
                          BindingResult result,
                          Model model) {
        /* 入力内容にエラーがあった場合は入力値をフォームに戻す（form.htmlへ遷移） */
        if (result.hasErrors()) {
            model.addAttribute("title", "Survey Form");
            return "survey/form";
        }
        /* 入力内容を表示する（confirm.htmlへ遷移） */
        model.addAttribute("title", "Confirm Page");
        return "survey/confirm";
    }

    @PostMapping("/complete")
    public String complete(@Validated SurveyForm surveyForm,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Survey Form");
            return "survey/form";
        }

        Survey survey = new Survey();
        survey.setAge(surveyForm.getAge());
        survey.setSatisfaction(surveyForm.getSatisfaction());
        survey.setComment(surveyForm.getComment());
        survey.setCreated(LocalDateTime.now());

        surveyService.save(survey);
        redirectAttributes.addFlashAttribute("complete", "Thanks for your cooperation!");
        return "redirect:/survey/form";
    }

}
