public class Main {
    public static void main(String[] args){
        Reader reader1 = new Reader("Ale");
        Reader reader2 = new Reader("Ligia");
        Editor editor = new Editor("Darius Floca", "DailyMail");
        Editor edit = new Editor("Bence","RO");

        reader1.registerListener();
        reader2.registerListener();

        reader1.addFilterCriteria("Source", "DailyMail");
        reader2.addFilterCriteria("Domain", "shopping");
        editor.registerListener();

        editor.publishArticle("Biden won", "Yuhu", "politics", "USA");
        edit.publishArticle("Black Friday Sale", "Hurry up!", "shopping", "TM News");

        editor.modifyArticle("Biden won", "!!!!!!!");
        editor.deleteArticle("Biden won");
    }
}
