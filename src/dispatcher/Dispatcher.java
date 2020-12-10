package dispatcher;

import actors.Actor;
import components.Event;
import components.EventType;
import filters.Filter;

import java.util.ArrayList;
import java.util.HashMap;

public class Dispatcher {

    private static Dispatcher dispatcher = null;
    private HashMap<EventType, ArrayList<Actor>> eventListeners = new HashMap<>();

    private Dispatcher() {
    }

    public static Dispatcher getInstance() {
        if (dispatcher == null) {
            synchronized (Dispatcher.class) {
                if (dispatcher == null) {
                    dispatcher = new Dispatcher();
                }
            }
        }
        return dispatcher;
    }

    public void registerListener(Actor actor, EventType eventType) {
        try {
            if (this.eventListeners.containsKey(eventType)) {
                eventListeners.get(eventType).add(actor);
            } else {
                ArrayList<Actor> newListeners = new ArrayList<>();
                newListeners.add(actor);
                eventListeners.put(eventType, newListeners);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void notifyListeners(Event event) {
        boolean criteriaMet = true;
        ArrayList<Actor> actors = this.eventListeners.get(event.getEventType());
        try {
            for (Actor actor : actors) {
                if (actor.getFilter() == null) {
                    actor.update(event);
                } else {
                    for (Filter filter : actor.getFilter()) {
                        if (!filter.applyFilter(event.getArticle())) {
                            criteriaMet = false;
                        }
                        if (criteriaMet) {
                            actor.update(event);
                        }
                        criteriaMet = true;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}