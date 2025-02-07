package com.phongdo.osahaneat.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.phongdo.osahaneat.core.pagination.Pagination;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    String message;
    Object result;
    Pagination pagination;

    public static ResponseEntity<ApiResponse> success(String message, Object result, Pagination pagination){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message(message)
                        .pagination(pagination)
                        .result(result)
                        .build()
        );
    }

    public static ResponseEntity<ApiResponse> success(String message, Object result){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message(message)
                        .result(result)
                        .build()
        );
    }

    public static ResponseEntity<ApiResponse> success(String message){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message(message)
                        .build()
        );
    }

    public static ResponseEntity<ApiResponse> success(Object result){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .result(result)
                        .build()
        );
    }
}
