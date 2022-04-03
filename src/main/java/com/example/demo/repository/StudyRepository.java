package com.example.demo.repository;

import com.example.demo.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Maciej Okliński
 * Klasa dostępu do danych w odniesieniu do klasy encji Students
 */

@Repository
public interface StudyRepository extends JpaRepository<Students, Long> {
    Optional<Students> findByName(String name);
    List<Students> findTop2ByOrderByNameAsc();
}