package com.example.catchat.repository;

import com.example.catchat.model.CatResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<CatResource, Long> {
}
