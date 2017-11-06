package com.yong.orders.config;

import com.yong.orders.constant.SequenceKeys;
import com.yong.orders.dao.SequenceDao;
import com.yong.orders.model.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by LiangYong on 2017/7/26.
 */
@Component
public class SequenceConfig implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SequenceConfig.class);

    @Autowired
    private SequenceDao dao;

    @Override
    public void run(String... strings) throws Exception {
        log.info("Start Init Sequence.");
        try {
            initSequence();
        }catch (Exception ex){
            log.error("exception handle : {}",ex);

        }
    }

    public void initSequence (){
        SequenceKeys.sequenceKeysMap.entrySet().stream()
            .filter(t -> !dao.findById(t.getKey()).isPresent())
            .forEach(t -> {
                log.info("Start init {},start Value = {}",t.getKey(),t.getValue());
                dao.save(Sequence.builder().id(t.getKey()).sequenceNo(t.getValue()).build());
            });
    }
}
