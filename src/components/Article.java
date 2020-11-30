package components;

import java.time.LocalDate;

public class Article {
    private String title, text, domain, subdomain, source, author;
    private LocalDate dateCreated, dateLastModified;
    private Integer views = 0;

    public Article(String title, String text, String domain, String subdomain, String source, String author) {
        this.title = title;
        this.text = text;
        this.domain = domain;
        this.subdomain = subdomain;
        this.source = source;
        this.author = author;
        this.dateCreated = LocalDate.now();
    }

    public void modifyArticle(String text) {
        this.text = text;
        this.dateLastModified = LocalDate.now();
    }

    public void read() {
        views++;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDomain() {
        return domain;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public String getSource() {
        return source;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalDate getDateLastModified() {
        return dateLastModified;
    }

    public Integer getViews() {
        return views;
    }
}
