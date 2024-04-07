package search;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = getFilePathFromArguments(args);
        SearchUtils searchUtils = new SearchUtils(filePath);

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
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    SearchUtils.SearchMode searchMode = SearchUtils.SearchMode.valueOf(scanner.nextLine().toUpperCase());

                    System.out.println("Enter a name or email to search all suitable people.");
                    String[] searchKeywords = scanner.nextLine().split(" ");
                    List<String> searchResult = searchUtils.search(searchKeywords, searchMode);
                    if (searchResult.isEmpty()) {
                        System.out.println("No matching people found.");
                    } else {
                        System.out.printf("%d persons found:\n", searchResult.size());
                        System.out.println(String.join("\n", searchResult));
                    }
                }
                break;
                case 2: {
                    System.out.println("=== List of people ===");
                    System.out.println(String.join("\n", searchUtils.getAllData()));
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
}
