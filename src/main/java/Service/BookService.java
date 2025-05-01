package Service;

import Model.Book;
import lombok.SneakyThrows;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class BookService {
    @SneakyThrows
    public Book searchBook(String query) {
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + query;

        URL url = new URL(apiUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
    }
}





