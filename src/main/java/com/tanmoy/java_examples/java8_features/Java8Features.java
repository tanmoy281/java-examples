package com.tanmoy.java_examples.java8_features;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java8Features {
    // Java 8 Features can be demonstrated here

    public static void main(String[] args) {
        //stream example with primitive data types
        //Example 1
        List<String> raw = List.of(
                " xenon  ", "XENON", "  ", "ai ", "midas", "  vpg",
                "Xenon", "Ai", "voice-mgr", "des", "DES  ", "  vpg  "
        );

        List<String> finalop=raw.stream().map(i-> i.trim()).filter(i->!i.isEmpty()).map(i->i.toUpperCase()).distinct().sorted().collect(Collectors.toList());
        //print the results
        System.out.println(finalop);

        //Example 2
        List<String> emails = List.of(
                "  Alice@example.com  ",
                "bob@EXAMPLE.com",
                "   ",
                "charlie@team.telstra.com",
                "dave@telstra",
                "eve@team.telstra.com ",
                "alice@example.com",          // duplicate of Alice (case-insensitive after normalization)
                "frank@tools.telstra.com ",
                " invalid@email ",            // no dot after @
                " GINA@TEAM.TELSTRA.COM "
        );
        Solution solution = new Solution();
        List<String> results = emails.stream().map(i->i.trim()).filter(i->!i.isEmpty()).map(i->i.toLowerCase()).filter(i->solution.isValidEmail(i)).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        //print the results
        System.out.println(results);

        /*#####################################################################*/

        // stream examples with custom objects

        record Employee(String name, int age, String department, double salary) {}
        List<Employee> employees = List.of(
                new Employee("Alice", 30, "HR", 60000),
                new Employee("Bob", 25, "IT", 70000),
                new Employee("Charlie", 35, "Finance", 80000),
                new Employee("David", 28, "IT", 72000),
                new Employee("Eve", 32, "HR", 4000)
        );
        //filter employees with salary > 5000, sort by salary descending and collect names
        List<String> employeeNames = employees.stream().filter(e ->  e.salary()>5000).sorted(Comparator.comparingDouble(Employee::salary).reversed()).map(Employee::name).collect(Collectors.toList());
        //print the results
        System.out.println(employeeNames);

        //Example 4: Average salary in HR department
         double averageHRSalaries = employees.stream().filter(e -> e.department().equals("HR")).mapToDouble(Employee::salary).average().orElse(0);
         //print the results
        System.out.println("Average HR Salary: " + averageHRSalaries);
    }
}

class Solution {
    boolean isValidEmail(String s){
        if (s.indexOf('@') >0 && s.indexOf('.')>s.indexOf('@') ){
            return true;
        }else{
            return false;
        }
    }
}
