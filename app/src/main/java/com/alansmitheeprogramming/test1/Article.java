package com.alansmitheeprogramming.test1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Article {
    private String team1;
    private String team2;
    private String time;
    private String tournament;
    private String place;
    private MinorArticle minorArticle;
    private String prediction;

    public Article(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray("article");

        this.team1 = jsonObject.getString("team1");
        this.team2 = jsonObject.getString("team2");
        this.time = jsonObject.getString("time");
        this.tournament = jsonObject.getString("tournament");
        this.place = jsonObject.getString("place");
        this.prediction = jsonObject.getString("prediction");
        this.minorArticle = new MinorArticle(jsonArray);

    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTime() {
        return time;
    }

    public String getTournament() {
        return tournament;
    }

    public String getPlace() {
        return place;
    }

    public MinorArticle getArticles() {
        return minorArticle;
    }

    public String getPrediction() {
        return prediction;
    }

    public class MinorArticle {
        private String[] header;
        private String[] text;

        public MinorArticle(JSONArray jsonArray) throws JSONException {
            header = new String[jsonArray.length()];
            text = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                this.header[i] = jsonArray.getJSONObject(i).getString("header");
                this.text[i] = jsonArray.getJSONObject(i).getString("text");
            }
        }

        public String[] getHeader() {
            return header;
        }

        public String[] getText() {
            return text;
        }
    }
}

