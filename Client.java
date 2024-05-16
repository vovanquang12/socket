package Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to server.");

            while (true) {
                // Đọc dữ liệu từ bàn phím của client
                System.out.print("Client: ");
                String clientMessage = userInput.readLine();

                // Gửi dữ liệu đến server
                clientOutput.println(clientMessage);

                // Đọc dữ liệu từ server
                String serverMessage = serverInput.readLine();
                System.out.println("Server: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
