package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = getFilePathFromArguments(args);
        List<Person> people = readDataFromFile(filePath);

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

    static String getFilePathFromArguments(String[] args) {
        boolean readFilePath = false;
        for (String arg : args) {
            if (readFilePath) {
                return arg;
            }
            if ("--data".equals(arg)) {
                readFilePath = true;
            }
        }
        return "";
    }

    static List<Person> readDataFromFile(String filePath) {
        ArrayList<Person> people = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                people.add(new Person(scanner.nextLine()));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return people;
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
