class Solution(object):
    def romanToInt(self, s):
        length = len(s)
        roman = {"I":1,"V":5,"X":10,"L":50,"C":100,"D":500,"M":1000}
        integer = 0
        for i in range(length):
            if i<(length-1) and roman[s[i]]<roman[s[i+1]]:
                integer -=  roman[s[i]]
            
            else:
                integer += roman[s[i]]

        return integer


        