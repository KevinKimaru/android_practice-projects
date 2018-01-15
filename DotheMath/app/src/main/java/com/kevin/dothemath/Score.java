package com.kevin.dothemath;

/**
 * Created by Kevin Kimaru Chege on 5/4/2017.
 */

public class Score implements Comparable<Score> {

    private String scoreDate;
    public int scoreNum;

    public Score(String date, int num){
        scoreDate=date;
        scoreNum=num;
    }

    //allow us to sort scores to determine the top ten
    public int compareTo(Score sc){
        //return 0 if equal
        //1 if passed greater than this
        //-1 if this greater than passed
        return sc.scoreNum>scoreNum? 1 : sc.scoreNum<scoreNum? -1 : 0;
    }
    //a method to return the date display text:
    public String getScoreText()
    {
        return scoreDate+" - "+scoreNum;
    }
}
