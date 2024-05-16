package Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                handleClientRequest(clientSocket);

                // Đóng kết nối
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = reader.readLine();
            if (request != null && request.equals("time")) {
                // Phản hồi thời gian hiện tại nếu nhận được yêu cầu "time"
                String currentTime = getCurrentTime();
                writer.println(currentTime);
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để lấy thời gian hiện tại dưới dạng chuỗi
    private static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        return dateFormat.format(currentTime);
    }
}
