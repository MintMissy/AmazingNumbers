import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        int addedLetters = 0;

        int vowelsInRow = 0;
        int consonantsInRow = 0;

        char[] charArray = word.toCharArray();
        for (char letter : charArray) {
            if (vowelsInRow == 2 && isVowel(letter)) {
                addedLetters++;
                vowelsInRow = 0;
            }

            if (consonantsInRow == 2 && !isVowel(letter)) {
                addedLetters++;
                consonantsInRow = 0;
            }

            if (isVowel(letter)) {
                vowelsInRow++;
                consonantsInRow = 0;
            }else{
                consonantsInRow++;
                vowelsInRow = 0;
            }
        }

        System.out.println(addedLetters);
    }

    private static  boolean isVowel(char letter){
        final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'y');
        return VOWELS.contains(letter);
    }
}