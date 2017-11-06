package com.yong.orders;

import com.google.common.base.Throwables;
import com.yong.orders.common.Result;
import com.yong.orders.constant.SequenceKeys;
import com.yong.orders.dao.ErrorLogDao;
import com.yong.orders.dao.SequenceDao;
import com.yong.orders.model.ErrorLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.yong.orders.common.ResultCode.FAIL;

/**
 * Created by LiangYong on 2017/9/12.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    @Autowired
    private ErrorLogDao dao;

    @Autowired
    private SequenceDao sequenceDao;

        @ExceptionHandler(value = Exception.class)
        public Result errorHandler(Exception ex) {
            List<Map<String, String>> list = Arrays.stream(Throwables.getRootCause(ex).getStackTrace())
                .filter(t ->
                t.getClassName().startsWith("com.yong") &&
                    t.getLineNumber() > 0
            )
                .map(t -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("methodName", t.getMethodName());
                    map.put("fileName", t.getFileName());
                    map.put("lineNumber", t.getLineNumber() + "");
                    return map;
                }).collect(Collectors.toList());
            List<ErrorLog> logList = dao.findByMessage(Throwables.getRootCause(ex).getMessage());
            if (logList.size()!=0 &&
                logList.get(0).getDetail().containsAll(list)){
                ErrorLog errorLog = logList.get(0);
                errorLog.setCount(errorLog.getCount()+1);
                dao.save(errorLog);
            }else{
                ErrorLog logEntity = ErrorLog.builder()
                    .id(sequenceDao.getNextSequenceId(SequenceKeys.LOG).toString())
                    .message(Throwables.getRootCause(ex).getMessage())
                    .detail(list)
                    .count(1)
                    .createdDate(new Date()).build();
                dao.save(logEntity);

            }
            return Result.fail(FAIL, Throwables.getRootCause(ex).getMessage());
        }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }
}
