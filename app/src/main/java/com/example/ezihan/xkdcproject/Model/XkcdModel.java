package com.example.ezihan.xkdcproject.Model;

/**
 * Created by EziHAn on 27.09.2017.
 */

public class XkcdModel {


    /**
     * month : 9
     * num : 1895
     * link :
     * year : 2017
     * news :
     * safe_title : Worrying Scientist Interviews
     * transcript :
     * alt : They always try to explain that they're called 'solar physicists', but the reporters interrupt with "NEVER MIND THAT, TELL US WHAT'S WRONG WITH THE SUN!"
     * img : https://imgs.xkcd.com/comics/worrying_scientist_interviews.png
     * title : Worrying Scientist Interviews
     * day : 27
     * {"month": "9", "num": 1895, "link": "", "year": "2017", "news": "", "safe_title": "Worrying Scientist Interviews", "transcript": "",
     * "alt": "They always try to explain that they're called 'solar physicists', but the reporters interrupt with \"NEVER MIND THAT, TELL US WHAT'S WRONG WITH THE SUN!\"",
     * "img": "https://imgs.xkcd.com/comics/worrying_scientist_interviews.png",
     * "title": "Worrying Scientist Interviews", "day": "27"}
     */

    private String month;
    private int num; //1895 is written without quotation mark, so it is assigned as integer
    private String link;
    private String year;// "2017" is written in quotation mark, so it is a String
    private String news;
    private String safe_title;
    private String transcript;
    private String alt;
    private String img;
    private String title;
    private String day;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getSafe_title() {
        return safe_title;
    }

    public void setSafe_title(String safe_title) {
        this.safe_title = safe_title;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}