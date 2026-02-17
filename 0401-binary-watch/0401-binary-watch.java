class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> list = new ArrayList<>();

        if(turnedOn > 8) return list;

        for(int hrs=0; hrs<12; hrs++){

            int hrsBit = Integer.bitCount(hrs);
            if(hrsBit > turnedOn) continue;

            for(int mins=0; mins<60; mins++){
                int minsBit = Integer.bitCount(mins);
                if(hrsBit+minsBit == turnedOn){
                    StringBuilder sb = new StringBuilder();
                    sb.append(hrs).append(":");

                    if(mins < 10){
                        sb.append("0");
                    }
                    sb.append(mins);

                    list.add(sb.toString());
                }
            }
        }

        return list;
    }
}