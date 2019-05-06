package edu.nyu.algo2.thread.safe;

public class Employee {

    private final String name;

    private final double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double raiseSalary(double newSalary) {
        throw new UnsupportedOperationException();
    }

}
