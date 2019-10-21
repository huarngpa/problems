package solution

import (
	"reflect"
	"testing"
)

func SetupSimpleGraph() (*Node, []string) {
	D := Node{"D", nil}
	C := Node{"C", nil}
	B := Node{"B", []*Node{&C, &D}}
	A := Node{"A", []*Node{&B}}
	want := []string{"A", "B", "C", "D"}
	return &A, want
}

func compareSlices(t *testing.T, got, want []string) {
	t.Helper()

	if !reflect.DeepEqual(got, want) {
		t.Errorf("got: %v; want: %v", got, want)
	}
}

func TestSolutionSimpleRecursive(t *testing.T) {
	graph, want := SetupSimpleGraph()

	got := graph.DfsRecursive(make([]string, 0, 4))

	compareSlices(t, got, want)
}

func TestSolutionSimpleIterative(t *testing.T) {
	graph, want := SetupSimpleGraph()

	got := graph.DfsIterative(make([]string, 0, 4))

	compareSlices(t, got, want)
}
