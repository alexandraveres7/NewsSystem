package filters;

import components.Article;

import java.time.LocalDate;


public class DateFilter extends Filter {
    public DateFilter(String criteria) {
        super(criteria);
    }

    @Override
    public boolean applyFilter(Article article){
        LocalDate date = LocalDate.parse(this.criteria);
        return article.getDateCreated().isEqual(date);
    }
}
