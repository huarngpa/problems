package t

import "testing"

func BenchmarkThis(b *testing.B) {
	for i := 0; i < b.N; i++ {
		input := [...]int{1, 2, 3, 4, 5}

		res := make([]int, len(input))
		copy(res, input[:])
	}
}

func BenchmarkThat(b *testing.B) {
	for i := 0; i < b.N; i++ {
		input := [...]int{1, 2, 3, 4, 5}

		slice := input[:]
		res := append(slice[:0:0], slice...)
		something(res)
	}
}

func something(input []int) {}
