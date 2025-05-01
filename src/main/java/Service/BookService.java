package Service;

import Model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    @SneakyThrows
    public List<Book> searchBook(String query) {
        List<Book> books = new ArrayList<>();
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + query;

        URL url = new URL(apiUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        if (urlConnection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(stringBuilder.toString());
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                JsonNode volumeInfo = item.path("volumeInfo");

                String title = volumeInfo.path("title").asText("No Title");
                String description = volumeInfo.path("description").asText("No Description");

                // Authors array to string
                JsonNode authorsNode = volumeInfo.path("authors");
                String author = "No Author";
                if (authorsNode.isArray()) {
                    List<String> authorList = new ArrayList<>();
                    for (JsonNode a : authorsNode) {
                        authorList.add(a.asText());
                    }
                    author = String.join(", ", authorList);
                }

                // Image link
                String imageUrl = null;
                JsonNode imageLinks = volumeInfo.path("imageLinks");
                if (!imageLinks.isMissingNode()) {
                    imageUrl = imageLinks.path("thumbnail").asText(null);
                }

                books.add(
                        Book.builder()
                                .title(title)
                                .author(author)
                                .description(description)
                                .imageUrl(imageUrl)
                                .build()
                );
            }
        }

        return books;
    }
}
