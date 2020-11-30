public class SourceFilter extends Filter {
    public SourceFilter(String criteria) {
        super(criteria);
    }

    @Override
    public boolean applyFilter(Article article) {
        return article.getSource().equalsIgnoreCase(this.criteria);
    }
}
