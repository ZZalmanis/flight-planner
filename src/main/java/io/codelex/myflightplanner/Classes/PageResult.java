package io.codelex.myflightplanner.Classes;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
    private int page;
    private int totalItems;
    private List<Flight> items;

    public int getPage() {
        return page;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<Flight> getItems() {
        return items;
    }

    public PageResult() {
        this.page = 0;
        this.totalItems = 0;
        this.items = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        items.add(flight);
        totalItems = items.size();
    }

    @Override
    public String toString() {
        return "{" +
                "\"items\": " + items +
                ", \"page\": " + page +
                ", \"totalItems\": " + totalItems +
                '}';
    }
}
