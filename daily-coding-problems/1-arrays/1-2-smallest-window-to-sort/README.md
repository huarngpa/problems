# Local smallest window to be sorted

Given an array of integers that are out of order, determine the bounds of the smallest window that must be sorted in order for the entire array to be sorted. For example, given `[3, 7, 5, 6, 9]`, you should return `(1, 3)`.

## Solution 1: sorting

You can use a sorted reference copy to determine where the window bounds are. The first instance of an out-of-place element is your lower bound and the last out-of-place element is the upper bound. This is `O(n)` computation complexity.

## Solution 2: min-max

you can use the following invariants for an `O(n)` time and `O(1)` space complexity solution. When you walk up the array the values in the array should be in increasing if you discover a number that is less than the maximum seen you know it must be part of the window range, and vice versa when you walk backwards to find the lower bound of the window.
