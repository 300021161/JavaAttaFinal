import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class WriteInJson {

    public void serializeRestaurants(Restaurant[] restaurants, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
