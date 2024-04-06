package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter all people:");
        ArrayList<Person> people = new ArrayList<>();
        while (numberOfPeople > 0) {
            people.add(new Person(scanner.nextLine()));
            numberOfPeople -= 1;
        }
        int menu = 0;
        do {
            System.out.println();
            System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");
            menu = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (menu) {
                case 0:
                    break;
                case 1: {
                    System.out.println("Enter a name or email to search all suitable people.");
                    String searchKeyword = scanner.nextLine();
                    List<Person> searchResult = searchPerson(people, searchKeyword);
                    if (searchResult.isEmpty()) {
                        System.out.println("not found");
                    } else {
                        printPeople(searchResult);
                    }
                }
                break;
                case 2: {
                    System.out.println("=== List of people ===");
                    printPeople(people);
                }
                break;
                default: {
                    System.out.println("Incorrect option! Try again.");
                }
                    break;
            }
        } while (menu != 0);
        System.out.println("Bye!");
    }

    static void printPeople(List<Person> people) {
        people.forEach(s -> System.out.println(s.toString()));
    }

    static List<Person> searchPerson(List<Person> people, String keyword) {
        return people.stream()
                .filter(s -> s.contains(keyword))
                .collect(Collectors.toList());
    }
}
