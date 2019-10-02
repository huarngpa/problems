package main

import (
	"fmt"
	"reflect"
)

// Naive is a O(w * s) solution
func Naive(w string, s string) []int {
	size := len(s)
	res := make([]int, 0, size)

	buildMap := func(input string) map[rune]int {
		m := make(map[rune]int)
		for _, c := range input {
			m[c]++
		}
		return m
	}

	word := buildMap(w)

	for i := 0; i < size-(len(w)-1); i++ {
		counter := buildMap(s[i : i+len(w)])
		if reflect.DeepEqual(word, counter) {
			res = append(res, i)
		}
	}

	return res
}

// Solution uses sliding window approach which is an O(s) solution
func Solution(w string, s string) []int {
	size := len(s)
	res := make([]int, 0, size)

	ref := make([]int, 26)
	curr := make([]int, 26)

	for _, c := range w {
		ref[int(c)-int('a')]++
	}

	for j := 0; j < size; j++ {
		i := j - len(w) + 1
		// Slide a window across string, end of the window increments
		end := int(s[j]) - int('a')
		curr[end]++

		if reflect.DeepEqual(curr, ref) {
			res = append(res, i)
		}

		// Your window needs to clean up after it procs a character
		if i >= 0 {
			beg := int(s[i]) - int('a')
			curr[beg]--
		}
	}

	return res
}

func main() {
	w := "ab"
	s := "abxaba"

	fmt.Printf("w: %q\n", w)
	fmt.Printf("s: %q\n", s)

	fmt.Printf("naive: %v\n", Naive(w, s))
	fmt.Printf("solution: %v\n", Solution(w, s))
}
