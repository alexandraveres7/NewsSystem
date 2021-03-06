package actors;

import components.Article;
import components.Event;
import components.EventType;
import dispatcher.Dispatcher;
import filters.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Editor implements Actor {
    private String name, editorialOffice;
    private Dispatcher dispatcher = Dispatcher.getInstance();
    private ArrayList<Article> articles = new ArrayList<>();

    public Editor(String name, String editorialOffice) {
        this.name = name;
        this.editorialOffice = editorialOffice;
    }

    public void update(Event event) {
        if (event.getEventType().equals(EventType.READ)) {
            Article article = event.getArticle();
            article.read();
            System.out.println(article.getAuthor() + "'s article \"" + article.getTitle() + "\" has been read " + "\nTotal views: " +
                    article.getViews() + "\n\n");
        } else {
            System.out.println("Unknown event type\n");
        }
    }

    @Override
    public ArrayList<Filter> getFilter() {
        return null;
    }

    public void registerListener() {
        dispatcher.registerListener(this, EventType.READ);
    }

    public void publishArticle(String title, String text, String domain, String subdomain) {
        Article newArticle = new Article(title, text, domain, subdomain, this.editorialOffice, this.name);
        articles.add(newArticle);
        Event event = new Event(newArticle, EventType.CREATE);
        dispatcher.notifyListeners(event);
    }

    public void modifyArticle(String title, String text) {
        Article modifiedArticle = null;
        for (Article article : this.articles) {
            if (article.getTitle().equals(title)) {
                article.modifyArticle(text);
                modifiedArticle = article;
            }
        }
        if (modifiedArticle == null) {
            System.out.println("components.Article " + title + " not found");
            return;
        }
        Event event = new Event(modifiedArticle, EventType.MODIFY);
        dispatcher.notifyListeners(event);
    }

    public void deleteArticle(String title) {
        List<Article> articleList;
        articleList = this.articles.stream()
                .filter(a -> a.getTitle().equals(title)).collect(Collectors.toList());
        if (articleList.isEmpty()) {
            return;
        }
        this.articles.removeIf(article -> article.getTitle().equals(title));
        Event event = new Event(articleList.get(0), EventType.DELETE);
        dispatcher.notifyListeners(event);
    }
}
