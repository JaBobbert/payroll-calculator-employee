package com.pluralsight;

import java.io.*;
import java.util.Scanner;


public class payrollCalculator {
    public static void main(String[] args) {
        String fileName = "employees.csv";

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the name of the file to process");
        String inputFileName = scan.nextLine();

        System.out.println("Enter the name of the payroll to create: ");
        String outputFileName = scan.nextLine();

        try {


            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            String line;


            reader.readLine();


            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split("\\|");

                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee employee = new Employee(id, name, hoursWorked, payRate);
                String outputLine = "";


                outputLine = String.format("%d|%n|%.2%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                writer.write(outputLine);
            }
            reader.close();
            writer.close();
            scan.close();
        } catch (IOException e) {

            System.err.println("Error reading file: " + fileName);
        }
    }

}
