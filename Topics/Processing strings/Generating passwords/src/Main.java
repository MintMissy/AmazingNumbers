import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int uppercase = scanner.nextInt();
        int lowercase = scanner.nextInt();
        int digits = scanner.nextInt();
        int requiredLength = scanner.nextInt();

        StringBuilder password = new StringBuilder();

        while (lowercase > 0) {
            boolean status = addCharacter(password, getLowercase());
            if (status) {
                lowercase--;
            }
        }

        while (uppercase > 0) {
            boolean status = addCharacter(password, getUppercase());
            if (status) {
                uppercase--;
            }
        }

        while (digits > 0) {
            boolean status = addCharacter(password, getDigit());
            if (status) {
                digits--;
            }
        }

        while (password.length() < requiredLength) {
            addCharacter(password, getLowercase());
        }

        System.out.println(password);
    }

    private static boolean addCharacter(StringBuilder password, char character) {
        if (password.length() == 0){
            password.append(character);
            return true;
        }

        if (password.charAt(password.length() - 1) != character) {
            password.append(character);
            return true;
        }
        return false;
    }

    private static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    private static Character getDigit() {
        return (char) getRandomNumber(48, 57);
    }

    private static Character getUppercase() {
        return (char) getRandomNumber(65, 90);
    }

    private static Character getLowercase() {
        return (char) getRandomNumber(97, 122);
    }
}