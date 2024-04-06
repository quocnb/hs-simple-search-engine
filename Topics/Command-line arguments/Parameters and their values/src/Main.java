class Problem {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        String key = "";
        for (String arg : args) {
            if (key.isEmpty()) {
                key = arg;
            } else {
                result.append(String.format("%s=%s\n", key, arg));
                key = "";
            }
        }
        System.out.print(result.toString());
    }
}