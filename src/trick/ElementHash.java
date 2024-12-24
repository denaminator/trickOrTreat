package trick;

import java.util.HashMap;

public class ElementHash {

    // Private var
    private HashMap<String, Integer> element = new HashMap<String, Integer>();

    // Constructor/getter
    public HashMap<String, Integer> hashMap (Graph graph) {
        // Make HashMap to contain index number of House in houses[]
        for (int i = 0; i < graph.houses.length; i++) {
            this.element.put(graph.houses[i].getName(), i);
        }
        return this.element;
    }
}
