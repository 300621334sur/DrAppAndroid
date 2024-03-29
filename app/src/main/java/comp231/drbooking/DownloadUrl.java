package comp231.drbooking;
/*
 * By: SHAFIQ-UR-REHMAN
 * Purpose: Send "GET" request to and receive response from any given URL in background
 * This class is used by "GetNearbyPlacesData" which itself is AsyncTask
 */
import android.net.UrlQuerySanitizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUrl //class will retrive data using httpUrl connection & file-handling methods
{

    ////JSON string returned from API
    public String readUrl(String myUrl) throws IOException
    {
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection=null;

        try {
            URL url = new URL(myUrl);//alt+ent => surround w try/catch
            urlConnection = (HttpURLConnection) url.openConnection();//alt+enter => add catch clause
            urlConnection.connect();

            //read data from URL
            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();

            //read ea line one by one
            String line = "";
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            //aft all lines r read, close buffer
            data = sb.toString();
            br.close();

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            inputStream.close();//add 'throws IOException' at fn sign
            urlConnection.disconnect();
        }
        return data;//JSON format string
    }
}
