package bean;

import java.sql.Timestamp;

public class chatinfo implements Comparable<chatinfo>{
    public String methond;
    public String content;
    public Timestamp time;

    @Override
    public int compareTo(chatinfo chat){
        return this.time.compareTo(chat.time);
    }
}
