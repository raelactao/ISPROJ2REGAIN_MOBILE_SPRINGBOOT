package com.isproj2.regainmobile.exceptions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageValidateService {

    private static final Logger logger = LoggerFactory.getLogger(ImageValidateService.class);

    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024; // 2 MB
    private static final List<String> ALLOWED_CONTENT_TYPES = List.of("image/jpeg", "image/png", "image/jpg");

    public void validateImageFile(MultipartFile file) {
        logger.info("Starting image validation...");

        if (file.isEmpty()) {
            logger.error("Validation failed: Uploaded file is empty.");
            throw new IllegalArgumentException("Uploaded file is empty.");
        }

        logger.info("File size: {} bytes", file.getSize());
        if (file.getSize() > MAX_FILE_SIZE) {
            logger.error("Validation failed: File size exceeds the maximum limit of 2 MB.");
            throw new IllegalArgumentException("File size exceeds the maximum limit of 2 MB.");
        }

        logger.info("File content type: {}", file.getContentType());
        if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
            logger.error("Validation failed: Invalid file type. Allowed types: {}", ALLOWED_CONTENT_TYPES);
            throw new IllegalArgumentException("Invalid file type. Only JPEG and PNG are allowed.");
        }

        logger.info("Image validation passed.");
    }
}
