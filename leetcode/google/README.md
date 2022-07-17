# Other Interesting Google Questions

## CPU Scheduling

https://leetcode.com/discuss/interview-question/2062557/Google-Interview-Experience

## Array With K-Replacements

https://leetcode.com/discuss/interview-question/2064289/Google-interview-question

```
Given an integer array a, and 2 integers - c and k, where c is the favourite number and k is the maximum number of replacements we can make. We have to find the maximum number of contiguous elements which are equal to c. We can replace k number of elements with any number we want.

Example - a = [1,2,2,3,2,2,2,4], c=2, k=2.

* In this case, without any replacement, maximum number of contiguous elements will be 3 (from index 4 to index 6).
* We can replace element at index 3 with number 2 so now array will be a = [1,2,2,2,2,2,2,4] , maximum number of contiguous elements will be 6 (from index 1 to index 6).
* For last replacement, we can replace either element at index 0 or index 7 with number 2, so maximum number of contiguous elements will be 7, which is the answer..
```

- We can solve this with two-pointers (will be O(n^2))
- Then we can optimize this with a queue that keeps track of the last index we replaced

## Robot Sort

https://leetcode.com/discuss/interview-question/2061238/Google-or-Onsite

```
You have boxes in magazine and a single empty spot (at the end) like:

1, 4, 2, 5, 3, _

You have a robot which needs to sort boxes. It can only pick up single box, put it in empty box and then pick another one up. Sort boxes to have either _, 1, 2, 3, 4, 5 or 1, 2, 3, 4, 5, _
What is the fastest way to sort if robot knows immediately where are boxes with specific numbers, but it takes time for him to move.
```

- Ultimately what we want to do is run Djikstra
- Basically we first need to pre-process/process the state transition graph
- Then we can run BFS
- How can we do better?
  - Maybe we can use an A\* or Djikstra-like algorithm to provide weights
  - Only process a few state transitions ahead?

## Reverse Polish Notation But With Assignment

https://leetcode.com/discuss/interview-question/2051711/Google-or-SE

For example:

```
b 5 = a b = a 5 + return 10
```

- Basically just use a HashMap with the Stack and keep track of variables

## Ring Topology

https://leetcode.com/discuss/interview-question/1989087/Google-or-Onsite

```
Graph is given as adj list format, return nodes order starting from any node.
Input:
[A: [B, D]
B: [A, C]
C: [B, D]
D: [A, C]]

Output: ABCD
Assumption: Graph represents Ring topology find the optimized way to traverse.
Follow up: Check if Graph represent ring topology check for all corner cases.
```

- Basically use a modified DFS approach and keep track of when you hit a cycle
- You should have visited all the nodes
- Note that the problem sorts itself when you need to actually check if it maintains ring topology
