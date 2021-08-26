package com.example.demo.dao;

import com.example.demo.entity.Survey;

import java.util.List;


public interface SurveyDao {

    void insertSurvey(Survey survey);

    List<Survey> getAll();

}
