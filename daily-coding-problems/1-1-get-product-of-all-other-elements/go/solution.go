package main

import "fmt"

func naive(input []int) []int {
	sum := 1
	for _, i := range input {
		sum *= i
	}
	for i := range input {
		input[i] = sum / input[i]
	}
	return input
}

func CopySlice(input []int) []int {
	if input == nil {
		return nil
	}
	res := make([]int, len(input))
	copy(res, input)
	return res
}

func Reverse(input []int) []int {
	size := len(input)
	res := CopySlice(input)
	for i := 0; i < size/2; i++ {
		opp := size - i - 1
		res[i] = input[opp]
		res[opp] = input[i]
	}
	return res
}

func AccumulateProducts(input []int) []int {
	res := make([]int, len(input))
	for i, num := range input {
		if i != 0 {
			res[i] = res[i-1] * num
		} else {
			res[i] = num
		}
	}
	return res
}

func Solution(input []int) []int {
	size := len(input)

	// Make prefix products
	prefix := AccumulateProducts(input)

	// Make suffix products
	reversed := Reverse(input)
	suffix := Reverse(AccumulateProducts(reversed))

	// Build the results
	res := make([]int, size)
	for i := range input {
		prev := i - 1
		next := i + 1
		res[i] = 1
		if prev >= 0 {
			res[i] *= prefix[prev]
		}
		if next < size {
			res[i] *= suffix[next]
		}
	}
	return res
}

func main() {
	var example = [...]int{1, 2, 3, 4, 5}
	fmt.Printf("input: %v\n", example)

	res := naive(CopySlice(example[:]))
	fmt.Printf("naive: %v\n", res)

	example = [...]int{1, 2, 3, 4, 5}
	res = Solution(example[:])
	fmt.Printf("solution: %v\n", res)
}
