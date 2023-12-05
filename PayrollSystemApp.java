package com.example.group_project;

import com.example.group_project.Employee;
import com.example.group_project.EmployeeManagementSystem;
import com.example.group_project.PayrollSystem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class PayrollSystemApp extends Application {
    private EmployeeManagementSystem employeeManagementSystem;
    private PayrollSystem payrollSystem;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Payroll System");

        // Initialize EmployeeManagementSystem and PayrollSystem
        employeeManagementSystem = new EmployeeManagementSystem();
        payrollSystem = new PayrollSystem(new Payroll());

        // Create a grid pane for layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Employee Management section
        Label employeeLabel = new Label("Employee Management");
        GridPane.setConstraints(employeeLabel, 0, 0);
        grid.getChildren().add(employeeLabel);

        Button addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setOnAction(e -> manageEmployee());
        GridPane.setConstraints(addEmployeeButton, 0, 1);
        grid.getChildren().add(addEmployeeButton);

        Button updateEmployeeButton = new Button("Update Employee");
        updateEmployeeButton.setOnAction(e -> manageEmployee());
        GridPane.setConstraints(updateEmployeeButton, 0, 2);
        grid.getChildren().add(updateEmployeeButton);

        Button viewEmployeeButton = new Button("View Employees");
        viewEmployeeButton.setOnAction(e -> viewEmployees());
        GridPane.setConstraints(viewEmployeeButton, 0, 3);
        grid.getChildren().add(viewEmployeeButton);

        Button deleteEmployeeButton = new Button("Delete Employee");
        deleteEmployeeButton.setOnAction(e -> manageEmployee());
        GridPane.setConstraints(deleteEmployeeButton, 0, 4);
        grid.getChildren().add(deleteEmployeeButton);

        // Payroll Processing section
        Label payrollLabel = new Label("Payroll Processing");
        GridPane.setConstraints(payrollLabel, 1, 0);
        grid.getChildren().add(payrollLabel);

        Button calculatePayrollButton = new Button("Calculate Payroll");
        calculatePayrollButton.setOnAction(e -> processPayroll());
        GridPane.setConstraints(calculatePayrollButton, 1, 1);
        grid.getChildren().add(calculatePayrollButton);

        // Data Storage section
        Label dataStorageLabel = new Label("Data Storage");
        GridPane.setConstraints(dataStorageLabel, 2, 0);
        grid.getChildren().add(dataStorageLabel);

        Button saveDataButton = new Button("Save Data");
        saveDataButton.setOnAction(e -> saveData());
        GridPane.setConstraints(saveDataButton, 2, 1);
        grid.getChildren().add(saveDataButton);

        Button loadDataButton = new Button("Load Data");
        loadDataButton.setOnAction(e -> loadData());
        GridPane.setConstraints(loadDataButton, 2, 2);
        grid.getChildren().add(loadDataButton);

        // Reporting section
        Label reportingLabel = new Label("Reporting");
        GridPane.setConstraints(reportingLabel, 3, 0);
        grid.getChildren().add(reportingLabel);

        Button generateReportsButton = new Button("Generate Reports");
        generateReportsButton.setOnAction(e -> generateReports());
        GridPane.setConstraints(generateReportsButton, 3, 1);
        grid.getChildren().add(generateReportsButton);

        // Set up the scene
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    // Employee management actions
    private void manageEmployee() {
        Stage employeeStage = new EmployeeManagementStage(employeeManagementSystem);
        employeeStage.showAndWait();
    }

    // View all employees
    private void viewEmployees() {
        Stage viewStage = new EmployeeViewStage(employeeManagementSystem.getEmployees());
        viewStage.showAndWait();
    }

    // Payroll processing action
    private void processPayroll() {
        payrollSystem.calculatePayroll();
        System.out.println("Payroll calculated successfully.");
    }

    // Save data to a file using serialization
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("payroll_data.ser"))) {
            oos.writeObject(employeeManagementSystem);
            oos.writeObject(payrollSystem.getPayroll());
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load data from a file using serialization
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("payroll_data.ser"))) {
            employeeManagementSystem = (EmployeeManagementSystem) ois.readObject();
            payrollSystem = new PayrollSystem((Payroll) ois.readObject());
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Generate and display payroll reports
    private void generateReports() {
        List<Employee> employees = employeeManagementSystem.getEmployees();
        Stage reportStage = new PayrollReportStage(employees);
        reportStage.showAndWait();
    }
}

class EmployeeViewStage extends Stage {
    public EmployeeViewStage(List<Employee> employees) {
        setTitle("View Employees");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        ListView<String> employeeListView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Employee employee : employees) {
            items.add("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Hours Worked: " +
                    employee.getHoursWorked() + ", Hourly Rate: " + employee.getHourlyRate());
        }
        employeeListView.setItems(items);
        GridPane.setConstraints(employeeListView, 0, 0);

        grid.getChildren().add(employeeListView);

        Scene scene = new Scene(grid, 400, 250);
        setScene(scene);
    }
}

class PayrollReportStage extends Stage {
    public PayrollReportStage(List<Employee> employees) {
        setTitle("Payroll Report");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        ListView<String> payrollListView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Employee employee : employees) {
            items.add("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Total Pay: " +
                    calculateTotalPay(employee));
        }
        payrollListView.setItems(items);
        GridPane.setConstraints(payrollListView, 0, 0);

        grid.getChildren().add(payrollListView);

        Scene scene = new Scene(grid, 400, 250);
        setScene(scene);
    }

    private double calculateTotalPay(Employee employee) {
        // Implement your logic to calculate total pay (including bonuses, taxes, etc.) here
        return employee.getHoursWorked() * employee.getHourlyRate();
    }
}