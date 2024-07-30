package base;

import config.JsonReader;
import models.User;
import org.testng.annotations.DataProvider;

import java.util.List;

public class StaticProvider {
    @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() {
        List<User> users = JsonReader.readUsers("src/main/resources/user.json");
        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }
}
