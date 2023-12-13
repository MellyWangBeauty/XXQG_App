package com.example.xxqg;

public class ListItem {
    private String title;
    private String duration;
    private String source;
    private String url;

    public ListItem(String title,String duration,String source,String url) {
        this.title = title;
        this.duration = duration;
        this.source = source;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }
    public String getDuration() {
        return duration;
    }
    public String getSource() {
        return source;
    }
    public String getUrl(){return url;}
}