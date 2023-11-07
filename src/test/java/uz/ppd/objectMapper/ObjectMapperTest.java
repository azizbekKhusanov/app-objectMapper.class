package uz.ppd.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ObjectMapperTest {


    @Test
    void sampleTest() throws JsonProcessingException {

        String json = """
                {
                    "id": 1,
                    "name": "ketmon",
                    "username": "ketmonjon",
                    "email": "ketmon@gmail.com",
                    "password": "12345"
                }""";

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println(user);

        String s = mapper.writeValueAsString(user);
        System.out.println(s);
    }

    @Test
    void objectFromStringReader() throws IOException {

        String json = """
                {
                    "id": 1,
                    "name": "ketmon",
                    "username": "ketmonjon",
                    "email": "ketmon@gmail.com",
                    "password": "12345"
                }""";
        StringReader stringReader = new StringReader(json);
        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(stringReader, User.class);
        System.out.println(user);

    }


    @Test
    void objectFromFile() throws IOException {

        File file = new File("data/user.txt");

        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(file, User.class);
        System.out.println(user);
    }


    @Test
    void objectFromUrl() throws IOException {

        int userId = new Random().nextInt(1, 10);
        System.out.println(userId);

        URL url = URI.create("https://jsonplaceholder.typicode.com/posts/" + userId).toURL();

        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(url, Post.class);
        System.out.println(post);

    }

    @Test
    void objectFromInputStream() throws IOException {

        InputStream is = new FileInputStream("data/user.txt");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(is, User.class);
        System.out.println(user);
    }


    @Test
    void objectFromByteArray() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Post[] postList = mapper.readValue(Posts.POSTS, Post[].class);

        for (Post post : postList) {
            System.out.println(post);
        }

    }


    @Test
    void listFromByteArray() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        List<Post> postList = mapper.readValue(Posts.POSTS, new TypeReference<List<Post>>(){
        });

        for (Post post : postList) {
            System.out.println(post);
        }

    }


    @Test
    void mapFromStringJson() throws Exception {

        String json = """
                {
                    "id": 1,
                    "name": "ketmon",
                    "username": "ketmonjon",
                    "email": "ketmon@gmail.com",
                    "password": "12345"
                }""";

        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> mapString = mapper.readValue(json, new TypeReference<Map<String, String>>(){
        });

        System.out.println(mapString);
    }


    @Test
    void jsonFromObject() throws Exception {

        User user = new User();
        user.setId(1);
        user.setName("Bolta");
        user.setUsername("Boltajon");
        user.setEmail("bolta@gmail.com");
        user.setPassword("wkflwekf");

        ObjectMapper mapper = new ObjectMapper();
        OutputStream os = new FileOutputStream("data/users.txt");
        mapper.writeValue(os, user);

    }


    @Test
    void yamlFormat() throws Exception{


        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        User user = new User();
        user.setId(1);
        user.setName("Bolta");
        user.setUsername("Boltajon");
        user.setEmail("bolta@gmail.com");
        user.setPassword("wkflwekf");
        user.setBirthDate(new Date());

        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyy"));
        String s = mapper.writeValueAsString(user);
        System.out.println(s);

    }



}
