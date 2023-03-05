package com.lcwd.electronicsStrore.electronicsStore.repositories;

import com.lcwd.electronicsStrore.electronicsStore.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {


    // Optional is a container object used to represent the presence or absence of a value.
    // It is a way to indicate that a value may or may not be present in a given context.
    //n Java, the Optional class was introduced in Java 8 as a way to address the problem of NullPointerExceptions (NPEs)
    // that can occur when working with null values.
     Optional<User> findByEmail(String email);

     Optional<User> findByEmailAndPassword(String email,String password);

     List<User> findByNameContaining(String Keyword);

}










//
//    Spring Data JPA uses a feature called method name query resolution, which allows developers to define query methods
//    using method names that follow a certain naming convention.
//
//    Based on the method name, Spring Data JPA will automatically generate the corresponding database query at runtime,
//    without the need for developers to write explicit SQL queries.
//
//     For example, the method findByEmail in the UserRepository interface will be automatically resolved to a query that
//     searches for a User entity by email address, using the email parameter passed to the method. Similarly, the method findByEmailAndPassword will be resolved to a query that searches for a User entity by email and password, using the email and password parameters passed to the method.
//
//     The findByNameContaining method is also resolved using the Containing keyword, which indicates a substring search for
//      the given Keyword parameter in the name field of the User entity.
//
//     Spring Data JPA supports many other method name conventions, such as GreaterThan, LessThan, OrderBy, and many more.
//     These conventions allow developers to easily define complex queries without having to write explicit SQL statements.