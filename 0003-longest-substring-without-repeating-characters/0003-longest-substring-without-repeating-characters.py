class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        char_map = {}
        max_length = 0
        left = 0

        for right in range(len(s)):
            char = s[right]

            # If the character is seen again and is within the current window
            if char in char_map and char_map[char] >= left:
                left = char_map[char] + 1  # Move left past the previous occurrence

            char_map[char] = right  # Update the last seen index
            max_length = max(max_length, right - left + 1)

        return max_length
        