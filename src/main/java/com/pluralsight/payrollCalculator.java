package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class payrollCalculator {
    public static void main(String[] args) {
        String fileName = "employees.csv";

        // Reading a file can throw an IOException, so the work happens inside try
        try {
            // FileReader opens the file; BufferedReader wraps it so we can read whole lines
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Skip the header line: "id" is not a number and parseInt would fail on it
            reader.readLine();

            // readLine() returns null at the end of the file, which ends the loop
            while ((line = reader.readLine()) != null) {
                // | has a special meaning in split (it takes a pattern), so it is escaped as \\|
                String[] tokens = line.split("\\|");
                // Everything read from a file is text: convert each value to its real type
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee employee = new Employee(id, name, hoursWorked, payRate);

                // %.2f shows the pay as money, two decimals
                System.out.printf("Employee ID: %d, Name: %s, Gross Pay: $%.2f%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
            }
            reader.close();
        } catch (IOException e) {
            // Most likely cause: employees.csv is not in the project folder
            System.err.println("Error reading file: " + fileName);
        }
    }

}
