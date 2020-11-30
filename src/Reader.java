import java.util.ArrayList;
import java.util.Random;

public class Reader implements Observer {
    private String name;
    private Dispatcher dispatcher = Dispatcher.getInstance();
    private ArrayList<Filter> filters;
    private Random random = new Random();

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void registerListener() {
        dispatcher.registerListener(this, EventType.CREATE);
        dispatcher.registerListener(this, EventType.MODIFY);
        dispatcher.registerListener(this, EventType.DELETE);
    }

    public void addFilterCriteria(String criteriaType, String criteria) {
        if (this.filters == null) {
            this.filters = new ArrayList<>();
        }
        if (criteriaType.equals("Source")) {
            this.filters.add(new SourceFilter(criteria));
        } else if (criteriaType.equals("Domain")) {
            this.filters.add(new DomainFilter(criteria));
        }

    }

    public ArrayList<Filter> getFilter() {
        return this.filters;
    }

    public void randomRead(Article article) {
        String reader = this.name;
        Event event = new Event(article, EventType.READ);

        //This method is used for randomly deciding whether an article is read or not, to simulate user experience,
        //depending on the value returned by the function
        if (random.nextBoolean()) {
            System.out.println(reader + " reads article \"" + article.getTitle() + "\".\n");
            dispatcher.notifyListeners(event);
        } else {
            System.out.println(reader + " doesn't read article \"" + article.getTitle() + "\".\n\n");
        }
    }

    @Override
    public void update(Event event) {
        String updateReader = this.name + " received the following UPDATE:\n\"" + event.getArticle().getTitle();

        if (event.getEventType().equals(EventType.CREATE)) {
            System.out.println(updateReader + "\" has been published on " + event.getArticle().getDateCreated() +
                    ".\nAuthor: " + event.getArticle().getAuthor() + "   Source: " + event.getArticle().getSource() + "\n");
            randomRead(event.getArticle());
        } else if (event.getEventType().equals(EventType.MODIFY)) {
            System.out.println(updateReader + "\" has been modified on " + event.getArticle().getDateLastModified() + ".\n");
            randomRead(event.getArticle());
        } else if (event.getEventType().equals(EventType.DELETE)) {
            System.out.println(updateReader + "\" has been deleted." + "\n");
        } else {
            System.out.println("\nUnknown event type\n");
        }
    }
}
