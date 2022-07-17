# Intervals

Interval problems typically involve a list of intervals, each represented by a start and end time/position, and the goal is typically to detect or merge overlapping intervals.

The key concept in solving an interval problem is finding the overlap of two intervals.

## Fundamental Concepts

### 1. Determine if there's an overlap between two intervals

First, let's think in an opposite direction, how would the interval look like if they do NOT overlap?

The end time of the first interval must be earlier than the start of the second interval.

So the overlapping condition is opposite: `x2 >= y1 or y2 >= x1`

### 2. Finding the overlap

The key for finding the overlap of two intervals is `[max(x1, y1), min(x2, y2)]`.

Note that this means that an overlapping interval is `max(x1, y1) <= min(x2, y2)`.

Solving interval problems becomes much easier once we can find the overlap.

### 3. Sort by start time

It's also very common to sort intervals by start time (pre-processing).
