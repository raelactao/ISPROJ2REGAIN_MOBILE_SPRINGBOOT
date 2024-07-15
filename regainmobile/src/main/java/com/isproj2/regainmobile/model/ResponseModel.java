package com.isproj2.regainmobile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {
    @lombok.NonNull
    private int statusCode;
    @lombok.NonNull
    private String message;
    private T response;
}
