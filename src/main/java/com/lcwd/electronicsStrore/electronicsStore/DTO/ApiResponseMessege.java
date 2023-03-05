package com.lcwd.electronicsStrore.electronicsStore.DTO;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessege {
    private String messege;
    private boolean success;
    private HttpStatus status;

}
