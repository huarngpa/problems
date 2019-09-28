package main

import "fmt"

// Naive is O(n^3)
func Naive(input []int) int {
	size := len(input)
	max := 0
	for i := range input {
		for j := i + 1; j <= size; j++ {
			sum := 0
			for _, num := range input[i:j] {
				sum += num
			}
			if sum > max {
				max = sum
			}
		}
	}
	return max
}

// Solution uses Kadane's algorithm to efficiently find a solution
func Solution(input []int) int {
	maxSeen := 0
	maxHere := 0
	for _, num := range input {
		maxHere += num
		if maxHere < 0 {
			maxHere = 0
		}
		if maxHere > maxSeen {
			maxSeen = maxHere
		}
	}
	return maxSeen
}

// MinSubArray finds the min continuous subarray
func MinSubArray(input []int) int {
	minSeen := 0
	minHere := 0
	for _, num := range input {
		minHere += num
		if minHere > 0 {
			minHere = 0
		}
		if minHere < minSeen {
			minSeen = minHere
		}
	}
	return minSeen
}

// SolutionCircular minimum subarray
func SolutionCircular(input []int) int {
	sum := 0
	for _, num := range input {
		sum += num
	}
	minSubarray := MinSubArray(input)
	circularSolution := sum - minSubarray
	solution := Solution(input)
	if circularSolution > solution {
		solution = circularSolution
	}
	return solution
}

func main() {
	ex1 := []int{34, -50, 42, 14, -5, 86}
	ex2 := []int{-5, -1, -8, -9}

	fmt.Printf("example1: %v\n", ex1)
	fmt.Printf("example2: %v\n", ex2)

	fmt.Printf("naive1: %v\n", Naive(ex1))
	fmt.Printf("naive2: %v\n", Naive(ex2))

	fmt.Printf("solution1: %v\n", Solution(ex1))
	fmt.Printf("solution2: %v\n", Solution(ex2))

	fmt.Printf("circular1: %v\n", SolutionCircular(ex1))
	fmt.Printf("circular2: %v\n", SolutionCircular(ex2))
}
