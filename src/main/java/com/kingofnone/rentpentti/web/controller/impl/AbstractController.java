package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.BaseEntity;
import com.kingofnone.rentpentti.service.model.ModelService;
import com.kingofnone.rentpentti.web.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AbstractController<T extends BaseEntity> implements Controller<T> {

    private static final Logger logger = LogManager.getLogger(AbstractController.class);

    protected ModelService<T> modelService;

    @Override
    public List<T> findAll() {
        return modelService.findAllActive();
    }

    @Override
    public ResponseEntity<T> create(@RequestBody T entity) {
        Optional<T> optional = modelService.create(entity);
        HttpHeaders headers = new HttpHeaders();

        if (optional.isEmpty()) {
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(optional.get(), headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<T> getById(@PathVariable long id) {
        Optional<T> optional = modelService.getById(id);
        HttpHeaders headers = new HttpHeaders();
        if (optional.isEmpty()) {
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }
        T entity = optional.get();
        return entity.isNotDeleted() ? new ResponseEntity<>(entity, headers, HttpStatus.OK) : new ResponseEntity<>(null, headers, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public T update(@PathVariable long id, @RequestBody T entity) {
        return modelService.updateById(id, entity);
    }

    @Override
    public void delete(@PathVariable long id) {
        modelService.deleteById(id);
    }

    public ModelService<T> getService() {
        return modelService;
    }

    public void setService(ModelService<T> modelService) {
        this.modelService = modelService;
    }
}

