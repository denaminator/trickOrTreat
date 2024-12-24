package trick;

public class Candy {
    
    // Private vars
    private String name;
    private int count;

    // Constructor to initialize Candy object
    public Candy(String name, int count) {
        this.name = name;
        this.count = count;
    }

    // Getters and setters
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public int getCount() { return this.count; }
    public void setCount(int count) { this.count = count; }
}
