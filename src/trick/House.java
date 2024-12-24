package trick;
import java.util.*;


public class House {
    
    // Private vars
    private String name;
    private int weight;
    private House next;
    private ArrayList<Candy> candies = new ArrayList<Candy>();
    private int candyNum;

    // Constructors
    // Initialize House object w/o weight
    public House(String name, House next) {
        this.name = name;
        this.next = next;
    }
    // Initialize House object w/weight
    public House(String name, int weight, House next) {
        this.name = name;
        this.weight = weight;
        this.next = next;
    }

    // Getters and setters
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public int getWeight() { return this.weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public House getNext() { return this.next; }
    public void setNext(House next) { this.next = next; }

    public ArrayList<Candy> getCandies() {
        // Only return ArrayList if House is a vertex
        if (weight == 0) return this.candies;
        else return null;
    }
    public void addCandy(Candy candy) {
        // Only adds candy to candyList if House is a vertex
        if (weight == 0) candies.add(candy);
        else return;
    }

    public int getCandyNum() { return this.candyNum; }
    public void setCandyNum(int candyNum) { this.candyNum = candyNum; }
}
