package in.shopperstreet.PlaylistDownloader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by prakash.s on 13/06/17.
 */
public class ApiCaller  {


    StringBuilder response;

    String folderPath;



    public void makeRequest(String playlistId,String folderPath){

        this.folderPath = folderPath;

        String param1 = "contentDetails";
        String param2 = "50";
        String param3 = playlistId;
        String param4 = "AIzaSyC9zk4bdawjR-INT8_o7LoKIT_5hhxmXxs";

        String baseUrl = "https://www.googleapis.com/youtube/v3/playlistItems";

        String query = String.format("part=%s&maxResults=%s&playlistId=%s&key=%s", param1,param2,param3,param4);

        try {

            URL url = new URL(baseUrl+"?"+query);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  //Get is the Default Request method

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            response = new StringBuilder();

            while ((line = in.readLine()) != null){
                response.append(line);
            }

            JSONParser();

        }catch (Exception e){

            e.printStackTrace();
        }

    }


    public void JSONParser(){


//        Create JSONObject from string
        JSONObject jsonObject = new JSONObject(response.toString());

//        Get items array from the json object
        JSONArray jsonArray = jsonObject.getJSONArray("items");

//        ArrayList to hold all the video ids(Passed to selenium)
        ArrayList<String> videoIdsList = new ArrayList<String>();

        JSONObject object;
        JSONObject content;
        String videoId;

        /*Loop through jsonArray & for every object get contentDetails object.
        * Get the 'videoId' String from the contentDetails object.
        * */
        for (int i=0;i<jsonArray.length();i++) {

            object = jsonArray.getJSONObject(i);
            content = object.getJSONObject("contentDetails");
            videoId = content.getString("videoId");
            videoIdsList.add(videoId);
        }

        Utube utube = new Utube();

        try {

            utube.setUp(videoIdsList,folderPath);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
