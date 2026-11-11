package com.java;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

enum Category {
    FOOD,
    TRAVEL,
    SHOPPING,
    EDUCATION,
    BILLS,
    ENTERTAINMENT,
    OTHERS
}

class Expense {
    int id;
    String description;
    double amount;
    Category category;
    LocalDate date;

    Expense(int id, String description,
            double amount, Category category) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
    }

    void display() {
        System.out.println(
            id + " | " + description + " | ₹"
            + amount + " | " + category + " | " + date
        );
    }
}

public class personalExpense {

    static ArrayList<Expense> expenses =
        new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    // Add Expense
    static void addExpense() {

        System.out.print("Enter Expense ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        System.out.println("Select Category:");

        for (Category c : Category.values()) {
            System.out.println(c.ordinal() + ". " + c);
        }

        int choice = sc.nextInt();

        if (choice < 0 || choice >= Category.values().length
                || amount <= 0) {
            System.out.println("Invalid input!");
            return;
        }

        Category category = Category.values()[choice];

        expenses.add(
            new Expense(id, description, amount, category)
        );

        System.out.println("Expense Added Successfully!");
    }

    // Display Expenses
    static void displayExpenses() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses found!");
            return;
        }

        System.out.println("\nID | Description | Amount | Category | Date");

        for (Expense e : expenses) {
            e.display();
        }
    }

    // Delete Expense
    static void deleteExpense() {

        System.out.print("Enter Expense ID: ");
        int id = sc.nextInt();

        boolean removed = expenses.removeIf(
            e -> e.id == id
        );

        if (removed) {
            System.out.println("Expense Deleted!");
        } else {
            System.out.println("Expense Not Found!");
        }
    }

    // Calculate Total Expense
    static void totalExpense() {

        double total = expenses.stream()
            .mapToDouble(e -> e.amount)
            .sum();

        System.out.println("Total Expense: ₹" + total);
    }

    // Find Highest Expense
    static void highestExpense() {

        Optional<Expense> highest = expenses.stream()
            .max(Comparator.comparingDouble(e -> e.amount));

        if (highest.isPresent()) {
            System.out.println("Highest Expense:");
            highest.get().display();
        } else {
            System.out.println("No expenses found!");
        }
    }

    // Category-wise Expense
    static void categoryExpense() {

        HashMap<Category, Double> map =
            expenses.stream().collect(
                Collectors.groupingBy(
                    e -> e.category,
                    HashMap::new,
                    Collectors.summingDouble(e -> e.amount)
                )
            );

        System.out.println("\nCategory-wise Expenses:");

        for (Category c : Category.values()) {
            System.out.println(
                c + " : ₹" + map.getOrDefault(c, 0.0)
            );
        }
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Total Expense");
            System.out.println("5. Highest Expense");
            System.out.println("6. Category-wise Expense");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    deleteExpense();
                    break;

                case 4:
                    totalExpense();
                    break;

                case 5:
                    highestExpense();
                    break;

                case 6:
                    categoryExpense();
                    break;

                case 7:
                    System.out.println("Thank You!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
