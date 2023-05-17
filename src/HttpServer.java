import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8080)) {
        System.out.println("Сервер запущен!");

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("Подключено!");

            String htmlFile = "C:\\Users\\student\\IdeaProjects\\untitled15\\src\\index.html";

            String htmlContent = readHtmlFile(htmlFile);

            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter output = new PrintWriter(socket.getOutputStream())) {

                while (!input.ready()) ;

                System.out.println();
                while (input.ready()) {
                    System.out.println(input.readLine());
                }

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; Content-Type: stylesheet/css; charset=utf-8");
                output.println();
                output.println(htmlContent);
                output.flush();

                System.out.println("Отключилючено!");
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
    private static String readHtmlFile(String htmlFile) throws IOException {
        File file = new File(htmlFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuffer htmlContent = new StringBuffer();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            htmlContent.append(line);
        }

        bufferedReader.close();
        fileReader.close();

        return htmlContent.toString();
    }
}