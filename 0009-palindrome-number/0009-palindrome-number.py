class Solution(object):
    def isPalindrome(self, x):
        x1 = str(x)
        x2 = x1[::-1]
        length = len(x1)

        if length == 1:
            return True

        elif x1 == x2:
            return True

        else:
            return False
        