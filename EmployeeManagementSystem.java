package com.example.group_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementSystem implements Serializable {
    private static final long serialVersionUID = 3L;
    private List<Employee> employees;

    public EmployeeManagementSystem() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void deleteEmployee(Employee employee) {
        employees.remove(employee);
    }
}
