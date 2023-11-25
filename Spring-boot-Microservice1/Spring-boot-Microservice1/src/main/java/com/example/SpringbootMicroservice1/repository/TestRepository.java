package com.example.SpringbootMicroservice1.repository;

import com.example.SpringbootMicroservice1.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    // Ajoutez des méthodes spécifiques au besoin
}

