package com.example.demo.repository;

import com.example.demo.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Maciej Okliński
 * Klasa dostępu do danych w odniesieniu do encji Teachers
 */

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Long> {

    Optional<Teachers> findByName(String name);
    List<Teachers> findTop1ByOrderByNameAsc();
}
