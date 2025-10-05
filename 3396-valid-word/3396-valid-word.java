class Solution {
    public boolean isValid(String word) {
        if(word.length() < 3) return false;

        String vowels = "aeiouAEIOU";
        String consonants = "qwrtypsdfghjklzxcvbnmQWRTYPSDFGHJKLZXCVBNM";
        String allowed = vowels + consonants + "0123456789";
        boolean isVowelPresent = false;
        boolean isConsonantPresent = false;

        for(char ch : word.toCharArray()){
            if(vowels.indexOf(ch) != -1)
                isVowelPresent = true;
            if(consonants.indexOf(ch) != -1)
                isConsonantPresent = true;
            if(allowed.indexOf(ch) == -1)
                return false;
        }

        return isVowelPresent && isConsonantPresent;
    }
}