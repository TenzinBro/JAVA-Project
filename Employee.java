import java.io.Serializable;

public class Employee implements Serializable{
    static private int idGenerator;
    private int employeeId;
    private String employeeName;
    private double hourlyRate;

    public static void setIdGenerator(int idGenerator){
        Employee.idGenerator = idGenerator;
    }

    public Employee(String employeeName, double hourlyRate) {
        this.employeeName = employeeName;
        this.hourlyRate = hourlyRate;
        this.employeeId = ++idGenerator;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}
