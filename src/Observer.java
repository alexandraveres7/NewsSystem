import java.util.ArrayList;

public interface Observer {
    public void registerListener();
    //public void removeListener(Article article);
    public void update(Event event);
    public ArrayList<Filter> getFilter();
}
