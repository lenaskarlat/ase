package at.ac.tuwien.infosys;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.*;

/**
 * Created by lenaskarlat on 4/12/17.
 */
@SpringBootApplication
public class SensorDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorDataApplication.class, args);
        sendData();
    }


    public static void sendData() {

        List<String> paths = Utils.getFilePathsInFolder("A2");

        for (String path : paths) {
            RawDataFrame rdf = new RawDataFrame();
            try {
                rdf.readSingleFrame(path);
                String time = Objects.toString(rdf.time, null);
                String data = Arrays.toString(rdf.data);
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("time", time));
                nvps.add(new BasicNameValuePair("data", data));
                Utils.sendRequest("http://127.0.0.1:8080/receive", nvps);
                Utils.sendRequest("http://127.0.0.1:8081/data", nvps);
                Thread.sleep(10 * 1000); //seconds
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}
