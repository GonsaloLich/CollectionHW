package com.example.collectionhw;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int MAX_COUNT = 100;
    private final List<Employee> employees = new ArrayList<>(MAX_COUNT);

    public void add(String firstName, String lastName) {
        if (employees.size() >= MAX_COUNT) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if(employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(new Employee(firstName, lastName));

    }
    public void remove (String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)){
                employees.remove(employee);
                return;
            }
        }
        throw new EmployeeNotFoundException();
    }
    public Employee find(String firstName, String lastName){
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)){
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees);
    }
}
