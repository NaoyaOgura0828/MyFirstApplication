package com.example.demo.dao;

import com.example.demo.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository // データベース操作クラスの宣言
public class InquiryDaoImpl implements InquiryDao{

    /* 変数の初期化 */
    private final JdbcTemplate jdbcTemplate;

    /* データベース操作テンプレートの呼び出し */
    @Autowired
    public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertInquiry(Inquiry inquiry) {
        jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)", // idは指定しない
                inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());

    }

    /* InquiryのSQLをMapで取得し、エンティティクラスに詰め直す */
    @Override
    public List<Inquiry> getAll() {
        String sql = "SELECT id, name, email, contents, created FROM inquiry";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Inquiry> list = new ArrayList<Inquiry>();
        for (Map<String, Object> result : resultList) {
            Inquiry inquiry = new Inquiry();
            inquiry.setId((int)result.get("id"));
            inquiry.setName((String)result.get("name"));
            inquiry.setEmail((String)result.get("email"));
            inquiry.setContents((String)result.get("contents"));
            inquiry.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
            list.add(inquiry);
        }
        return list; // inquiryリストへ追加する
    }
}
