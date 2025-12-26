class Solution {
    public int bestClosingTime(String customers) {
        int score = 0;
        int maxScore = 0;
        int bestClosingHour = 0;

        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                score++;
            } else {
                score--;
            }

            if (score > maxScore) {
                maxScore = score;
                bestClosingHour = i + 1;
            }
        }

        return bestClosingHour;

    }
}