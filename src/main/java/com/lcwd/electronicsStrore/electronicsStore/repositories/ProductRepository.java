package com.lcwd.electronicsStrore.electronicsStore.repositories;

import com.lcwd.electronicsStrore.electronicsStore.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    //
    List<Product> findByTitle(String subtitle);
    List<Product> findByLiveTrue();


}
