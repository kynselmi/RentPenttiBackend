package com.kingofnone.rentpentti.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class FileImport extends BaseEntity {
    private Date importedTime;
    private String filePath;
    private String fileName;
    private Boolean successfullyImported;
    private String error;

    public Date getImportedTime() {
        return importedTime;
    }

    public void setImportedTime(Date importedTime) {
        this.importedTime = importedTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean getSuccessfullyImported() {
        return successfullyImported;
    }

    public void setSuccessfullyImported(Boolean successfullyImported) {
        this.successfullyImported = successfullyImported;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
