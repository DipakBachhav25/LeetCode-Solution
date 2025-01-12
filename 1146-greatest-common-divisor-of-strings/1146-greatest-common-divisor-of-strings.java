class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1)) return "";

        int m = str1.length();
        int n = str2.length();

        while(n != 0){
            int temp = n;
            n = m%n;
            m = temp;
        }
        return str1.substring(0, m);

    }
}