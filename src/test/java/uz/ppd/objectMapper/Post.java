package uz.ppd.objectMapper;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Post {

    private Integer id;
    private Integer userId;
    private String body;
    private String title;

}
