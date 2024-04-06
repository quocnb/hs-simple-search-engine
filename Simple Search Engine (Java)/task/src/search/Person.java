package search;

public class Person {
    private String firstName;
    private String lastName;
    private String email;

    public Person(String input) {
        String[] arr = input.split(" ");
        if (arr.length > 0) {
            firstName = arr[0];
        }
        if (arr.length > 1) {
            lastName = arr[1];
        }
        if (arr.length > 2) {
            email = arr[2];
        }
    }
    public boolean contains(String text) {
        return this.toString().toLowerCase().contains(text.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s",
                firstName == null ? "" : firstName,
                lastName == null ? "" : lastName,
                email == null ? "" : email
                ).trim();
    }
}
