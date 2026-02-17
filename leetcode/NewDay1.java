public class NewDay1 class Solution {

    public int reverse(int x) {
        int reversedNumber = 0;

        while (x != 0) {

            if (reversedNumber > Integer.MAX_VALUE / 10 || reversedNumber < Integer.MIN_VALUE / 10) {
                return 0;
            }


            int lastDigit = x % 10;
            reversedNumber = reversedNumber * 10 + lastDigit;


            x = x / 10;
        }

        return reversedNumber;
    }
}{
}
