package com.ecreyes.springbootbackendapi.service;

import java.io.File;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    public String create(MultipartFile file);

    public Boolean delete(String fileName);

    public Path getAbsolutePath(String fileName);

    public File getFileByName(String fileName);
}
