package utility.Log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Log {

    @Id
    private long time;
    @Column
    private String userName;
    @Column
    private String text;


    public Log() {

    }

    public Log(String userName, String text) {
        time=System.currentTimeMillis();
        this.userName = userName;
        this.text = text;
    }


    //getter and setters
    //********************

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
