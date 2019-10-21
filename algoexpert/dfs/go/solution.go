package solution

// Node data structure
type Node struct {
	Name     string
	Children []*Node
}

// DfsRecursive a recursive implementation of DFS
func (n *Node) DfsRecursive(array []string) []string {
	array = append(array, n.Name)
	if n.Children == nil {
		return array
	}
	for _, child := range n.Children {
		array = child.DfsRecursive(array)
	}
	return array
}

// DfsIterative is an iterative approach to DFS
func (n *Node) DfsIterative(array []string) []string {
	stack := make([]*Node, 0, 256)
	stack = append(stack, n)
	for len(stack) > 0 {
		size := len(stack)
		curr := stack[size-1]
		stack = stack[:size-1]
		// Do-op
		array = append(array, curr.Name)
		// Put children on stack (reversed)
		if curr.Children == nil {
			continue
		}
		for i := len(curr.Children) - 1; i >= 0; i-- {
			stack = append(stack, curr.Children[i])
		}
	}
	return array
}
