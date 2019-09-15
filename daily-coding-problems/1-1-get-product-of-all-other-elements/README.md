# get product of all other elements

given an array of integers, return a new array such that each at index `i` of the new array is the product of all the numbers in the original array except the one at `i`

## solution

Our most efficient approach to solving this problem is the prefix and suffix accumulated product approach. This avoids the expensive divide operation and achieves a nice `O(n)` time and space solution.
