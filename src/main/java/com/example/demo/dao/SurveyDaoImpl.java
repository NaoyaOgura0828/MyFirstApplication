package com.example.demo.dao;

import com.example.demo.entity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository // データベース操作クラスの宣言
public class SurveyDaoImpl implements SurveyDao{

    /* 変数の初期化 */
    private final JdbcTemplate jdbcTemplate;

    /* データベース操作テンプレートの呼び出し */
    @Autowired
    public SurveyDaoImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void insertSurvey(Survey survey) {
        jdbcTemplate.update("INSERT INTO survey(age, satisfaction, comment, created) VALUES(?, ?, ?, ?)",
                survey.getAge(), survey.getSatisfaction(), survey.getComment(), survey.getCreated());
    }


    /* ServeyのSQLをMapで取得し、エンティティクラスに詰め直す */
    @Override
    public List<Survey> getAll() {
        String sql = "SELECT id, age, satisfaction, comment, created FROM survey";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Survey> list = new ArrayList<Survey>();
        for (Map<String, Object> result : resultList) {
            Survey survey = new Survey();
            survey.setId((int)result.get("id"));
            survey.setAge((int)result.get("age"));
            survey.setSatisfaction((int)result.get("satisfaction"));
            survey.setComment((String)result.get("comment"));
            survey.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
            list.add(survey); // surveyリストへ追加する
        }
        return list;
    }
}
