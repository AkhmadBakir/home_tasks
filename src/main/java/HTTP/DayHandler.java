package HTTP;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

public class DayHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Random random = new Random();
        int index = random.nextInt(6);

//        int index = 5;

        List<String> days = List.of("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

        String response = days.get(index);

        exchange.sendResponseHeaders(200, response.length());

        try (OutputStream outputStream = exchange.getResponseBody()) {
            System.out.println(response);
            outputStream.write(response.getBytes());
        }
    }
}
