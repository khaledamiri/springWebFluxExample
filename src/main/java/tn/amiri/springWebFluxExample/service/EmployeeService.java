package tn.amiri.springWebFluxExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tn.amiri.springWebFluxExample.models.Employee;
import tn.amiri.springWebFluxExample.repository.EmployeeRepository;

/**
 * @author amiri
 * 
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void create(Employee e) {
        employeeRepository.save(e);
    }

    public Mono<Employee> findById(Integer id) {
        return Mono.justOrEmpty(employeeRepository.findById(id));
    }

    public Flux<Employee> findAll() {
        return Flux.fromIterable(employeeRepository.findAll());
    }

    public Mono<Employee> update(Employee e) {
        return Mono.just(employeeRepository.save(e));
    }

    public Mono<Void> delete(Integer id) {
        employeeRepository.deleteById(id);
        return Mono.empty();
    }
}