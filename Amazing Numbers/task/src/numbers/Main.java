package numbers;

import java.util.*;

import static java.lang.Long.parseLong;
import static numbers.Main.NumberTypes.SAD;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printStartMessage();

        long naturalNumber = 1;
        long followingNumbers = 0;

        ArrayList<NumberTypes> searchedTypes = new ArrayList<>();
        ArrayList<NumberTypes> notSearchedTypes = new ArrayList<>();

        while (naturalNumber != 0) {
            System.out.print("Enter a request: ");

            String line = scanner.nextLine();
            String[] arguments = line.split(" ");
            int numberOfArguments = arguments.length;

            naturalNumber = parseLong(arguments[0]);
            searchedTypes.clear();
            notSearchedTypes.clear();

            if (naturalNumber < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }

            if (numberOfArguments > 1) {
                followingNumbers = parseLong(arguments[1]);
                if (followingNumbers < 0) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
            }

            // Data input logic
            if (numberOfArguments > 2) {
                // Check if given number types are valid
                ArrayList<String> invalidNumbersTypes = new ArrayList<>();
                for (int i = 2; i < numberOfArguments; i++) {
                    String numberType = arguments[i].replace("-", "").toUpperCase();
                    if (!NumberTypes.isInEnum(numberType)) {
                        invalidNumbersTypes.add(numberType);
                    }
                }

                // TODO implement new errors
                // Check if given properties can be NumberTypes enum values
                if (invalidNumbersTypes.size() == 1) {
                    System.out.println("The property " + invalidNumbersTypes + " is wrong.");
                    System.out.println("Available properties: [" + NumberTypes.getAllTypes() + "]");
                    continue;
                } else if (invalidNumbersTypes.size() >= 2) {
                    System.out.println("The properties " + invalidNumbersTypes + " are wrong.");
                    System.out.println("Available properties: [" + NumberTypes.getAllTypes() + "]");
                    continue;
                }

                for (int i = 2; i < numberOfArguments; i++) {
                    String numberType = arguments[i].toUpperCase();
                    if (numberType.startsWith("-")) {
                        notSearchedTypes.add(NumberTypes.valueOf(numberType.replace("-", "")));
                    } else {
                        searchedTypes.add(NumberTypes.valueOf(numberType));
                    }
                }

                // Check if given properties aren't mutually exclusive
                boolean areTypesMismatched = false;

                ArrayList<List<NumberTypes>> mutuallyExclusiveTypes1 = new ArrayList<>();
                mutuallyExclusiveTypes1.add(Arrays.asList(NumberTypes.HAPPY, NumberTypes.SAD));
                mutuallyExclusiveTypes1.add(Arrays.asList(NumberTypes.SAD, NumberTypes.HAPPY));

                for (List<NumberTypes> exclusiveTypes : mutuallyExclusiveTypes1) {
                    int containedTypes = 0;
                    if (searchedTypes.contains(exclusiveTypes.get(0)) &&
                            notSearchedTypes.contains(exclusiveTypes.get(1))) {
                        containedTypes++;
                    }

                    if (containedTypes == 1) {
                        System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n",
                                exclusiveTypes.get(0),
                                "-" + exclusiveTypes.get(1));
                        System.out.println("There are no numbers with these properties.");
                        areTypesMismatched = true;
                    }
                }

                if (areTypesMismatched) {
                    continue;
                }

                ArrayList<List<NumberTypes>> mutuallyExclusiveTypes = new ArrayList<>();
                mutuallyExclusiveTypes.add(Arrays.asList(NumberTypes.ODD, NumberTypes.EVEN));
                mutuallyExclusiveTypes.add(Arrays.asList(NumberTypes.SUNNY, NumberTypes.SQUARE));
                mutuallyExclusiveTypes.add(Arrays.asList(NumberTypes.SPY, NumberTypes.DUCK));
                mutuallyExclusiveTypes.add(Arrays.asList(NumberTypes.HAPPY, NumberTypes.SAD));

                for (List<NumberTypes> exclusiveTypes : mutuallyExclusiveTypes) {
                    int containedTypes = 0;
                    for (NumberTypes exclusiveType : exclusiveTypes) {
                        if (searchedTypes.contains(exclusiveType)) {
                            containedTypes++;
                        }
                    }

                    if (containedTypes == 2) {
                        System.out.println("The request contains mutually exclusive properties: " + exclusiveTypes);
                        System.out.println("There are no numbers with these properties.");
                        areTypesMismatched = true;
                    }
                }

                if (areTypesMismatched) {
                    continue;
                }

                for (List<NumberTypes> exclusiveTypes : mutuallyExclusiveTypes) {
                    int containedTypes = 0;
                    for (NumberTypes exclusiveType : exclusiveTypes) {
                        if (notSearchedTypes.contains(exclusiveType)) {
                            containedTypes++;
                        }
                    }

                    if (containedTypes == 2) {
                        System.out.printf("The request contains mutually exclusive properties: [-%s, -%s]\n",
                                exclusiveTypes.get(0), exclusiveTypes.get(1));
                        System.out.println("There are no numbers with these properties.");
                        areTypesMismatched = true;
                    }
                }

                if (areTypesMismatched) {
                    continue;
                }

                for (NumberTypes type : searchedTypes) {
                    int containedTypes = 0;
                    if (notSearchedTypes.contains(type)) {
                        containedTypes++;
                    }

                    if (containedTypes == 1) {
                        System.out.printf("The request contains mutually exclusive properties: [%s, -%s]\n", type, type);
                        System.out.println("There are no numbers with these properties.");
                        areTypesMismatched = true;
                    }
                }

                if (areTypesMismatched) {
                    continue;
                }
            }

            // Program Logic
            if (numberOfArguments == 1) {
                if (naturalNumber == 0) {
                    System.out.println("Goodbye!");
                    break;
                }

                printSingleNumberProperties(naturalNumber);

            } else if (arguments.length == 2) {
                for (long i = naturalNumber; i < naturalNumber + followingNumbers; i++) {
                    ArrayList<NumberTypes> numberProperties = getNumberProperties(i);
                    printNumberProperties(i, numberProperties);
                }
            } else if (arguments.length >= 3) {
                int numbersCounter = 0;
                while (numbersCounter < followingNumbers) {
                    ArrayList<NumberTypes> numberProperties = getNumberProperties(naturalNumber);

                    int matchedPropertiesRequirements = 0;
                    for (NumberTypes type : searchedTypes) {
                        if (numberProperties.contains(type)) {
                            matchedPropertiesRequirements++;
                        }
                    }

                    for (NumberTypes type : notSearchedTypes) {
                        if (!numberProperties.contains(type)) {
                            matchedPropertiesRequirements++;
                        }
                    }

                    if (matchedPropertiesRequirements == searchedTypes.size() + notSearchedTypes.size()) {
                        printNumberProperties(naturalNumber, numberProperties);
                        numbersCounter++;
                    }

                    naturalNumber++;
                }
            }
        }
    }

    private static void printSingleNumberProperties(long naturalNumber) {
        System.out.printf("Properties of %d%n", naturalNumber);
        System.out.printf("\teven: %b%n", isEven(naturalNumber));
        System.out.printf("\todd: %b%n", !isEven(naturalNumber));
        System.out.printf("\tbuzz: %b%n", isBuzzNumber(naturalNumber));
        System.out.printf("\tduck: %b%n", isDuckNumber(naturalNumber));
        System.out.printf("\tpalindromic: %b%n", isNumberPalindrome(naturalNumber));
        System.out.printf("\tgapful: %b%n", isGapfulNumber(naturalNumber));
        System.out.printf("\tspy: %b%n", isSpyNumber(naturalNumber));
        System.out.printf("\tsunny: %b%n", isSunnyNumber(naturalNumber));
        System.out.printf("\tsquare: %b%n", isSquareNumber(naturalNumber));
        System.out.printf("\tjumping: %b%n", isJumpingNumber(naturalNumber));
        System.out.printf("\thappy: %b%n", isHappyNumber(naturalNumber));
        System.out.printf("\tsad: %b%n", !isHappyNumber(naturalNumber));
    }

    private static void printStartMessage() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public enum NumberTypes {
        EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE, JUMPING, HAPPY, SAD;

        public static String getAllTypes() {
            StringBuilder allTypes = new StringBuilder();
            for (NumberTypes type : NumberTypes.values()) {
                if (allTypes.toString().equals("")) {
                    allTypes = new StringBuilder(type.toString());
                } else {
                    allTypes.append(", ").append(type.toString());
                }
            }
            return allTypes.toString();
        }

        private static boolean isInEnum(String value) {
            for (NumberTypes type : NumberTypes.values()) {
                if (type.name().equals(value)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static void printNumberProperties(long number, ArrayList<NumberTypes> numberProperties) {
        if (numberProperties.size() <= 1) {
            System.out.println(number + " is " + numberProperties.get(0));
        } else {
            StringBuilder propertiesString = new StringBuilder(numberProperties.get(0).toString().toLowerCase());
            for (int j = 1; j < numberProperties.size(); j++) {
                String property = numberProperties.get(j).toString().toLowerCase();
                propertiesString.append(", ").append(property);
            }
            System.out.println(number + " is " + propertiesString);
        }
    }

    private static ArrayList<NumberTypes> getNumberProperties(long number) {
        ArrayList<NumberTypes> numberProperties = new ArrayList<>();
        if (isEven(number)) numberProperties.add(NumberTypes.EVEN);
        if (!isEven(number)) numberProperties.add(NumberTypes.ODD);
        if (isBuzzNumber(number)) numberProperties.add(NumberTypes.BUZZ);
        if (isDuckNumber(number)) numberProperties.add(NumberTypes.DUCK);
        if (isNumberPalindrome(number)) numberProperties.add(NumberTypes.PALINDROMIC);
        if (isGapfulNumber(number)) numberProperties.add(NumberTypes.GAPFUL);
        if (isSpyNumber(number)) numberProperties.add(NumberTypes.SPY);
        if (isSquareNumber(number)) numberProperties.add(NumberTypes.SQUARE);
        if (isSunnyNumber(number)) numberProperties.add(NumberTypes.SUNNY);
        if (isJumpingNumber(number)) numberProperties.add(NumberTypes.JUMPING);
        if (isHappyNumber(number)) numberProperties.add(NumberTypes.HAPPY);
        if (!isHappyNumber(number)) numberProperties.add(SAD);
        return numberProperties;
    }

    private static long getSumOfDigitSquares(long number) {
        long sum = 0;
        while (number > 0) {
            sum += Math.pow(number % 10, 2);
            number /= 10;
        }
        return sum;
    }

    private static boolean isHappyNumber(long number) {
        ArrayList<Long> knownNumbers = new ArrayList<Long>();
        knownNumbers.add(number);
        while (true) {
            number = getSumOfDigitSquares(number);
            if (number == 1) {
                return true;
            }
            if (knownNumbers.contains(number)) {
                return false;
            } else {
                knownNumbers.add(number);
            }
        }
    }

    private static boolean isJumpingNumber(long number) {
        long previousDigit = number % 10;
        number /= 10;
        while (number > 0) {
            long lastDigit = number % 10;
            if (Math.abs(lastDigit - previousDigit) != 1) {
                return false;
            }
            number /= 10;
            previousDigit = lastDigit;
        }
        return true;
    }

    private static boolean isSunnyNumber(long number) {
        return Math.sqrt(number + 1) % 1 == 0;
    }

    private static boolean isSquareNumber(long number) {
        return Math.sqrt(number) % 1 == 0;
    }

    private static boolean isSpyNumber(long number) {
        long product = 1;
        long sum = 0;

        while (number > 0) {
            product *= number % 10;
            sum += number % 10;
            number /= 10;
        }
        return product == sum;
    }

    private static long getFirstDigit(long number) {
        while (number >= 10) {
            number /= 10;
        }
        return number;
    }

    private static boolean isGapfulNumber(long number) {
        if (number < 100) {
            return false;
        }

        long lastDigit = number % 10;
        long firstDigit = getFirstDigit(number);

        long divisor = firstDigit * 10 + lastDigit;
        return number % divisor == 0;
    }

    private static boolean isNumberPalindrome(long number) {
        long copiedNumber = number;
        int reversedNumber = 0;

        while (number > 0) {
            int digit = (int) (number % 10);
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }
        return reversedNumber == copiedNumber;
    }

    private static boolean isDuckNumber(long number) {
        while (number > 0) {
            if (number % 10 == 0) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

    private static boolean isEven(long number) {
        return number % 2 == 0;
    }

    private static boolean isBuzzNumber(long number) {
        boolean endsWith7 = number % 10 == 7;
        boolean isDivisibleBySeven = isDivisibleBy7(number);

        return endsWith7 || isDivisibleBySeven;
    }

    private static boolean isDivisibleBy7(long number) {
        int lastNumber = (int) (number % 10);
        long startNumbers = number / 10;
        return (startNumbers - (lastNumber * 2)) % 7 == 0;
    }
}
