package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.integration.payment.PaymentImportException;
import com.kingofnone.rentpentti.integration.payment.PaymentImporter;
import com.kingofnone.rentpentti.model.FileImport;
import com.kingofnone.rentpentti.model.factory.FileImportFactory;
import com.kingofnone.rentpentti.model.factory.impl.FactoryService;
import com.kingofnone.rentpentti.service.model.impl.FileImportModelService;
import com.kingofnone.rentpentti.service.util.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController("fileImportController")
@RequestMapping("/import")
public class FileImportController extends AbstractController<FileImport> {
    @Autowired
    FileImportModelService fileImportService;
    @Autowired
    PaymentImporter paymentImporter;
    @Autowired
    StorageService storageService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public FileImportController() { setService(fileImportService); }

    @PostMapping(value = "/payment")
    public String create(
            @RequestParam("importFile") MultipartFile importFile,
            @RequestParam("receiver") String receiver,
            @RequestParam("headerIncluded") Boolean headerIncluded,
            RedirectAttributes redirectAttributes) throws PaymentImportException {
        FileImport fileImport = (FileImport) FactoryService.getFactory(FileImportFactory.class).create();
        fileImport.setImportedTime(new Date());
        fileImport.setSuccessfullyImported(false);
        fileImport.setFileName(importFile.getName());
        if (importFile.isEmpty()) {
            fileImport.setError("EMPTY_FILE");
            fileImportService.create(fileImport);
            redirectAttributes.addFlashAttribute("message", "File you tried to import was empty. Importing failed");
            return "redirect:/";
        }
        Optional<File> storedFile = storageService.store(importFile);
        if (storedFile.isEmpty()) {
            fileImport.setError("STORAGE_FAILED");
            fileImportService.create(fileImport);
            redirectAttributes.addFlashAttribute("message", "File import failed");
            return "redirect:/";
        }
        paymentImporter.importPayments(storedFile.get(), receiver, headerIncluded);
        fileImport.setFilePath(storedFile.get().getPath());
        fileImport.setSuccessfullyImported(true);
        fileImportService.create(fileImport);
        redirectAttributes.addFlashAttribute("message", "File imported succesfully");

        return "redirect:/";
    }

    @ExceptionHandler(PaymentImportException.class)
    public ResponseEntity<?> handlePaymentImportException(
            PaymentImportException paymentImportException,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Parsing payment import file failed");
        return ResponseEntity.badRequest().build();
    }


}
