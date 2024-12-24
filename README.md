# trickOrTreat
A Halloween-inspired project using graphs and other data structures to determine information about hypothetical neighborhoods for trick-or-treaters :)

## Breakdown of Classes:
Each class reads an input file (of type .in) and writes to an output file (of type .out), and can be run independently of each other.

### NeighborhoodMap
- Initializes a graph of the given neighborhood
- For each house in the neighborhood, displays candy content and list of adjacent houses (+ weight of edges, ie. distance in minutes between houses).

### FindTreatsRoute
- Implements depth-first search (DFS) to find a route from a source house to as many other houses as possible
- Returns source house and DFS ordering of all reachable houses

### FindHouseWithMostCandy
- Uses the DFS ordering to find the house along the accessible route computed in FindTreatsRoute with the most candy of an inputted candy type

### PathToMostCandy
- Implements breadth-first search (BFS) to find a route from a source house to the house with the most candy
- BFS can also find the shortest path from a source house to all other houses
- Returns source house and BFS-ordered route to house with most candy

### ShortestPath
- Implements Dijkstra's algorithm to find the shortest route (in minutes using edge weight) from a source house to all other houses in the neighborhood
- Returns each house in the neighborhood and its predecessor found with Dijkstra's algo

### CanAvoidCurfew
- Uses the shortest path calculated with Dijkstra's algo to determine if a route between two houses is under the curfew time (inputted in terminal)
- Returns whether the total weight of this route is less than the curfew time, or if the trick-or-treaters can avoid curfew
