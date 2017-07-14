package com.comcast.advertisement.validation;

import com.comcast.advertisement.exception.AdValidityException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Service
public class AdValidationService {

    public void validateCreateRequest(BindingResult result) {
        if(result.hasErrors()){
            StringBuffer sb = new StringBuffer();
            for(FieldError fieldError : result.getFieldErrors()){
                sb.append("field: " + fieldError.getField());
                sb.append(" " + fieldError.getCode());
                sb.append("\n");
            }
            throw new AdValidityException(sb.toString());
        }
    }

}
