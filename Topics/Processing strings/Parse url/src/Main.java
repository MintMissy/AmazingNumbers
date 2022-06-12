import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        String parametersUrl = url.split("\\?")[1];
        String[] parameters = parametersUrl.split("&");

        String password = "";

        for (String parameter : parameters) {
            String key;
            String value;
            if (parameter.split("=").length != 2) {
                key = parameter.split("=")[0];
                value = "not found";
            } else {
                key = parameter.split("=")[0];
                value = parameter.split("=")[1];
            }
            System.out.println(key + " : " + value);

            if (key.equals("pass")) {
                password = value;
            }
        }
        if (password.length() > 0) {
            System.out.println("password : " + password);
        }
    }
}