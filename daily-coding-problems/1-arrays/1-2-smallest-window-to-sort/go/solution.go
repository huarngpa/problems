package main

import (
	"fmt"
	"sort"
)

func CopySlice(input []int) []int {
	if input == nil {
		return nil
	}
	res := make([]int, len(input))
	copy(res, input)
	return res
}

func Naive(input []int) (int, int) {
	sorted := CopySlice(input)
	sort.Ints(sorted)

	// bounds
	low := -1
	high := -1

	for i := range input {
		if low == -1 && input[i] != sorted[i] {
			low = i
		}
		if input[i] != sorted[i] {
			high = i
		}
	}

	return low, high
}

func Solution(input []int) (int, int) {
	// bounds
	low, high := -1, -1
	size := len(input)
	if size == 0 {
		return low, high
	}

	// min, max seen
	max := input[0]
	min := input[size-1]

	for i, num := range input {
		if num < max {
			high = i
		}
		if num > max {
			max = num
		}
	}

	for i := size - 1; i >= 0; i-- {
		num := input[i]
		if num > min {
			low = i
		}
		if num < min {
			min = num
		}
	}

	return low, high
}

func main() {
	example := []int{3, 7, 5, 6, 9}
	fmt.Printf("example: %v\n", example)

	low, high := Naive(example)
	fmt.Printf("naive: (%v, %v)\n", low, high)

	low, high = Solution(example)
	fmt.Printf("solution: (%v, %v)\n", low, high)
}
