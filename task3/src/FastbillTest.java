import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;


/**
 * Created by Roman Karaba (a1301624) on 17-Jun-17.
 */
public class FastbillTest {

  public static void main(String[] args) {

    HttpClient httpClient = HttpClientBuilder.create().build();
    String jsonString = "";

    try {
      String url = "https://my.fastbill.com/api/1.0/api.php";
      String email = "swe2task3@gmail.com";
      String apiKey = "f290364e533055d3766a970cbbe1908dj0LdM76NY3dkJyy9c8KTL63OsAfdioct";

      String authoNoEncode = email + ":" + apiKey;

      String allEncoded =  "Basic " + java.util.Base64.getEncoder().encodeToString((authoNoEncode).getBytes());


      String content = "{\"SERVICE\":\"customer.get\",\"FILTER\":{}}";
      StringEntity entity = new StringEntity(content);

      HttpPost request = new HttpPost(url);
      request.addHeader("Authorization", allEncoded);
      request.addHeader("Content-Type", "application/json");

      request.setEntity(entity);

      HttpResponse response = httpClient.execute(request);

      BufferedReader br = null;
      StringBuilder sb = new StringBuilder();


      String line;
      try {

        br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        while ((line = br.readLine()) != null) {
          sb.append(line);
        }
        jsonString = sb.toString();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (br != null) {
          try {
            br.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }
    System.out.println(jsonString.toString());
    JSONObject jsonObject = null;

    try {
      jsonObject = new JSONObject(jsonString);
      System.out.println(jsonObject.get("RESPONSE"));
    } catch (Exception e) {e.printStackTrace();}


    System.out.println(jsonObject.toString());

  }

}
