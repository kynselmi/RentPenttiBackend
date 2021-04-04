package com.kingofnone.rentpentti.service.impl;

import com.kingofnone.rentpentti.dao.impl.FileImportDao;
import com.kingofnone.rentpentti.model.FileImport;

public class FileImportService extends AbstractService<FileImport> {
    public FileImportService() { setDao(new FileImportDao());}
}
