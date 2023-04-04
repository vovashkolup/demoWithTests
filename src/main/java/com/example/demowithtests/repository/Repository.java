package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = " select e from Employee e where e.country=:country")
    List<Employee> findEmployeeByCountry(String country);

//    @Query(value = "select e from Employee e join e.addresses a where a.city=:city")
//    List<Employee> findEmployeeByCity(String city);

    @Query(value = "select * from users join addresses on users.id=addresses.employee_id where addresses.city= ? ",nativeQuery = true)
    List<Employee> findEmployeeByCity(String city);

    @Query(value = "select * from users where id between ?1 and ?2",nativeQuery = true)
    List<Employee> findEmployeeByID (Integer startID, Integer endID);
}
