package trick;

import java.util.ArrayList;
import java.util.HashMap;

public class FindHouseWithMostCandy {

    public House mostCandy(Graph graph, FindTreatsRoute route, String c) {
        
        // Counter for largest number of candies and House w/this number
        int maxNum = 0;
        House maxHouse = null;

        /*
         * Check each House according to DFS traversal order
         * Call order[] that keeps track of DFS order sequentially
         */
        for (int i = 0; i < route.getOrder().length; i++) {
            // Temp pointer to check Candy contents of each House
            House ptr = graph.houses[route.getOrder()[i]];
            ArrayList<Candy> candies = ptr.getCandies();

            // Check each of the Candy objects within House ArrayList
            for (int j = 0; j < candies.size(); j++) {
                // If Candy names match
                if (candies.get(j).getName().equals(c)) {
                    // If ptr's Candy number is larger than max, update counters
                    if (candies.get(j).getCount() > maxNum) {
                        maxNum = candies.get(j).getCount();
                        maxHouse = ptr;
                        break;
                    }
                }
            }
        }

        return maxHouse;
    }
    public static void main(String[] args) {

        // Invalide terminal entry
        if (args.length < 4) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Execute: java -cp bin trick.FindHouseWithMostCandy input1.in h1 Skittles findcandy1.out");
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

        // Instantiate DFS route and arrays used in DFS
        FindTreatsRoute route = new FindTreatsRoute();
        route.setLength(houses.length);
        route.dfs(graph, element, houses[element.get(args[1])]);

        // Initialize self object to call non-static methods
        FindHouseWithMostCandy mostCandy = new FindHouseWithMostCandy();

        // Find House with max number of candy of target Candy and print
        House max = mostCandy.mostCandy(graph, route, args[2]);
        StdOut.setFile(args[3]);
        StdOut.println(max.getName());
        
    }
}
