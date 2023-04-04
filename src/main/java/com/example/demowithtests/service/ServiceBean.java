package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.*;
import com.example.demowithtests.util.IllegalArgumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;
    private static ArrayList<String> countries = new ArrayList<>(List.of(
            "Germany", "Ukraine", "Poland", "Turkey",
            "Slovenia", "France", "USA",
            "United Kingdom", "Latvia", "Estonia"));
    private static final Random random=new Random();


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


    @Override
    public List<Employee> processor() {
        log.info("replace null  - start");
        List<Employee> replaceNull = repository.findEmployeeByIsDeletedNull();
        log.info("replace null after replace: " + replaceNull);
        for (Employee emp : replaceNull) {
            emp.setIsDeleted(Boolean.FALSE);
        }
        log.info("replaceNull = {} ", replaceNull);
        log.info("replace null  - end:");
        return repository.saveAll(replaceNull);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        List<Employee> employees = repository.findEmployeeByCountry(country);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCity(String city, String text) {
        List<Employee> employees = repository.findEmployeeByCity(city);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public String generateCountry() {
        String countryNumber=countries.get(random.nextInt(countries.size()));
        return countryNumber;
    }

    @Override
    public void autoFillData(String name,String country, String email) {
        for(int i=0;i<10000;i++){
            Employee employee=new Employee(name,country,email);
            repository.save(employee);
        }
    }

    @Override
    public void randomUpdateDataByCountry(Integer startID, Integer endID) {
        String country=generateCountry();
        log.info("Country is "+country);
        List<Employee> usersList=repository.findEmployeeByID(startID,endID);
        for (Employee emp:usersList) {
            emp.setCountry(country);
        }
        repository.saveAll(usersList);

    }

    @Override
    public void smartUpdateDataByCountry(Integer startID, Integer endID, String country) {
        List<Employee> usersList=repository.findEmployeeByID(startID,endID);
        for (Employee emp:usersList) {
            if(!emp.getCountry().equals(country)){
                emp.setCountry(country);
            }
        }
        repository.saveAll(usersList);
    }


    private static List<String> extracted(List<Employee> employees) {
        List<String> emails = new ArrayList<>();
        //Arrays.asList();
        for (Employee emp : employees) {
            emails.add(emp.getEmail());
        }
        return emails;
    }


    public void mailSender(List<String> emails, String text) {
        log.info("List size is " + emails.size());
        if (emails.size() == 0) {
            log.warn("Email list size is 0!");
        } else {
            log.info("Emails were successfully sent");
        }
    }

}
