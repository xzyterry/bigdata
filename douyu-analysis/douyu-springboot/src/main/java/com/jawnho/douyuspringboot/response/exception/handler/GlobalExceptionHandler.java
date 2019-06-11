package com.jawnho.douyuspringboot.response.exception.handler;


import com.jawnho.douyuspringboot.response.ResultEntity;
import com.jawnho.douyuspringboot.response.ReturnStatus;
import com.jawnho.douyuspringboot.response.exception.BussinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 *
 * @date 2018-09-12
 * @description
 *     异常处理handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 数据库与实体字段映射异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultEntity ConstraintViolationException(ConstraintViolationException e){
        logger.error(e.getMessage(),e);
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ReturnStatus.DB_REFLACT_ERROR.getCode());
        resultEntity.setMessage(ReturnStatus.DB_REFLACT_ERROR.getMessage()+"->"+e.getMessage());
        return resultEntity;
    }

    /**
     * 请求参数映射异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultEntity IllegalArgumentException(IllegalArgumentException e){
        logger.error(e.getMessage(),e);
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ReturnStatus.PARAM_FIELD_INCORRECT.getCode());
        resultEntity.setMessage(ReturnStatus.PARAM_FIELD_INCORRECT.getMessage()+"->"+e.getMessage());
        return resultEntity;
    }


    /**
     * 请求路径不存在异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultEntity noHandlerFoundException(Exception e){
        logger.error(e.getMessage(),e);
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ReturnStatus.REQUEST_NOT_FOUND.getCode());
        resultEntity.setMessage(ReturnStatus.REQUEST_NOT_FOUND.getMessage()+"->"+e.getMessage());
        return resultEntity;
    }

    /**
     * 业务层异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BussinessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultEntity paramHandler(BussinessException e){
        logger.error(e.getMessage(),e);
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(e.getCode());
        resultEntity.setMessage(e.getMessage());
        return resultEntity;
    }


    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultEntity exceptionHandler(Exception e){
        logger.error(e.getMessage(),e);
        ResultEntity resultEntity = new ResultEntity<>();
        resultEntity.setCode(ReturnStatus.UNKNOWN_ERROR.getCode());
        resultEntity.setMessage(e.getMessage()==null?ReturnStatus.UNKNOWN_ERROR.getMessage():e.getMessage());
        return resultEntity;
    }

}
