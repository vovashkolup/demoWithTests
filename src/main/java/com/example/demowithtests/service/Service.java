package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.util.List;

public interface Service {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    List<Employee> processor();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    String generateCountry();

    void autoFillData(String name,String country,String email);

    void randomUpdateDataByCountry(Integer startID, Integer endID);
    void smartUpdateDataByCountry(Integer startID, Integer endID,String country);

}
