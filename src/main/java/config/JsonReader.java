package config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
//    public static void main(String[] args) {
//        JsonParser jsonParser = new JsonParser();
//        try (FileReader reader = new FileReader("src/main/resources/user.json")) {
//            Object object = jsonParser.parse(reader);
//
//            JsonArray userList = (JsonArray) object;
//            System.out.println(userList);
//
//            userList.forEach(user -> parseUserObject((JsonObject) user));
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static List<User> readUsers(String filePath) {
        List<User> users = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        try (FileReader reader = new FileReader(filePath)) {
            JsonArray userList = (JsonArray) jsonParser.parse(reader);

            userList.forEach(user -> {
                JsonObject userObject = (JsonObject) user.getAsJsonObject().get("user");
                String username = userObject.get("username").getAsString();
                String password = userObject.get("password").getAsString();
                users.add(new User(username, password));
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

//    private static void parseUserObject(JsonObject user){
//        JsonObject userObject = (JsonObject) user.get("user");
//        String username = userObject.get("username").getAsString();
//        System.out.println(username);
//        String password = userObject.get("password").getAsString();
//        System.out.println(password);
//    }
}
