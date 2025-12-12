class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        events.sort((x, y) -> {
            int timeStampX = Integer.parseInt(x.get(1));
            int timeStampY = Integer.parseInt(y.get(1));
            if(timeStampX == timeStampY) return x.get(0).charAt(2) - y.get(0).charAt(2);
            return timeStampX - timeStampY;
        });

        int[] ans = new int[numberOfUsers];
        int[] onlineUntilTime = new int[numberOfUsers];

        int allMentionsCount = 0;

        for (List<String> event : events) {
            String eventType = event.get(0);
            int currentTime = Integer.parseInt(event.get(1));
            String content = event.get(2);
          
            if (eventType.charAt(0) == 'O') {
                int userId = Integer.parseInt(content);
                onlineUntilTime[userId] = currentTime + 60;
            } else if (content.charAt(0) == 'A') {
                allMentionsCount++;
            } else if (content.charAt(0) == 'H') {
                for (int i = 0; i < numberOfUsers; i++) {
                    if (onlineUntilTime[i] <= currentTime) {
                        ans[i]++;
                    }
                }
            } else {
                String[] userIds = content.split(" ");
                for (String userIdStr : userIds) {
                    int userId = Integer.parseInt(userIdStr.substring(2));
                    ans[userId]++;
                }
            }
        }

        if (allMentionsCount > 0) {
            for (int i = 0; i < numberOfUsers; i++) {
                ans[i] += allMentionsCount;
            }
        }
      
        return ans;
    }
}