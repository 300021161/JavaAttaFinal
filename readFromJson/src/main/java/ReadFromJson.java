import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadFromJson {

    public List<Restaurant> deserialize(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Чтение JSON из файла в виде строки
            String json = Files.readString(Path.of(filePath));

            // Десериализация JSON в список ресторанов
            List<Restaurant> restaurants = objectMapper.readValue(json, new TypeReference<>() {});

            return restaurants;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // В случае ошибки возвращаем пустой список
        return new ArrayList<>();
    }
}
