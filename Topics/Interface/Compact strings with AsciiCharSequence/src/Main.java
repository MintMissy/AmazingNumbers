import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class AsciiCharSequence implements CharSequence {

    private final byte[] value;

    @Override
    public int length() {
        return value.length;
    }

    @Override
    public char charAt(int index) {
        return (char) value[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {

        return new AsciiCharSequence(Arrays.copyOfRange(value, start, end));
    }

    @Override
    public String toString() {

        return new String(value);
    }

    AsciiCharSequence(byte[] input) {

        value = Arrays.copyOf(input, input.length);
    }
}
