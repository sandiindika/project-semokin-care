package com.semokin.app.application.service;

import com.semokin.app.application.contract.ProfilePictureService;
import com.semokin.app.domain.model.ProfilePicture;
import com.semokin.app.domain.model.Role;
import com.semokin.app.domain.model.User;
import com.semokin.app.infrastructure.repository.ProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
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
    @Transactional
    public String storeFile(MultipartFile file, User user) {
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        String idFileName = user.getId() + "_" + fileName;

        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setName(idFileName);
        profilePicture.setUrl(idFileName);
        profilePicture.setSize(file.getSize());
        if (user.getRoles().stream()
                .anyMatch((role) -> role.getRole().equals(Role.Privilege.CUSTOMER))){
            profilePicture.setCustomer(user.getCustomer());
        }else {
            profilePicture.setStaff(user.getStaff());
        }
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
        Path filePath = fileStorageLocation.resolve(fileName);
        System.out.println(filePath);
        if (!Files.exists(filePath)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,fileName+" not found");
        }
        try {
            return Files.readAllBytes(filePath); // ini jika langusng tnnpa di olah
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            Thumbnails.of(filePath.toFile())
//                    .size(100, 100)
//                    .outputFormat("jpg")
//                    .toOutputStream(outputStream);
//            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,e.getMessage()+" not found");
        }
    }
}
