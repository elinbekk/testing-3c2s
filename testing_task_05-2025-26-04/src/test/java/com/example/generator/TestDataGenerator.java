package com.example.generator;

import com.example.model.PostData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.datafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {
    private static final Faker faker = new Faker();
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void main(String[] args) throws IOException {
        int postCount = 5;
        generatePosts(postCount);
    }

    private static void generatePosts(int count) throws IOException {
        List<PostData> posts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            posts.add(new PostData(
                    faker.lorem().sentence(3),
                    faker.lorem().paragraph(3)
            ));
        }
        mapper.writeValue(new File("posts.json"), posts);
    }
}
