import java.util.ArrayList;
import java.util.HashMap;

public class Dispatcher {

    private static Dispatcher dispatcher = null;
    private HashMap<EventType, ArrayList<Observer>> eventListeners = new HashMap<>();

    private Dispatcher(){}

    public static Dispatcher getInstance(){
        if (dispatcher == null) {
            synchronized (Dispatcher.class) {
                if (dispatcher == null) {
                    dispatcher = new Dispatcher();
                }
            }
        }
        return dispatcher;
    }

    public void registerListener(Observer observer, EventType eventType){
        try{
            if (this.eventListeners.containsKey(eventType)){
                eventListeners.get(eventType).add(observer);
            }
            else{
                ArrayList<Observer> newListeners = new ArrayList<>();
                newListeners.add(observer);
                eventListeners.put(eventType, newListeners); }
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void notifyListeners(Event event){
        boolean criteriaMet = true;
        ArrayList<Observer> observers = this.eventListeners.get(event.getEventType());
        try{
            for (Observer observer: observers)
                if (observer.getFilter() == null)
                    observer.update(event);
                else {
                    for (Filter filter : observer.getFilter())
                        if (!filter.applyFilter(event.getArticle()))
                            criteriaMet = false;
                    if (criteriaMet)
                        observer.update(event);
                }
                criteriaMet = true;
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}