package minisearch.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0
        );

        server.createContext("/hello", App::hello);
        server.createContext("/search", App::search);

        server.start();

        System.out.println("Server started on http://localhost:8080");
    }

    private static void hello(HttpExchange exchange) throws IOException {
        String response = """
                {"message": "Hello!"}
                """;

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private static void search(HttpExchange exchange) throws IOException {

    }
}