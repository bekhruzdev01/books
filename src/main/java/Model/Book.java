package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Book {
    private int id;
    private String title;
    private String author ;
    private String description;
    private String imageUrl;

}
