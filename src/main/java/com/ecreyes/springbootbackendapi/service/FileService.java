package com.ecreyes.springbootbackendapi.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements IFileService {
    private final String FOLDER_NAME = "storage";

    @Override
    public String create(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path absolutePath = this.getAbsolutePath(fileName);
            Files.copy(file.getInputStream(), absolutePath);
            return fileName;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Boolean delete(String fileName) {
        try {
            File file = this.getFileByName(fileName);
            if (file == null)
                return false;
            if (file.exists() && file.canRead()) {
                return file.delete();
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public Path getAbsolutePath(String fileName) {
        return Paths.get(FOLDER_NAME).resolve(fileName).toAbsolutePath();
    }

    @Override
    public File getFileByName(String fileName) {
        if (fileName != null && fileName.length() > 0) {
            Path absolutePath = this.getAbsolutePath(fileName);
            File file = absolutePath.toFile();
            return file;
        }
        return null;
    }

}
