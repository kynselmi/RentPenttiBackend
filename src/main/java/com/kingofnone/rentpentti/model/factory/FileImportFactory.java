package com.kingofnone.rentpentti.model.factory;

import com.kingofnone.rentpentti.model.FileImport;

public class FileImportFactory implements Factory<FileImport> {
    @Override
    public FileImport create() { return new FileImport(); }
}
