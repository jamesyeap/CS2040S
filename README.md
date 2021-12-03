# -------------------- DATA STRUCTURES  ----------------

## Tree

---

### Properties

- has $V$ vertices and $V-1$ edges

- contains no cycles

## AVL-Tree

---

### Properties

- if there are $n$ nodes in AVL tree,
  - minimum height of AVL tree is $floor(log_{2}(n))$
  - maximum height can’t exceed $1.44 \times (log_2(n))$
    
    
- if height of AVL tree is $h$, 
  - minimum number of nodes in a tree with height h can be represented as: $N(h) = N(h-1) + N(h-2) + 1$ 
    - for $n>2$, where $N(0) = 1$ and $ N(1) = 2$
  - maximum number of nodes is $2^{h+1} – 1$
    
    
- the complexity of searching, inserting and deletion in AVL tree is $O(log N)$

## Priority-Queue

---

also known as: Binary-Heap

- has 2 variants:
  
  - minimum heap:
    
    - root is the SMALLEST element in the entire binary-tree
    
    - all children of the node must be LARGER than it
  
  - maximum-heap:
    
    - root is the LARGEST element in the entire binary-tree
    
    - all children of the node must be SMALLER than it

### Properties

- there are $ceiling(\frac{n}{2})$ leaf-nodes in a binary-heap; where $n$ is the number of elements in the heap
  
  - this set of leaf-nodes is GUARANTEED to hold the:
    
    - LARGEST element in a minimum-heap
    
    - SMALLEST element in a maximum-heap
  
  - however, at this level (which is the last), they are not guaranteed to be ordered
    
    - so, to find the LARGEST/SMALLEST element, need to check through all leaf-nodes
      
      - which is an $O(n)$ operation

    

# ------------------ ALGORITHMS  ------------------

# Sorting

---

## Quickselect

Quickselect is a selection algorithm to find the $k^\text{th}$ smallest element in an unordered list.

### Time-Complexity

- $O(N)$ on average

# MST

---

## Prim's Algorithm

### Time-Complexity

$O(E \times logV)$

- for dense graphs where $E = O(V^2)$:
  
  - $O(V^2 \times logV)$

### Pseudo-Code

```
1. T <- {s}, a starting vertex s (usually vertex 0)
2. enqueue edges connected to s (only the other ending vertex and edge weight) into a priority queue PQ that orders elements based on increasing weight
3. while there are unprocessed edges left in PQ
        take out the front most edge e
        if vertex v linked with this edge e is not taken yet
            T <- T ∪ v (including this edge e)
            enqueue each edge adjacent to v into the PQ if it is not already in T
4.T is an MST
```

## Variant of Prim's Algorithm for Dense-Graphs

### Time-Complexity

- $O(V^2)$

### Psuedo-Code

```
1. Initialize A to <+inf,s>
2. A[s] = <0,s>
3. While not all vertices are in T
        Scan A to get v where A[v].first is minimum in A
        T <- T ∪ v and the adjoining edge
        A[v] = <+inf, A[v].second> // will not choose it again 
        for all u adjacent to v // all neighbors of v
            if (u is not in T && A[u].first > w(v,u))
              A[u] = <w(v,u),v>
4. T is an MST
```

## 

## Kruskal's Algorithm

### Time-Complexity

- $O(E \times log V)$

### Pseudo-Code

```
sort the set of E edges by increasing weight // O(N logN) 

T <- {}
while there are unprocessed edges left // O(E)
    pick an unprocessed edge e with min cost // O(1) 
    if adding e to T does not form a cycle // O(α(1))
        add e to the T // O(1)
T is an MST
```

# SSSP

---

## Modified BFS

### Time-Complexity

$O(V+E)$

### Pseudo-Code

```
/* INITIALISATION-PHASE */
for all v in V
    D[v] <- INF
    p[v] <- -1
D[s] <- 0
Q <- {s} // then, start from s 

/* MAIN-LOOP */
while Q is not empty
    u <- Q.dequeue()
    for all v adjacent to u // order of neighbor
        if D[v] = INF // influences BFS
            D[v] <- D[u]+1 // visitation sequence 
            p[v] <- u
            Q.enqueue(v)

// we can then use information stored in D[] and p[] to find
// shortest-distance and the actual-path that will yield such
// a shortest-distance
```

### Properties

- if graph is NOT a tree, then this algo only works for UNWEIGHTED graphs:
  
  - either edges have NO weights or all edges have the SAME weight

- however, if the graph is a tree, this algo would still give correct output

## Modified DFS

### Time-Complexity

$O(V+E)$

### Pseudo-Code

```
/* INITIALISATION-PHASE */
for all v in V
    D[v] <- INF
    p[v] <- -1
D[s] <- 0

/* MAIN-LOOP */
DFS(s)

/* RECURSIVE-FUNCTION */
DFS(u):
  for each neighbor v of u:
    if !visited[v]
       D[v] = D[u]+w(u,v) // relax this edge
       p[v] = u           // update the predecessor 
                          // also, if necessary, update some data structure

       DFS(v)             // then, continue to DFS on the neighbour v
```

### Properties

- only works for TREES (both weighted and unweighted)

## Bellman-Ford

### Time-Complexity

$O(V \times E)$

### Pseudo-Code

```
/* INITIALISATION-PHASE */
for all v in V
    D[v] <- INF
    p[v] <- -1
D[s] <- 0

/* MAIN-LOOP */
for i = 1 to |V|-1 // O(V) here
    for each edge(u, v) ∈ E // O(E) here 
        if D[v] > D[u]+w(u,v)  // if SP can be shortened
            D[v] = D[u]+w(u,v) // relax this edge
            p[v] = u           // update the predecessor 
                               // also, if necessary, update some data structure

// At the end of Bellman Ford's algorithm,
// D[v] = δ(s, v) if no negative weight cycle exist
```

### Properties

- for positively-weighted graphs, Bellman-Ford will run for at most V-1 passes

### Variations

- One-Pass Bellman-Ford

## One-Pass Bellman-Ford

### Time-Complexity

$O(V+E)$

### Pseudo-Code

```
/* INITIALISATION-PHASE */
for all v in V
    D[v] <- INF
    p[v] <- -1
sorted <- list of topologically-sorted vertices (using either Kahn's or DFS topo-sort algo)

D[sorted.first()] <- 0 // start from the FIRST-vertex in the
                       // topologically-sorted list of vertices

/* MAIN-LOOP */
for each vertex v in sorted:
    for each edge(u, v) in adjList.get(v): // relax all outgoing edges of vertex u
       if D[v] > D[u]+w(u,v)  // if SP can be shortened
       D[v] = D[u]+w(u,v) // relax this edge
       p[v] = u           // update the predecessor 
                          // also, if necessary, update some data structure
```

### Properties

- only works for Directed-Acyclic Graph (DAG)

## Modified Dijkstra's

### Time-Complexity

- $O(V \times logV))$

### Pseudo-Code

```
/* INITIALISATION-PHASE */
for all v in V
    D[v] <- INF
    p[v] <- -1
D[s] <- 0
PQ.enqueue((0, s)) // store pair of (dist[u], u)

/* MAIN-LOOP */
while PQ is not empty // order: increasing dist[u]
    (d, u) <- PQ.dequeue()
    if d == D[u] // important check, lazy DS
       for each vertex v adjacent to u
           if D[v] > D[u] + w(u, v) // if can relax
               D[v] = D[u] + w(u, v) // update the new shortest-distance to v
               p[v] = u              // update the predecessor-vertex to v
               PQ.enqueue((D[v], v)) // (re)enqueue this
```

### Properties

- does not work for graphs with NEGATIVE cycles
  
  - will go into an infinite-loop and never terminate

# APSP

---

## Floyd Warshall's APSP

### Time-Complexity

- $O(V^3)$

### Pseudo-Code

```
/* INITIALISATION-PHASE */
for all v in V:
    for all u in V:
        if v == u:
            D[v][u] <- 0
            p[v][u] <- v
        else:
            D[v][u] <- INF
            p[v][u] <- -1

/* MAIN-LOOP */
for (int k = 0; k < V; k++)         // remember, k first 
    for (int i = 0; i < V; i++)     // before i
        for (int j = 0; j < V; j++) // then j
            if D[i][k]+D[k][j] < D[i][j]: 
                D[i][j] = D[i][k]+D[k][j]
                p[i][j] = p[k][j]
```
