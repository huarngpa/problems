package main

import (
	"fmt"
	"math"
	"sort"
)

// Naive is a O(n^2) solution, can we do better?
func Naive(input []int) []int {
	size := len(input)
	res := make([]int, size)

	for i := range input {
		smaller := 0
		for j := i + 1; j < size; j++ {
			if input[j] < input[i] {
				smaller += 1
			}
		}
		res[i] = smaller
	}
	return res
}

// Solution is a O(n lg n) solution, sort
func Solution(input []int) []int {
	size := len(input)
	sorted := make([]int, size)
	res := make([]int, size)

	for i := range res {
		sorted[i] = math.MaxInt32
	}

	for i := size - 1; i >= 0; i-- {
		num := input[i]
		lookupTo := size - 1 - i
		numLessThan := sort.Search(lookupTo, func(i int) bool { return sorted[i] > num })
		res[i] = numLessThan
		// Possibly can approve the performance here?
		sorted = append(sorted[:numLessThan], append([]int{num}, sorted[numLessThan:]...)...)
	}

	return res
}

func main() {
	example := []int{3, 4, 9, 6, 1}

	fmt.Printf("example: %v\n", example)

	fmt.Printf("naive: %v\n", Naive(example))
	fmt.Printf("solution: %v\n", Solution(example))
}
