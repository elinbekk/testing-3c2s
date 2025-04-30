package com.example.generator;

import com.example.model.PostData;
import com.example.model.UserData;
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
        generateUsers(5, "users.json");
        generatePosts(5, "posts.json");
    }

    private static void generateUsers(int count, String filename) throws IOException {
        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData(
                    String.format("%s%s", faker.name().username().replace(".", ""), faker.number().digits(3)), // Уникальный username
                    generateSecurePassword() // Сложный пароль
            ));
        }
        mapper.writeValue(new File(filename), users);
    }

    private static void generatePosts(int count, String filename) throws IOException {
        List<PostData> posts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            posts.add(new PostData(
                    faker.lorem().sentence(3),
                    faker.lorem().paragraph(3)
            ));
        }
        mapper.writeValue(new File(filename), posts);
    }

    private static String generateSecurePassword() {
        return faker.internet().password(8, 16, true, true, true);
    }
}
