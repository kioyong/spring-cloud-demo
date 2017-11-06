package com.yong.orders.dao;

import com.yong.orders.dao.custom.SequenceCustomDao;
import com.yong.orders.model.Sequence;
import org.springframework.stereotype.Repository;

/**
 * Created by LiangYong on 2017/7/26.
 */

@Repository
public interface SequenceDao extends BaseDao<Sequence>,SequenceCustomDao {

}
