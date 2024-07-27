package com.semokin.app.application.contract;

import com.semokin.app.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePictureService {
    String storeFile(MultipartFile file, User user);
    byte[] loadFileAsBytes(String fileName);
}
