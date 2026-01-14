import com.sun.net.httpserver.*;
import com.google.gson.Gson;
import java.io.*;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.*;

public class UniversitySimpleAPI {
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // ==================== STUDENTS ROUTES ====================
        server.createContext("/students", exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                handleGetAllStudents(exchange);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                handleCreateStudent(exchange);
            } else {
                sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
            }
        });

        server.createContext("/students/", exchange -> {
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");

            if (parts.length >= 3 && !parts[2].isEmpty()) {
                String name = parts[2];

                if ("GET".equals(exchange.getRequestMethod())) {
                    handleGetStudentByName(exchange, name);
                } else if ("PUT".equals(exchange.getRequestMethod())) {
                    handleUpdateStudent(exchange, name);
                } else if ("DELETE".equals(exchange.getRequestMethod())) {
                    handleDeleteStudent(exchange, name);
                } else {
                    sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
                }
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  REST API Server Started Successfully  ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Server running on: http://localhost:8080");
        System.out.println("Run curl on https://reqbin.com/curl");
        System.out.println("See curl commands on the end of the file:)");
        System.out.println("\nAvailable endpoints:");
        System.out.println("  GET    /students           - Get all students");
        System.out.println("  POST   /students           - Create new student");
        System.out.println("  GET    /students/{name}    - Get student by name");
        System.out.println("  PUT    /students/{name}    - Update student course");
        System.out.println("  DELETE /students/{name}    - Delete student");
    }

    // ==================== STUDENTS HANDLERS ====================

    private static void handleGetAllStudents(HttpExchange exchange) throws IOException {
        try {
            ArrayList<DBConnection> students = DBConnection.getAll();
            String json = gson.toJson(students);
            sendResponse(exchange, 200, json);
        } catch (SQLException e) {
            sendResponse(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private static void handleGetStudentByName(HttpExchange exchange, String name) throws IOException {
        try {
            ArrayList<DBConnection> students = DBConnection.getAll();
            DBConnection foundStudent = null;

            for (DBConnection student : students) {
                if (student.toString().contains(name)) {
                    foundStudent = student;
                    break;
                }
            }

            if (foundStudent == null) {
                sendResponse(exchange, 404, "{\"error\":\"Student not found\"}");
            } else {
                String json = gson.toJson(foundStudent);
                sendResponse(exchange, 200, json);
            }
        } catch (SQLException e) {
            sendResponse(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private static void handleCreateStudent(HttpExchange exchange) throws IOException {
        try {
            String body = new String(exchange.getRequestBody().readAllBytes());
            Map<String, Object> studentData = gson.fromJson(body, Map.class);

            String name = (String) studentData.get("name");
            int age = ((Double) studentData.get("age")).intValue();
            int course = ((Double) studentData.get("course")).intValue();

            DBConnection student = new DBConnection(name, age, course);
            student.save();

            String json = gson.toJson(studentData);
            sendResponse(exchange, 201, json);
        } catch (SQLException e) {
            sendResponse(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            sendResponse(exchange, 400, "{\"error\":\"Invalid data\"}");
        }
    }

    private static void handleUpdateStudent(HttpExchange exchange, String name) throws IOException {
        try {
            String body = new String(exchange.getRequestBody().readAllBytes());
            Map<String, Object> studentData = gson.fromJson(body, Map.class);

            int newCourse = ((Double) studentData.get("course")).intValue();

            DBConnection.updateCourse(name, newCourse);

            String json = gson.toJson(Map.of("message", "Student updated successfully", "name", name, "newCourse", newCourse));
            sendResponse(exchange, 200, json);
        } catch (SQLException e) {
            sendResponse(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            sendResponse(exchange, 400, "{\"error\":\"Invalid data\"}");
        }
    }

    private static void handleDeleteStudent(HttpExchange exchange, String name) throws IOException {
        try {
            DBConnection.delete(name);
            sendResponse(exchange, 200, "{\"message\":\"Student deleted successfully\"}");
        } catch (SQLException e) {
            sendResponse(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // ==================== UTILITY METHOD ====================

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


//# Получить всех студентов
//curl http://localhost:8080/students
//
//        # Создать студента
//curl -X POST http://localhost:8080/students \
//        -H "Content-Type: application/json" \
//        -d '{"name":"Azamat","age":18,"course":1}'
//
//        # Получить студента по имени
//curl http://localhost:8080/students/Azamat
//
//        # Обновить курс студента
//curl -X PUT http://localhost:8080/students/Azamat \
//        -H "Content-Type: application/json" \
//        -d '{"course":2}'
//
//        # Удалить студента
//curl -X DELETE http://localhost:8080/students/Azamat