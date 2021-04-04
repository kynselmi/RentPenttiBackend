package com.kingofnone.rentpentti.service.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

public interface StorageService {

    Optional<File> store(MultipartFile file);
}
