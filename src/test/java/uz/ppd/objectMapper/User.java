package uz.ppd.objectMapper;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Date birthDate;
}
