package com.example.demo.service;

import com.example.demo.entity.Inquiry;

import java.util.List;

public interface InquiryService {

    void save(Inquiry inquiry);

    List<Inquiry> getAll();

}
