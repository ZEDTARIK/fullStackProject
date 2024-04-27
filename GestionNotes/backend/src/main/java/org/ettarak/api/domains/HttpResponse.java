package org.ettarak.api.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Collection;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse<T> implements Serializable {
    protected  String timeStamp;
    protected  int statusCode;
    protected HttpStatus status;
    protected  String reason;
    protected  String message;
    protected  String developerMessage;
    protected  Collection<? extends T> notes;
}

