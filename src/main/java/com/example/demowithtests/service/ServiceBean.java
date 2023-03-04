package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.*;
import com.example.demowithtests.util.IllegalArgumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        try {
            return repository.save(employee);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (InvalidAccessException e) {
            throw new InvalidAccessException();
        }
    }

    @Override
    public List<Employee> getAll() {
        try {
            return repository.findAll();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (WrongTypeOfDataException e) {
            throw new WrongTypeOfDataException();
        }
    }

    @Override
    public Employee getById(Integer id) {
        try {
            Employee employee = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
            return employee;
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        try {
            return repository.findById(id)
                    .map(entity -> {
                        entity.setName(employee.getName());
                        entity.setEmail(employee.getEmail());
                        entity.setCountry(employee.getCountry());
                        return repository.save(entity);
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

    }

    @Override
    public void removeById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        } catch (InvalidAccessException e) {
            throw new InvalidAccessException();
        }
    }

    @Override
    public void removeAll() {
        try {
            repository.deleteAll();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        } catch (InvalidAccessException e) {
            throw new InvalidAccessException();
        }

    }
}
