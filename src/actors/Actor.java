package actors;

import components.Event;
import filters.Filter;

import java.util.ArrayList;

public interface Actor {
    public void registerListener();
    public void update(Event event);
    public ArrayList<Filter> getFilter();
}
