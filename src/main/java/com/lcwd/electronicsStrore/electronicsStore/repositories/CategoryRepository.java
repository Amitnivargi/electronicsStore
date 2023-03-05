package com.lcwd.electronicsStrore.electronicsStore.repositories;

import com.lcwd.electronicsStrore.electronicsStore.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
