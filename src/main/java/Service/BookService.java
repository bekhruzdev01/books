package Service;

import Model.Book;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    @SneakyThrows
    public List<Book> searchBook(String query) {
        List<Book> Books = new ArrayList<>();
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



            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");

                JSONArray authors = volumeInfo.optJSONArray("authors");
                String author = "No Author";
                if (authors.length() > 0 && authors != null) {
                    author = authors.join(", ");
                    author = author.replace("\"", "");

                }

                String imageUrl = null;
                if (volumeInfo.has("imageLinks")) {
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    imageUrl = imageLinks.optString("thumbnail", null);
                }

                Books.add(
                        Book.builder()
                                .title(item.getString("title"))
                                .author(author)
                                .description(item.getString("description"))
                                .imageUrl(imageUrl)
                                .build()
                );
            }
        }
        return Books;
    }
}





