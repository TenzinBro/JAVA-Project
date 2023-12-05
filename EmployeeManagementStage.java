package com.example.group_project;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeManagementStage extends Stage {
    private EmployeeManagementSystem employeeManagementSystem;

    public EmployeeManagementStage(EmployeeManagementSystem employeeManagementSystem) {
        this.employeeManagementSystem = employeeManagementSystem;
        setTitle("Employee Management");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameField = new TextField();
        GridPane.setConstraints(nameField, 1, 0);

        Label hoursLabel = new Label("Hours Worked:");
        GridPane.setConstraints(hoursLabel, 0, 1);
        TextField hoursField = new TextField();
        GridPane.setConstraints(hoursField, 1, 1);

        Label rateLabel = new Label("Hourly Rate:");
        GridPane.setConstraints(rateLabel, 0, 2);
        TextField rateField = new TextField();
        GridPane.setConstraints(rateField, 1, 2);

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            double hours = Double.parseDouble(hoursField.getText());
            double rate = Double.parseDouble(rateField.getText());
            Employee newEmployee = new Employee(name, hours, rate);
            employeeManagementSystem.addEmployee(newEmployee);
            clearFields(nameField, hoursField, rateField);
        });
        GridPane.setConstraints(addButton, 0, 3);

        Button updateButton = new Button("Update Employee");
        updateButton.setOnAction(e -> {
            if (!employeeManagementSystem.getEmployees().isEmpty()) {
                Employee firstEmployee = employeeManagementSystem.getEmployees().get(0);
                firstEmployee.setName(nameField.getText());
                firstEmployee.setHoursWorked(Double.parseDouble(hoursField.getText()));
                firstEmployee.setHourlyRate(Double.parseDouble(rateField.getText()));
            }
            clearFields(nameField, hoursField, rateField);
        });
        GridPane.setConstraints(updateButton, 1, 3);

        Button deleteButton = new Button("Delete Employee");
        deleteButton.setOnAction(e -> {
            
            if (!employeeManagementSystem.getEmployees().isEmpty()) {
                employeeManagementSystem.deleteEmployee(employeeManagementSystem.getEmployees().get(0));
            }
            clearFields(nameField, hoursField, rateField);
        });
        GridPane.setConstraints(deleteButton, 0, 4);

        grid.getChildren().addAll(nameLabel, nameField, hoursLabel, hoursField, rateLabel, rateField, addButton, updateButton, deleteButton);

        Scene scene = new Scene(grid, 400, 250);
        setScene(scene);
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}
