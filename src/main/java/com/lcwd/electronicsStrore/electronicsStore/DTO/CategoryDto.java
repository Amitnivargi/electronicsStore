package com.lcwd.electronicsStrore.electronicsStore.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private String categoryId;
    @NotBlank
    @Min(value = 4,message = "title must be of minimum 4 char !!")
    private String title;
    @NotBlank(message = "description required")
    private String description;
    @NotBlank
    private String coverImage;
}
