package com.alansmitheeprogramming.test1;

import org.json.JSONException;
import org.json.JSONObject;

public class Event {

    private String title;
    private String coefficient;
    private String time;
    private String place;
    private String preview;
    private String article;

    public Event(JSONObject jsonObject) {
        try {
            this.title = jsonObject.getString("title");
            this.coefficient = jsonObject.getString("coefficient");
            this.time = jsonObject.getString("time");
            this.place = jsonObject.getString("place");
            this.preview = jsonObject.getString("preview");
            this.article = jsonObject.getString("article");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getPreview() {
        return preview;
    }

    public String getArticle() {
        return article;
    }
}
