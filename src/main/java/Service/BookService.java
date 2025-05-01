package Service;

import Model.Book;
import lombok.SneakyThrows;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    @SneakyThrows
    public Book searchBook(String query) {
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + query;

        URL url = new URL(apiUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        if (urlConnection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();

            while (reader.ready()) {
                stringBuffer.append(reader.readLine());
            }

            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            JSONArray items = jsonObject.getJSONArray("items");

            List<Book> Books = new ArrayList<>();

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");

                JSONArray authors = volumeInfo.optJSONArray("authors");
                    if (authorsArray != null && authorsArray.length() > 0) {
                    authors = authorsArray.join(", ");
                    // Removing quotes from the joined string
                    authors = authors.replaceAll("\"", "");
                }

                String imageUrl = null;
                if (volumeInfo.has("imageLinks")) {
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    imageUrl = imageLinks.optString("thumbnail", null);
                }
                Book.builder()
                        .title(item.getString("title"))
                        .author(item.getString("author"))
                        .description(item.getString("description"))
                        .build();
            }
        }
    }
}





