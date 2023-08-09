package com.richards.promeescuous.dtos.responses;

import lombok.Builder;

@Builder
public class ApiResponse <T> {
    private T data;
    }

