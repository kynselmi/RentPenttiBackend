package com.kingofnone.rentpentti.service.util.impl;

import com.kingofnone.rentpentti.service.util.StorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger logger = LogManager.getLogger(StorageServiceImpl.class);

    @Value("${file.storage.folder}")
    private String fileStorageFolder;

    private static final String IMPORT_FOLDER = "/import";

    @Override
    public Optional<File> store(MultipartFile file) {
        Optional<File> savedFileOptional = Optional.empty();
        try {
            File savedFile =  new File(IMPORT_FOLDER+"/"+file.getName().replaceAll("\\W", "_"));
            file.transferTo(savedFile);
            savedFileOptional = Optional.of(savedFile);
        } catch (IOException e) {
            logger.error("Saving file failed", e);
        }
        return savedFileOptional;
    }


}
