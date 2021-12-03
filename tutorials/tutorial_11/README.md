
# Problem 1. True or False?

## a) If a undirected graph has a unique minimum spanning tree, and we run Prim’s and any single source shortest path algorithm from the same source, the final result of edges chosen will form the same spanning tree.

NOT SURE.


## b) If the weights of all edges in a positively weighted graph are unique, there is always a unique shortest path (the smallest total cost is unique) from a source to a destination in such a graph.


## c) A connected, undirected graph will always form a shortest path tree with V − 1 edges.



## d) The shortest path from any vertex A to any vertex B is the combination of the shortest path from A to any vertex C and shortest path from C to vertex B.

## e) The algorithm below will still give the correct shortest path.
```
function Dijkstra(Graph , source):
	create vertex set Q
	for each vertex v in Graph: 
		dist[v] = INFINITY 
		prev[v] = UNDEFINED
		add v to Q
 	dist[source] = 0

	while Q is not empty:
		u = vertex in Q with min dist[u]
		
		remove u from Q

		for each neighbor v of u: // only v that are still in Q
			alt = dist[u] + length(u, v) 
			if alt < dist[v]:
				dist[v] = alt
				prev[v] = u 

	return dist[], prev[]
```

# Problem 2. Promoter

Topologically-sort the vertices.
- A B C D E 

In the topologically-sorted list, go to the vertex representing city A:
	for each vertex between city A and city B in this list (call such a vertex city X):
		dfs(city )


# Problem 3. Lego Mindstorms EV3 (2013/2014 CS2010 S1 WQ2 )

## a) What do the vertices and the edges of your graph represent?

## b) What is the upper bound of the number of vertices and edges in your graph?

## c) What is the graph problem that you want to solve?

## d) What is the most appropriate graph algorithm to solve this problem and it’s time complexity? (Note: What should the terms in the time complexity be expressed as?)


# Problem 4. Friends meeting up (2015/2016 CS2010 S2 WQ2)



# Problem 5. Multiple-Choice Questions

## a) Which of the following properties of a Binary Search Tree (BST) is false?

## b) Consider a connected, undirected graph G which can have two or more cycles, and a vertex X in the graph. Which of the following statements is true?

## c) Mark has designed a graph-based game. He will first create a directed unweighted graph with V vertices and E edges. He will then pick a source vertex s, a destination vertex d, and some number K. He will now start from s and place K on s. If s has M outgoing edges, he will divide K by M (integer division), and so each neighbor of s will have the value K/M placed on them. This will continue for each vertex with a value placed on them and will stop if d is reached, or the number K′ placed on the vertex v is such that K′/(number of outgoing edges of v) == 0. Mark wants to know what is the minimum value of K so that based on his game he can reach d. You can assume that there is always a path from x to y. The best algorithm to find the minimum value of K is equivalent to



