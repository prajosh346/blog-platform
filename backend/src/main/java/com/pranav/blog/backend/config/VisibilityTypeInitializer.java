package com.pranav.blog.backend.config;

import com.pranav.blog.backend.entity.VisibilityType;
import com.pranav.blog.backend.repository.VisibilityTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VisibilityTypeInitializer implements CommandLineRunner {

    private final VisibilityTypeRepository repository;

    public VisibilityTypeInitializer(VisibilityTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        createIfNotExists("FEATURED", "Featured blog");
        createIfNotExists("TRENDING", "Trending blog");
        createIfNotExists("BREAKING", "Breaking news");
        createIfNotExists("RECOMMENDED", "Recommended content");
    }

    private void createIfNotExists(String name, String description) {
        repository.findByName(name)
                .orElseGet(() ->
                        repository.save(
                                new VisibilityType(name, description)
                        ));
    }
}