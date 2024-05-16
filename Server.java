package Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running. Waiting for client to connect...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Đọc dữ liệu từ client
                String clientMessage = clientInput.readLine();
                System.out.println("Client: " + clientMessage);

                // Đọc dữ liệu từ bàn phím của server
                System.out.print("Server: ");
                String serverMessage = serverInput.readLine();

                // Gửi dữ liệu đến client
                serverOutput.println(serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
