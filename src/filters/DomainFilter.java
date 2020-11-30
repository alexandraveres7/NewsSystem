package filters;

import components.Article;

public class DomainFilter extends Filter {
    public DomainFilter(String criteria) {
        super(criteria);
    }

    @Override
    public boolean applyFilter(Article article) {
        return article.getDomain().equalsIgnoreCase(this.criteria);
    }
}

