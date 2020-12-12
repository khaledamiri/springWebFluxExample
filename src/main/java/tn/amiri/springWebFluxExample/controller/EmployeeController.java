package tn.amiri.springWebFluxExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tn.amiri.springWebFluxExample.models.Employee;
import tn.amiri.springWebFluxExample.service.EmployeeService;

/**
 * @author amiri
 * add Content-Type = application/json on every request
 */
@RestController()
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Employee>> findAll() {
        Flux<Employee> emps = employeeService.findAll();
        HttpStatus status = emps != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Flux<Employee>>(emps, status);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<Mono<Employee>>(e, status);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Employee e) {
        for (int i = 200; i < 900; i++) {
            e.setId(i);
            e.setName(e.getName());
            employeeService.create(e);

        }
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Employee>> update(@RequestBody Employee emp) {
        Mono<Employee> e = employeeService.findById(emp.getId());
        HttpStatus status = e.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        if (e.equals(Mono.empty())) {
            return new ResponseEntity<Mono<Employee>>(Mono.empty(), status);
        }
        return new ResponseEntity<Mono<Employee>>(employeeService.update(emp), status);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Void>> delete(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        if (e.equals(Mono.empty())) {
            return new ResponseEntity<Mono<Void>>(Mono.empty(), status);
        }
        return new ResponseEntity<Mono<Void>>(employeeService.delete(id), status);
    }
}