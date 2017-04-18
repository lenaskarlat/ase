package at.ac.tuwien.infosys;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by lenaskarlat on 4/12/17.
 */
@RestController
@RequestMapping("/")
public class SensorDataController {

    private List<String> timeStamps=new ArrayList<>();

    @RequestMapping(value = "data", method = RequestMethod.POST)
    public void acceptData(@RequestParam("data") String data, @RequestParam("time") String time) throws Exception {
        timeStamps.add(time);
    }

    @RequestMapping(value = "data", method = RequestMethod.GET)
    public String acceptData() {
        String html = "<html><head></head><body style='background: white; color: black; font-family: Verdana'>" +
                "<h1>Sensor Status Page</h1>";
        html+="<p><b>Sensor A2</b></p>";
        html+="<hr/>";
        html+="<p>  Sent Data Time Stamps </p>";
        html+="<ol>";
        for (String timeStamp:timeStamps){
            long time= Long.parseLong(timeStamp);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
            html+="<li>"+sdf.format(time)+"</li>";
        }
        html+="</ol>";
        html += "</body></html>";
        return html;
    }


}