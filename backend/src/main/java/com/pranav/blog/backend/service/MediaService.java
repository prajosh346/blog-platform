package com.pranav.blog.backend.service;

import com.pranav.blog.backend.dto.MediaResponse;
import com.pranav.blog.backend.entity.Media;
import com.pranav.blog.backend.entity.User;
import com.pranav.blog.backend.repository.MediaRepository;
import com.pranav.blog.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MediaService {

    private final CloudinaryService cloudinaryService;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;

    public MediaService(
            CloudinaryService cloudinaryService,
            MediaRepository mediaRepository,
            UserRepository userRepository
    ) {
        this.cloudinaryService = cloudinaryService;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
    }

    public MediaResponse uploadFile(
            MultipartFile file
    ) throws IOException {

        Map uploadResult =
                cloudinaryService.uploadFile(file);

        String imageUrl =
                uploadResult.get("secure_url").toString();

        String publicId =
                uploadResult.get("public_id").toString();

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        Media media = new Media();

        media.setFileName(file.getOriginalFilename());
        media.setFileUrl(imageUrl);
        media.setPublicId(publicId);
        media.setFileType(file.getContentType());
        media.setFileSize(file.getSize());
        media.setUploadedBy(user);

        Media savedMedia =
                mediaRepository.save(media);

        return new MediaResponse(
                savedMedia.getId(),
                savedMedia.getFileName(),
                savedMedia.getFileUrl()
        );
    }

    public void deleteFile(
            Long mediaId
    ) throws IOException {

        Media media =
                mediaRepository.findById(mediaId)
                        .orElseThrow(() ->
                                new RuntimeException("Media not found"));

        cloudinaryService.deleteFile(
                media.getPublicId()
        );

        mediaRepository.delete(media);
    }
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Media not found"));
    }
}