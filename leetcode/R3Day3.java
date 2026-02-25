public class R3Day3 {

        public int[] sortByBits(int[] arr) {
            int arrayLength = arr.length;
            for (int i = 0; i < arrayLength; ++i) {
                int originalValue = arr[i];
                int bitCount = Integer.bitCount(originalValue);
                arr[i] = originalValue + (bitCount * 100000);
            }

            Arrays.sort(arr);
            for (int i = 0; i < arrayLength; ++i) {
                arr[i] = arr[i] % 100000;
            }

            return arr;
        }
    }
