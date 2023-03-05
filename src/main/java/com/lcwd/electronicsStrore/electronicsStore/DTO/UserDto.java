package com.lcwd.electronicsStrore.electronicsStore.DTO;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;

    @Size(min=3,max = 15,message = "Invalid name")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Invalid email")
    @Email(message = "invalid userEmail")
    private String email;
    @NotBlank(message = "password Required")
    private String password;
    @Size(min = 4,max = 6,message = "Invalid gender")
    private  String gender;
    @NotBlank(message = "write something about yourself")
    private String about;
    
    private String imageName;
}
