package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private final Service service;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.create(employee);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Integer id) {

        Employee employee = service.getById(id);
        return employee;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {

        return service.updateById(id, employee);
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }

    //@PatchMapping("/replaceNull")
    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull(){
        service.processor();
    }

    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text){
        service.sendEmailByCountry(country, text);
    }

    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text){
        service.sendEmailByCity(city, text);
    }

    @PostMapping("/autoFillData")
    @ResponseStatus(HttpStatus.OK)
    public void autoFillData(@RequestParam String name, @RequestParam String country,@RequestParam String email){
        service.autoFillData(name, country, email);
    }

    @PostMapping ("/randomUpdateDataByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void randomUpdateDataByCountry(@RequestParam Integer startID, @RequestParam Integer endID){
        service.randomUpdateDataByCountry(startID,endID);
    }

    @PostMapping ("/smartUpdateDataByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void smartUpdateDataByCountry(@RequestParam Integer startID, @RequestParam Integer endID, @RequestParam String country){
        service.smartUpdateDataByCountry(startID,endID,country);
    }
}

