package trick;

import java.util.HashMap;

// import java.util.ArrayList;


public class FindTreatsRoute {

    // Arrays to keep track during DFS
    private boolean[] marked;
    private int[] edgeTo;
    private int[] order;
    private int count = 0;

    // Searches graph using DFS
    public void dfs (Graph graph, HashMap<String, Integer> element, House v) {

        // Check off current house as visited
        marked[element.get(v.getName())] = true;
        order[count] = element.get(v.getName());
        count++;

        // Check House objects in adjacency list of given House vertex
        for (House w: graph.adj(v)) {

            // If adjacent House is unvisited, mark current House as its edge and do recursive call
            if (!marked[element.get(w.getName())]) {
                edgeTo[element.get(w.getName())] = element.get(v.getName());
                dfs(graph, element, w);
            }
        }
    }

    // Getters and setters
    public boolean[] getMarked() { return this.marked; }
    public int[] getEdges() { return this.edgeTo; }
    public int[] getOrder() { return this.order; }

    public void setLength(int length) {
        this.order = new int[length];
        this.marked = new boolean[length];
        this.edgeTo = new int[length];
    }


    public static void main(String[] args) {

        // Invalid terminal entry
        if (args.length < 3) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Run command: java -cp bin trick.FindTreatsRoute input1.in h1 findtreats1.out");
            return;
        }
        
        // Instantiates graph and adds adjacencies
        NeighborhoodMap map = new NeighborhoodMap();
        House[] houses = map.setHouses(args[0]);
        Graph graph = new Graph(houses);
        map.addAdjacencies(args[0], graph);

        // Make HashMap to contain index number of House in houses[]
        ElementHash hash = new ElementHash();
        HashMap<String, Integer> element = hash.hashMap(graph);

        // Initialize self object and DFS tracking arrays
        FindTreatsRoute route = new FindTreatsRoute();
        route.setLength(houses.length);
        route.dfs(graph, element, houses[element.get(args[1])]);

        // Prints out DFS traversal order
        StdOut.setFile(args[2]);
        for (int i = 0; i < route.order.length; i++) {
            int pos = route.order[i];
            StdOut.print(houses[pos].getName() + " ");
        }
    }
}
