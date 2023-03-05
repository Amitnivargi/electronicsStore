package com.lcwd.electronicsStrore.electronicsStore.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @Column(name = "Id")
    private String categoryId;
    @Column(name = "category_title",length = 60)
    private String title;
    @Column(name="category_desc",length = 50)
    private String description;
    private String coverImage;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products=new ArrayList<>();


}
