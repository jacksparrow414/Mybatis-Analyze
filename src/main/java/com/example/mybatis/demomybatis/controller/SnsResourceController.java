package com.example.mybatis.demomybatis.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.mybatis.demomybatis.entity.SnsResourceBasic;
import com.example.mybatis.demomybatis.entity.SnsResourceCache;
import com.example.mybatis.demomybatis.entity.SnsResourceEssay;
import com.example.mybatis.demomybatis.service.SnsResourceBasicService;
import com.example.mybatis.demomybatis.service.SnsResourceCacheService;
import com.example.mybatis.demomybatis.service.SnsResourceEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jacksparrow414
 * @date 2020/8/20
 */
@RestController
@RequestMapping(value = "sns")
public class SnsResourceController {
    
    @Autowired
    private SnsResourceCacheService cacheService;
    
    @Autowired
    private SnsResourceEssayService essayService;
    
    @Autowired
    private SnsResourceBasicService basicService;
    
    @RequestMapping(value = "saveBatch")
    public void saveBatch() {
        String id = IdWorker.get32UUID();
        SnsResourceBasic basic = new SnsResourceBasic();
        basic.setResId(id);
        SnsResourceCache cache = new SnsResourceCache();
        cache.setResId(id);
        SnsResourceEssay essay = new SnsResourceEssay();
        essay.setResId(id);
        cacheService.save(cache);
        essayService.save(essay);
        basicService.save(basic);
    }
}
