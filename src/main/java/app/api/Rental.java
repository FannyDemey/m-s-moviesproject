package app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by a508708 on 19/02/2017.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    @Id private Integer id;
    private Movie movie;
    private User user;
    private Date rentalDate;
}
