# General functions used in all SSSP algorithms
```
initSSSP(s):
	for each v ∈ V // initialization phase
		D[v] <- 1000000000 // use 1 billion to represent INF
		p[v] <- -1 // use -1 to represent NULL
	D[s] <- 0 // this is what we know so far
```

```
relax(u, v, w(u,v)):
	if D[v] > D[u]+w(u,v) // if SP can be shortened
		D[v] <- D[u]+w(u,v) // relax this edge
		p[v] <- u // remember/update the predecessor 
		// if necessary, update some data structure
```

# BFS SSSP
O(V+E)

## Description
BFS SSSP basically a modified version of BFS MST, with only 1 modification:
### in BFS MST,
```
if visited[v] = 0:
	visited[v] = 1
	...
```
### in BFS SSSP,
```
if D[v] = INF:
	D[v] = D[u] + 1
```
## Limitation:
Only works for unweighted graphs! -> all edges must have the same weight.


# Bellman-Ford SSSP
O(VE)

## Description
Basically, for each e ∈ E: 
	e = (u, v, weight)

	if D[u] == INF: 
		skip because no path exists to u yet
	else:
		check if going from vertex u to vertex v is shorter than previous attempts of getting to vertex v
			if D[v] > D[u] + e.weight: D[v] = D[u] + e.weight

Repeat the above V times.

## Limitation:
Works for weighted graphs with negative-edges; EXCEPT where the negative-edges are part of a cycle (aka Negative-Cycle).

You will know if there is a Negative-Cycle if, at the end of Bellman-Ford, when you comb through all the edges, you find any pair of vertices (u, v) such that they can be relaxed further.

# One-Pass Bellman-Ford SSSP

## Description


## Limitation


# Dijkstra's SSSP
O( (V+E) * logV )


## Description


## Limitation



# Modified Dijkstra's SSSP

## Description


## Limitation





# Special Cases
When to use what, for what.

## Weighted-Graph is a Tree


## Graph is unweighted
• For SSSP on unweighted graph, we can only use BFS
• For SSSP on tree, we can use either DFS/BFS

## Weighted-Graph is DAG
One-pass Bellman Ford

## Weighted-Graph has no Negative-Weight edges
- Use Dijkstra's instead of Bellman Ford (because Djikstra's has better Time-Complexity)






