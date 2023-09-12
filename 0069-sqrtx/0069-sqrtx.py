class Solution(object):
    def mySqrt(self, x):
        start = 0
        end = x
        mid = int

        while True:
            mid = int((start+end)//2)

            if x == 1:
                mid = 1
                break

            if start>=end or mid == start or mid == end:
                break

            if (mid*mid) == x:
                break

            if (mid*mid) > x:
                end = mid

            else:
                start = mid

        return mid
        