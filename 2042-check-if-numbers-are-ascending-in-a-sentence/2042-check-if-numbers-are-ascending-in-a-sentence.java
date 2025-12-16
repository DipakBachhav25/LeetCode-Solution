class Solution {
    public boolean areNumbersAscending(String s) {
        int previousNumber = -1;

        for (String token : s.split(" ")) {
            if (Character.isDigit(token.charAt(0))) {
                int currentNumber = Integer.parseInt(token);

                if (currentNumber <= previousNumber) {
                    return false;
                }
                previousNumber = currentNumber;
            }
        }
        return true;

    }
}