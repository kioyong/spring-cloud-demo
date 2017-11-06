package com.yong.orders.service.impl;

/**
 * Created by yong.a.liang on 6/22/2017.
 */

import com.yong.orders.annotation.NotNull;
import com.yong.orders.annotation.Unique;
import com.yong.orders.common.Result;
import com.yong.orders.common.ResultCode;
import com.yong.orders.dao.BaseDao;
import com.yong.orders.dao.UserDao;
import com.yong.orders.model.User;
import com.yong.orders.model.base.BaseEntity;
import com.yong.orders.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import static com.yong.orders.common.ResultCode.FAIL;


public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    BaseDao<T> dao;

    public BaseServiceImpl(BaseDao<T> dao) {
        this.dao=dao;
    }

    public T beforeDeactivate(T instance) {
        return instance;
    }

    public T beforeUpdate(T instance) {
        return instance;
    }

    public T beforeUpdateSave(T instance) {
        return instance;
    }

    public T beforeAdd(T instance) {
        return instance;
    }
    public T beforeAddSave(T instance) {
        return instance;
    }


    @Autowired
    private MongoOperations mongoOperation;


    public boolean exists(Class<?> entityClass, Map<String, Object> params) {
        if (null == mongoOperation) { // avoid null exception in service test
            return false;
        } else {

            Query query = new Query();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                if (key == "id") continue;
                query.addCriteria(Criteria.where(key).is(entry.getValue()));
            }
            if (null != params.get("id")) {
                query.addCriteria(Criteria.where("id").ne(params.get("id")));
            }
            return mongoOperation.exists(query, entityClass);
        }
    }
    public String getCreatedBy(){
        //TODO Created By hard code
        return "LiangYong";
    }

    @Override
    public T addOne(T instance) throws IllegalArgumentException{
        try {
            validate(instance);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        T instanceCopy = beforeAdd(instance);
            instanceCopy.setCreatedBy(getCreatedBy());
            instanceCopy.setCreatedDate(new Date());
            instanceCopy.setIsActive(true);
            instanceCopy = beforeAddSave(instanceCopy);
            doSaveForAdd(instanceCopy);
            return instanceCopy;
    }

    protected void doSaveForAdd(T instance) {
        dao.save(instance);
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<T> findAll(){
        List<T> list= dao.findAll();
        return list;
    }

    @Override
    public T getOne(String id) {
        //TODO why Optional
        Optional<T> one = dao.findById(id);
        if(one == null){
            throw new IllegalArgumentException("Entity Not found!");
        }
        return one.get();
    }

    @Override
    public T updateOne(T instance) throws IllegalArgumentException{
        try {
            validate(instance);
            T instanceCopy = beforeUpdate(instance);
            Field idField = instanceCopy.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            String id = (String) idField.get(instanceCopy);
            log.debug("instance's id = " + id + ", active = " + instanceCopy.getIsActive());
            T old = dao.findById(id).get();
            if (old != null) {
                Field[] fields = old.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(old, field.get(instanceCopy));
                }
                old.setIsActive(instanceCopy.getIsActive());
                old.setLastModifiedBy(getCreatedBy());
                old.setLastModifiedDate(new Date());
                old = beforeUpdateSave(old);
                this.doSaveForUpdate(old);
                return old;
            }
            throw new IllegalArgumentException("id not found,update fail");
        }catch(Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    protected void doSaveForUpdate(T instance) {
        dao.save(instance);
    }

    void validate(T instance) throws IllegalAccessException {
        log.info("instance = " + instance);
        if (instance == null) {
            log.error("instance is null!return error.");
            throw new IllegalAccessException("instance is null!");
        }

        // check combination unique
        Unique uniqueAnnotation = instance.getClass().getDeclaredAnnotation(Unique.class);
        if (null != uniqueAnnotation) {
            String[] fieldNames = uniqueAnnotation.fields().split(",");

            try {

                Field idField = instance.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                String id = (String) idField.get(instance);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", id);
                for (String fieldName : fieldNames) {
                    Field field = instance.getClass().getDeclaredField(fieldName);
                    if (null != field) {
                        field.setAccessible(true);
                        map.put(field.getName(), field.get(instance));
                    }
                }

                if (map.size() == fieldNames.length + 1) {
                    if (exists(instance.getClass(), map)) {
                        throw new IllegalArgumentException(uniqueAnnotation.message());
                    }
                }

            } catch (IllegalArgumentException err) {
                throw err;
            } catch (Exception err) {
                log.error("BaseServiceImpl::validate:check combination unique failed:", err);
            }


        }

        // unique field checking

        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {

                if (annotation instanceof NotNull) {
                    NotNull myAnnotation = (NotNull) annotation;
                    Object value = field.get(instance);
                    if (value == null) {
                        throw new IllegalArgumentException(myAnnotation.message());
                    }
                    if (value.toString().trim().length() == 0) {
                        throw new IllegalArgumentException(myAnnotation.message());
                    }
                }

                if (annotation instanceof Unique) {
                    Unique myAnnotation = (Unique) annotation;
                    try {
                        Field idField = instance.getClass().getDeclaredField("id");
                        idField.setAccessible(true);
                        String id = (String) idField.get(instance);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(field.getName(), field.get(instance));
                        map.put("id", id);

                        if (exists(instance.getClass(), map)) {
                            throw new IllegalArgumentException(myAnnotation.message());
                        }
                    } catch (IllegalArgumentException err) {
                        throw err;
                    } catch (Exception e) {
                        log.error("BaseServiceImpl::validate.unique", e);
                    }
                }
            }
        }
    }
}

