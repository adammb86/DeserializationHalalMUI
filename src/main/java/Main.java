import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();

        // ubah string JSON menjadi Objek
        Gson gsonBuilder = new GsonBuilder().create();

        // deserialisasi data JSON dari Webservice
        try

        {
            String jsonWeb = getJson("https://script.googleusercontent.com/macros/echo?user_content_key=8HZ7R87V2Bu1_BXHUEjIJFKtTSZ3xaNPFmjEOVvg_BXumiUSP3BmfbaR8wdBTTMSNcquzx_j3w9rYogI9YcqZ50EyqS4iADeOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuB6lHT6qnqYcmFWggwoSVQQy1I5Bo9qCEkCzCDqo9woNJdb-TW_oskRAvMiLJmfu8uHcCUBLJV6YgIO_Ew8o_0imBB_KEA6gBFJTkuTqaPWp36w6lqZ9_xTy2bHR3CakFrc_ScPTHfLrg&lib=MUBVDLa2DH4xDzprwWT1Nz4v1P8nYvko1");
            ProdukResult produkResult = gson.fromJson(jsonWeb, ProdukResult.class);

            System.out.println("Hasil deserialisasi dari Webservice: ");
            System.out.println(produkResult.getStatus());
            for(Produk products:produkResult.getData()){
                System.out.println(products.getTitle());
                System.out.println(products.getNomor_sertifikat());
                System.out.println(products.getProdusen());
                System.out.println(products.getBerlaku_hingga());
                System.out.println();
            }
        } catch(
                Exception e)

        {
            System.out.println("Terjadi masalah: " + e.getMessage());
        }

    }

    private static String getJson(String url) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", "Mozilla/5.0");

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }
}
