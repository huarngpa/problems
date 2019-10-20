# Three Number Sum

This problem extends the Two Sum problem by asking you to find all triplet sub-arrays that sum to some target number. Each triplet should be in ascending order and the triplets themselves should be in ascending order themselves.

Note that this problem usually asks us to only consider distinct integers.

Sample input: `[12, 3, 1, 2, -6, 5, -8, 6], 0`
Solution: `[[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]`

## Solution

It should be clear that we can easily do this in `O(n^3)` time and `O(n)` space. But is there a solution that allows us to do this quicker?

Yes, we use left and right pointer techniques to achieve `O(n^2)` time.
