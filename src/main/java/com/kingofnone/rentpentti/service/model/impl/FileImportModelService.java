package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.impl.FileImportDao;
import com.kingofnone.rentpentti.model.FileImport;
import org.springframework.stereotype.Service;

@Service
public class FileImportModelService extends AbstractModelService<FileImport> {
    public FileImportModelService() { setDao(new FileImportDao());}
}
