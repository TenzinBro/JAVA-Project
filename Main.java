import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner keyboard = new Scanner(System.in);
    static ArrayList<Employee> employeeList = new ArrayList<>();
    public static void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("employee.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(employeeList);

        oos.flush();
        fos.close();
    }

    public static void load() throws IOException, ClassNotFoundException {
        Path p = Paths.get("employee.data");

        if(Files.exists(p)){
            FileInputStream fis = new FileInputStream("employee.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            employeeList = (ArrayList<Employee>) ois.readObject();

            Employee.setIdGenerator(
                    employeeList.get(
                    employeeList.size()-1).getEmployeeId());

            fis.close();
        }
        else {
            Employee.setIdGenerator(0);
        }
    }

    public static String employeeMenu(){
        System.out.println("-------Employee Management System-------");
        System.out.println("1 - Add Employee to system");
        System.out.println("2 - View All the Employee");
        System.out.println("3 - Update Employee information");
        System.out.println("4 - Delete Employee from system");
        System.out.println("5 - Exit");
        return keyboard.nextLine();
    }

    private static void addEmployee() throws IOException {
        System.out.println("Enter the name of employee: ");
        String name = keyboard.nextLine();
        System.out.println("Enter the hourly rate of employee");
        double rate = Double.parseDouble(keyboard.nextLine());
        Employee e = new Employee(name, rate);
        employeeList.add(e);
        System.out.println(e);
        System.out.println(" is added!");
        save();
    }

    private static void viewAllEmployee(){
        for (Employee e:employeeList) {
            System.out.println(e);
        }
    }

    private static void updateEmployee() throws IOException {
        System.out.println("Enter the Employee ID: ");
        int id = Integer.parseInt(keyboard.nextLine());



        for (int i = 0; i<employeeList.size(); i++) {

            if (employeeList.get(i).getEmployeeId() == id) {

                System.out.println("Employee Found!");
                System.out.println(employeeList.get(i));
                System.out.println("Enter 1 to the change the name, 2 to change hourly rate," +
                        "3 to change both!");
                String input = keyboard.nextLine();
                if (input.equals("1")) {
                    System.out.println("Enter the new name: ");
                    employeeList.get(i).setEmployeeName(keyboard.nextLine());
                    System.out.println("Employee name is changed!");
                } else if (input.equals("2")) {
                    System.out.println("Enter the new hourly rate: ");
                    employeeList.get(i).setHourlyRate(Double.parseDouble(keyboard.nextLine()));
                    System.out.println("Employee's hourly rate is changed!");
                } else if (input.equals("3")) {
                    System.out.println("Enter the new name: ");
                    employeeList.get(i).setEmployeeName(keyboard.nextLine());
                    System.out.println("Enter the new hourly rate: ");
                    employeeList.get(i).setHourlyRate(Double.parseDouble(keyboard.nextLine()));
                    System.out.println("Employee's name and hourly rate are changed");
                } else {
                    System.out.println("Bad input! Start over!");
                }
                save();
            }
        }

    }

    private static void deleteEmployee() throws IOException {
        System.out.println("Enter the Employee ID to delete: ");
        String idToDelete = keyboard.nextLine();

        Employee employeeToDelete = null;
        for (Employee employee : employeeList) {
            if (employee.getEmployeeId() == Integer.parseInt(idToDelete)) {
                employeeToDelete = employee;
                break;
            }
        }
        if (employeeToDelete != null) {
            employeeList.remove(employeeToDelete);
            System.out.println("Employee ID " + idToDelete + " is deleted");
            save();
        } else {
            System.out.println("Employee ID " + idToDelete + " not found.");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        load();
        String choice;
        while(true){
            choice = employeeMenu();
            if (choice.equals("1")) addEmployee();
            else if (choice.equals("2")) viewAllEmployee();
            else if (choice.equals("3")) updateEmployee();
            else if (choice.equals("4")) deleteEmployee();
            else if (choice.equals("5")) break;
            else System.out.println("Invalid Input!");
        }
    }
}
