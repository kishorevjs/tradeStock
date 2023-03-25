package com.example.tradestock.controller;

import com.example.tradestock.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(10, "Shore", 2000));
        employeeList.add(new Employee(20, "Vjs", 1200));
        employeeList.add(new Employee(30, "Jambalaya", 3000));

        EmployeeController employeeController = new EmployeeController();
        List<Employee> updatedSalaryList = employeeController.updateSalary(employeeList);

        for(Employee e : updatedSalaryList)
            System.out.println(e.toString());

    }

    public List<Employee> updateSalary(List<Employee> employees){

        return employees.stream()
                        .peek(employee -> employee.setSalary(employee.getSalary() * 1.1))
                        .filter(employee -> employee.getSalary() > 1200)
                        .toList();
    }
}