class Solution {
    public String makeLargestSpecial(String s) {
        if(s == null || s.length() == 0) return "";

        int count = 0;
        int st = 0;
        List<String> list = new ArrayList<>();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '1') count++;
            else count--;

            if(count == 0){
                String inner = s.substring(st+1, i);
                String tmp = makeLargestSpecial(inner);

                list.add("1"+tmp+"0");

                st = i+1;
            }
        }

        Collections.sort(list, Collections.reverseOrder());

        return String.join("", list);
    }
}