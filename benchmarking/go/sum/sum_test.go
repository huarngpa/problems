package t

import "testing"

func BenchmarkThis(b *testing.B) {
	for i := 0; i < b.N; i++ {

		input := [...]int{1, 2, 3, 4, 6}

		sum := 0

		for i := range input {
			sum += input[i]
		}

	}
}

func BenchmarkThat(b *testing.B) {
	for i := 0; i < b.N; i++ {

		input := [...]int{1, 2, 3, 4, 6}

		sum := 0

		for _, i := range input {
			sum += i
		}

	}
}
