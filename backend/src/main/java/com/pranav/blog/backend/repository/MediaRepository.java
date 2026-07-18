package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}