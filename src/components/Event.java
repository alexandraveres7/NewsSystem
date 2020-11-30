package components;

public class Event {
    private Article article;
    private EventType eventType;

    public Event(Article article, EventType eventType) {
        this.article = article;
        this.eventType = eventType;
    }

    public Article getArticle() {
        return article;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String toString() {
        return "components.Event type: " + this.eventType + "\ncomponents.Article: " + this.article;
    }
}
