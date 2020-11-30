package filters;

import components.Article;

public abstract class Filter {
    protected String criteria;

    public Filter(String criteria) {
        this.criteria = criteria;
    }
    public abstract boolean applyFilter(Article article);
}