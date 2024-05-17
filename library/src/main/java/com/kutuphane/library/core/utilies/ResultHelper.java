package com.kutuphane.library.core.utilies;

import com.kutuphane.library.core.result.Result;
import com.kutuphane.library.core.result.ResultData;
import com.kutuphane.library.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

public class ResultHelper {
    public static <T> ResultData<T> created(T data){
        return new  ResultData<>(true,Msg.CREATED,"201",data);
    }
    public static <T>ResultData<T> validateError(T data){
        return new  ResultData<>(true,Msg.VALIDATE_ERROR,"400",data);
    }
    public static <T>ResultData<T> success(T data){
        return new  ResultData<>(true,Msg.OK,"200",data);
    }
    public static Result successResult(){
        return  new Result(true,Msg.OK,"200");

    }
    public static Result notFoundError(String msg){
        return new Result(false,Msg.NOT_FOUND,"404");
    }
    public static <T>ResultData<CursorResponse<T>> cursor(Page<T> pageData){
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());
        return  ResultHelper.success(cursor);
    }
}
