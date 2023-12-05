package com.example.group_project;

import java.io.Serializable;

public class PayrollSystem implements Serializable {
    private static final long serialVersionUID = 4L;
    private Payroll payroll;

    public PayrollSystem(Payroll payroll) {
        this.payroll = payroll;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void calculatePayroll() {
        for (Employee employee : payroll.getEmployees()) {
            double basePay = employee.getHoursWorked() * employee.getHourlyRate();
            double overtimePay = calculateOvertimePay(employee);
            double bonus = calculateBonus(employee);
            double totalPay = basePay + overtimePay + bonus;

            // Deduct taxes
            double taxedPay = deductTaxes(totalPay);

            // You can update the Employee object or do something with the calculated payroll
            System.out.println("Employee ID: " + employee.getId() +
                    ", Name: " + employee.getName() +
                    ", Total Pay: " + taxedPay);
        }
    }

    private double calculateOvertimePay(Employee employee) {
        // Assuming overtime is paid at 1.5 times the hourly rate for hours worked beyond 40 hours per week
        double standardHours = 40;
        if (employee.getHoursWorked() > standardHours) {
            double overtimeHours = employee.getHoursWorked() - standardHours;
            return overtimeHours * 1.5 * employee.getHourlyRate();
        }
        return 0;
    }

    private double calculateBonus(Employee employee) {
        // Sample logic for bonus calculation (replace with your own criteria)
        if (employee.getHoursWorked() > 45) {
            return 100; // Bonus amount in this example
        }
        return 0;
    }

    private double deductTaxes(double totalPay) {
        // Sample logic for tax deduction (replace with your own tax calculation method)
        double taxRate = 0.2; // 20% tax rate in this example
        return totalPay * (1 - taxRate);
    }
}
