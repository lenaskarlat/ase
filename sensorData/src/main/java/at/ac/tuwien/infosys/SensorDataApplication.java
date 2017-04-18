package at.ac.tuwien.infosys;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

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

                String url = "http://127.0.0.1:8080/receive";
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(url);
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("time", time));
                nvps.add(new BasicNameValuePair("data", data));
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                CloseableHttpResponse response = httpclient.execute(httpPost);
                System.out.println(response.getStatusLine());
                response.close();

                Thread.sleep(10 * 1000); //seconds
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
