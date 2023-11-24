import java.io.Serializable;

public class Payroll implements Serializable{
    private Employee employee;
    private double hoursWorked;
    private double overtimeHours;

    public Payroll(Employee employee, double hoursWorked, double overtimeHours) {
        this.employee = employee;
        this.hoursWorked = hoursWorked;
        this.overtimeHours = overtimeHours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "employee=" + employee +
                ", hoursWorked=" + hoursWorked +
                ", overtimeHours=" + overtimeHours +
                '}';
    }
}
