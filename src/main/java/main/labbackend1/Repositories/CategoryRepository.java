package main.labbackend1.Repositories;

import main.labbackend1.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsGlobalTrue();
    List<Category> findByIsGlobalFalseAndUserId(Long userId);
}

