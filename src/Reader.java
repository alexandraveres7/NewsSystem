import java.util.ArrayList;

public class Reader implements Observer{
    private String name;
    private Dispatcher dispatcher = Dispatcher.getInstance();
    private ArrayList<Filter> filters;

    public Reader(String name){
        this.name = name;
    }

    @Override
    public void registerListener() {
        dispatcher.registerListener(this, EventType.CREATE);
        dispatcher.registerListener(this, EventType.MODIFY);
        dispatcher.registerListener(this, EventType.DELETE);
    }

    public void addFilterCriteria (String criteriaType, String criteria) {
        if (this.filters == null) {
            filters = new ArrayList<>();
            if (criteriaType.equals("Source"))
                filters.add(new SourceFilter(criteria));
            else if (criteriaType.equals("Domain"))
                filters.add(new DomainFilter(criteria));
        }
    }

    public ArrayList<Filter> getFilter () {
        return this.filters;
    }

    public void read(Article article){
    }

    @Override
    public void update(Event event) {
        String updateReader = this.name + " received the following UPDATE:\n" + event.getArticle().getTitle();

        if (event.getEventType().equals(EventType.CREATE)){
            System.out.println(updateReader + " has been published " + event.getArticle().getDateCreated() +
                    "\nauthor:"+event.getArticle().getAuthor() + " source: " + event.getArticle().getSource() + "\n");
        }
        else if (event.getEventType().equals(EventType.MODIFY)){
            System.out.println(updateReader + " has been modified " + event.getArticle().getDateLastModified() + "\n");
        }
        else if(event.getEventType().equals(EventType.DELETE)){
            System.out.println(updateReader + " has been deleted." + "\n");
        }
        else {
            System.out.println("\nUnknown event type\n"); }
    }
}
