package com.yong.orders.dao.impl;

import com.yong.orders.dao.custom.SequenceCustomDao;
import com.yong.orders.model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by yong.a.liang on 7/27/2017.
 */
@Repository
public class SequenceDaoImpl implements SequenceCustomDao{


    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public Long getNextSequenceId(String key) {
        Query query = new Query(Criteria.where("id").is(key));

        Update update = new Update();
        update.inc("sequenceNo", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        Sequence seqId = mongoOperation.findAndModify(query, update, options, Sequence.class);

        return seqId.getSequenceNo();
    }
}
