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
        return "Event type: " + this.eventType + "\nArticle: " + this.article;
    }
}
