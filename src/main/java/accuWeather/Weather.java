package accuWeather;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Weather {

    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();
        URI url = URI.create("http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212?apikey=WI4lhSJG55Wainf5sNrDmH3OfqRl4fcr");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonArray dailyForecasts = jsonObject.getAsJsonArray("DailyForecasts").getAsJsonArray();
                for (int i = 0; i < 5; i++) {
                    JsonObject jsonObjectInDate = dailyForecasts.get(i).getAsJsonObject();
                    String date = jsonObjectInDate.get("Date").getAsString();
                    OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
                    LocalDate localDate = offsetDateTime.toLocalDate();
                    String formattedDate = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                    int minTemperature = jsonObjectInDate.getAsJsonObject("Temperature").getAsJsonObject("Minimum").get("Value").getAsInt();
                    int maxTemperature = jsonObjectInDate.getAsJsonObject("Temperature").getAsJsonObject("Maximum").get("Value").getAsInt();
                    String dayIconPhrase = jsonObjectInDate.getAsJsonObject("Day").get("IconPhrase").getAsString();
                    String nightIconPhrase = jsonObjectInDate.getAsJsonObject("Night").get("IconPhrase").getAsString();
                    boolean hasPrecipitationInDay = jsonObjectInDate.getAsJsonObject("Day").get("HasPrecipitation").getAsBoolean();
                    String dayTimePrecipitation = "днем без осадков";
                    if (hasPrecipitationInDay) {
                        dayTimePrecipitation = "днем возможны осадки";
                    }
                    boolean hasPrecipitationInNight = jsonObjectInDate.getAsJsonObject("Night").get("HasPrecipitation").getAsBoolean();
                    String nightTimePrecipitation = "ночью без осадков";
                    if (hasPrecipitationInNight) {
                        nightTimePrecipitation = "ночью возможны осадки";
                    }
                    System.out.println("Прогноз погоды на " + formattedDate + ": " + "\n"
                            + "температура от " + ((minTemperature - 32) * 5/9) + "° C" + " до " + ((maxTemperature - 32) * 5/9) + "° C" + ", \n"
                            + "днем: " + dayIconPhrase + ", ночью: " + nightIconPhrase + ", \n"
                            + dayTimePrecipitation + ", " + nightTimePrecipitation);
                    System.out.println("-".repeat(40));
                }
            } else {
                System.out.println("Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) { // обрабатываем ошибки отправки запроса
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }
}
