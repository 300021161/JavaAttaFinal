import java.util.List;

public class Main {

    public static void main(String[] args) {
        Generator generator = new Generator();

        Restaurant[] restaurants = generator.generateRestaurants(1);

        WriteInJson writeInJson = new WriteInJson();

        writeInJson.serializeRestaurants(restaurants, "player.json");

        ReadFromJson readFromJson = new ReadFromJson();

        List<Restaurant> listRes = readFromJson.deserialize("player.json");
    }
}