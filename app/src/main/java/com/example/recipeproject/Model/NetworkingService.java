package com.example.recipeproject.Model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.recipeproject.activities.MainActivity;

import org.json.JSONException;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {

    String recipeURL = "https://api.spoonacular.com/recipes/complexSearch?";
    String apiKey = "apiKey=c896c1207ccf465da33cd952861700f9&addRecipeInformation=true";
    String mealType = "&type=";
    String dietType = "&diet=";
    String intolerancesType = "&intolerances=";

    String url = recipeURL + apiKey;
    String completeURL;
    //ExecutorService interface created
    public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);

    //handler is needed to return the result from connection (which returns void) to the Main thread
    static Handler networkHandler = new Handler(Looper.getMainLooper());

    public static interface NetworkingListener {
        void APINetworkListener(String jsonString) throws JSONException;
    }

    public static NetworkingListener listener;

    //NUM4 BUILD THE URL
    public void fetchRecipeData(String mealText,String dietText,String intolerancesText){
        System.out.println("2.in the fetchRecipe method in NetworkingService");
//        mealText = MainActivity.getMealString();
//        dietText = MainActivity.getDietString();
//        intolerancesText = MainActivity.getIntoleranceString();
        completeURL = recipeURL + apiKey + mealType  + mealText + dietType + dietText + intolerancesType + intolerancesText;
        //String completeURL = "https://travelbriefing.org/countries.json";
        System.out.println("3. complete URL: in NetworkingService");
        connect(completeURL);

    }
    //NUM5 CREATE CONNECTION AND GET THE DATA IN CHARACTERS and get unparse json
    private void connect(String completeURL){
        System.out.println("4. in the connect method creating connection in NetworkingService");
        //run this in a background thread
        networkingExecutor.execute(new Runnable() {

            String urlJSONString = ""; //this is the JSON string is the outcome after inputStreamReader
            @Override
            public void run(){
                System.out.println("5. ExecutorService run in NetworkingService");
                //initialize it to null
                HttpURLConnection httpURLConnection = null;
                try {
                    //create an object of URL class and pass the url which will be the completeURL
                    URL urlObj = new URL(completeURL);
                    //start the connection
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    //prepare the request
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                    //check the status
                    int status = httpURLConnection.getResponseCode();
                    System.out.println("6. this is the status in NetworkingService");

                    if ((status >= 200) && (status <=299)){
                        System.out.println("7. In the status if statement in NetworkingService");
                        //if status between 200 and 299, then its a greenlight
                        //read the stream
                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(in);
                        int read = 0;
                        while ((read = inputStreamReader.read()) != -1){
                            char c = (char) read;
                            urlJSONString += c;
                        }
                        System.out.println("7. inputstreamReader stage in NetworkingService");
                        //final human readable unparsed JSONfinal
                        String finalJson = urlJSONString;
                        System.out.println("8. finalJson which is the unparsed JSONl in NetworkingService");
                        //need a handler to send the result to the main thread
                        //handler has access to the main thread
                        networkHandler.post(new Runnable() {
                            @Override
                            public void run(){
                                //activity iwill be the listener - returns unparsed json

                                try {
                                    System.out.println("9. returning unparsed JSON to the main thread in NetworkingService");
                                    listener.APINetworkListener(finalJson);
                                    System.out.println("14.back in NetworkingService");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }
            }
        });
    }

}
