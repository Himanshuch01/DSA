public class NewDay4 {
    class Solution {
        public String makeLargestSpecial(String s) {
            if (s.isEmpty()) {
                return "";
            }

            List<String> specialSubstrings = new ArrayList<>();
            int balance = 0;
            int startIndex = 0;

            for (int currentIndex = 0; currentIndex < s.length(); currentIndex++) {
                if (s.charAt(currentIndex) == '1') {
                    balance++;
                } else {
                    balance--;
                }

                if (balance == 0) {
                    String innerContent = makeLargestSpecial(
                            s.substring(startIndex + 1, currentIndex)
                    );
                    String specialString = "1" + innerContent + "0";
                    specialSubstrings.add(specialString);
                    startIndex = currentIndex + 1;
                }
            }

            specialSubstrings.sort(Comparator.reverseOrder());
            return String.join("", specialSubstrings);
        }
    }

}
