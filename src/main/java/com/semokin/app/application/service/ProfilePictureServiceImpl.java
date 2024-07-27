package com.semokin.app.application.service;

import com.semokin.app.application.contract.ProfilePictureService;
import com.semokin.app.domain.model.ProfilePicture;
import com.semokin.app.domain.model.User;
import com.semokin.app.infrastructure.repository.ProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {
    private final Path fileStorageLocation;

    private final ProfilePictureRepository profilePictureRepository;
    @Autowired
    public ProfilePictureServiceImpl(ProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
        this.fileStorageLocation = Path.of("assets/profile/");
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String storeFile(MultipartFile file, User user) {
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        String idFileName = user.getId() + "_" + fileName;

        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setName(idFileName);
        profilePicture.setUrl(idFileName);
        profilePicture.setSize(file.getSize());
        profilePicture.setCustomer(user.getCustomer());
        profilePictureRepository.save(profilePicture);

        try {
            Path targetLocation = fileStorageLocation.resolve(idFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return idFileName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] loadFileAsBytes(String fileName) {
        return new byte[0];
    }
}
