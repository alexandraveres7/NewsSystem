package main;

import actors.Editor;
import actors.Reader;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Reader reader1 = new Reader("Ale");
        Reader reader2 = new Reader("Ligia");
        Editor editor1 = new Editor("Darius Floca", "DailyMail");
        Editor editor2 = new Editor("Bence", "CNN");

        reader1.registerListener();
        reader2.registerListener();

        reader1.addFilterCriteria("Date", "2019-08-08");
        reader1.addFilterCriteria("Source", "DailyMail");
        reader1.addFilterCriteria("Source", "CNN");
        reader2.addFilterCriteria("Domain", "shopping");
        editor1.registerListener();

        editor1.publishArticle("Biden won", "The election has come to an end.", "politics", "USA");
        editor2.publishArticle("Black Friday Sale", "Hurry up!", "shopping", "TM News");


        editor1.modifyArticle("Biden won", "!!!!!!!");
        editor1.deleteArticle("Biden won");
    }
}
