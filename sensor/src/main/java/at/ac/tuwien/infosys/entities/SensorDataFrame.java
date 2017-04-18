package at.ac.tuwien.infosys.entities;

/**
 * Created by lenaskarlat on 4/13/17.
 */
public class SensorDataFrame {


    private String data;
    private String time;

    public SensorDataFrame(String data, String time) {
        this.data = data;
        this.time = time;
    }

    public String getData() { return data; }

    public String getTime() { return time; }

    public void setData(String data) { this.data = data; }

    public void setTime(String time) { this.time = time; }
}
