package projectnewsaggregator.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User  {
    @Id
    private String id;
    @NotNull(message = "Email cannot be null")
    @Indexed(unique = true)
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    private String firstName;
    private String lastName;
    private List<String> preferences;

}
