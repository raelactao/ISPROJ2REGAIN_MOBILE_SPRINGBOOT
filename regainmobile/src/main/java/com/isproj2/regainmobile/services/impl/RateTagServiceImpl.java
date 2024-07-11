package com.isproj2.regainmobile.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.repo.RateTagRepository;
import com.isproj2.regainmobile.services.RateTagService;

@Service
public class RateTagServiceImpl implements RateTagService {

    @Autowired
    private RateTagRepository rateTagRepository;

    @Override
    public Integer getRateTagId(String name) {
        return rateTagRepository.findByName(name).getRateTagID();
    }
    
}
