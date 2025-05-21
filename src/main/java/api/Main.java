package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class Main {
    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();

        // сформируйте правильный URL-адрес
        URI url = URI.create("http://ipapi.co/213.186.33.69/json/?lang=fr");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // проверяем, успешно ли обработан запрос
            // передаем парсеру тело ответа в виде строки, содержащей данные в формате JSON
            if (response.statusCode() == 200) {
                // преобразуем результат разбора текста в JSON-объект
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                // проверяем, точно ли мы получили JSON-объект
                // получаем название страны
                String country = jsonObject.get("country").getAsString();
                // получаем название города
                String city = jsonObject.get("city").getAsString();
                // получаем значение широты
                double latitude = jsonObject.get("latitude").getAsDouble();
                // получите значения полей из задания
                double longitude = jsonObject.get("longitude").getAsDouble();
                // получите значения полей из задания
                // соседние страны
                String countryNeighbours = jsonObject.get("countryNeighbours").getAsString();
                // телефонный код страны
                int countryPhone = jsonObject.get("countryPhone").getAsInt();

                System.out.println("Страна: " + country);
                System.out.println("Город: " + city);
                System.out.println("Широта: " + latitude);
                System.out.println("Долгота: " + longitude);
                System.out.println("Соседние страны: " + countryNeighbours);
                System.out.println("Телефонный код страны: " + countryPhone);
            } else {
                System.out.println("Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) { // обрабатываем ошибки отправки запроса
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }
}