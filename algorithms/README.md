# Top Interview Algorithms

- Depth first search
- Breadth first search
- Matching parenthesis (stack)
- Hash-table (supports caching or memoization)
- Manipulate variable pointers
- Reverse linked list
- Sorting fundamentals (merge, heap, quick-sort)
- Select
- Recursion
- Custom data structures (suffix tree)
- Binary search

## Depth First Search

```
DFS(G):
  for each u in G.V:
    d[u] = time
    color[u] = White
    p[u] = Nil
  time = 0
  for each u in G.V:
    if color[u] == White:
      DFS-Visit(G, u)

DFS-Visit(G, u):
  time = time + 1
  d[u] = time
  color[u] = Gray
  for each v in G.Adj[u]:
    if color[v] == White:
      p[v] = u
      DFS-Visit(G, v)
  color[u] = Black
```

## Breadth First Search

```
BFS(Q, s):
  for each u in G.V:
    d[u] = inf
    color[u] = White
    p[u] = Nil
  d[s] = 0
  color[s] = Gray
  Q = empty
  Enqueue(Q, s)
  while Q not empty:
    u = Dequeue(Q)
    for v in G.Adj[u]:
      if color[v] == White:
        d[v] = d[u] + 1
        p[v] = u
        color[v] = Gray
        Enqueue(Q, v)
    color[u] = Black
```
