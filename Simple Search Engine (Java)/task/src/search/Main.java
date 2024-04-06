package search;

import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("\nEnter the number of search queries:");
        int numberOfQueries = Integer.parseInt(scanner.nextLine());

        while (numberOfQueries > 0) {
            System.out.println("\nEnter data to search people:");
            String searchKeyword = scanner.nextLine();

            int found = 0;
            for (Person person : people) {
                if (person.contains(searchKeyword)) {
                    found += 1;
                    if (found == 1) {
                        System.out.println("Found people:");
                    }
                    System.out.println(person);
                }
            }
            if (found == 0) {
                System.out.println("No matching people found.");
            }
            numberOfQueries -= 1;
        }
    }

}
