package app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by a508708 on 28/01/2017.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private Integer id;
    private String name;
    private String genre;
    private String year;
    private Boolean available;
    private User renter;
    private Date rental_date;
    private Date expiration_date;
}
