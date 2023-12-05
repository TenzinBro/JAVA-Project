package com.example.group_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Payroll implements Serializable {
private static final long serialVersionUID = 2L;
private List<Employee> employees;

public Payroll() {
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