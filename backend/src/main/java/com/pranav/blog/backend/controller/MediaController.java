package com.pranav.blog.backend.controller;

import com.pranav.blog.backend.dto.MediaResponse;
import com.pranav.blog.backend.entity.Media;
import com.pranav.blog.backend.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<MediaResponse> uploadFile(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        return ResponseEntity.ok(
                mediaService.uploadFile(file)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(
            @PathVariable Long id
    ) throws IOException {

        mediaService.deleteFile(id);

        return ResponseEntity.ok(
                "File deleted successfully"
        );
    }

    @GetMapping
    public ResponseEntity<List<Media>> getAllMedia() {

        return ResponseEntity.ok(
                mediaService.getAllMedia()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                mediaService.getMediaById(id)
        );
    }
}