package com.kingofnone.rentpentti.web.controller;

import com.kingofnone.rentpentti.model.BaseEntity;
import com.kingofnone.rentpentti.web.controller.impl.ControllerConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface Controller<T extends BaseEntity> {
    @GetMapping(value = "", produces = ControllerConstants.APPLICATION_JSON)
    List<T> findAll();

    @PostMapping(value = "", consumes = ControllerConstants.APPLICATION_JSON, produces = ControllerConstants.APPLICATION_JSON)
    ResponseEntity<T> create(@RequestBody T entity);

    @GetMapping(value = "/{id}", produces = ControllerConstants.APPLICATION_JSON)
    ResponseEntity<T> getById(@PathVariable long id);

    @PutMapping(value = "/{id}", consumes = ControllerConstants.APPLICATION_JSON, produces = ControllerConstants.APPLICATION_JSON)
    T update(@PathVariable long id, @RequestBody T entity);

    @DeleteMapping(value = "/{id}", produces = ControllerConstants.APPLICATION_JSON)
    void delete(@PathVariable long id);
}
