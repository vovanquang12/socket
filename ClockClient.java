package Socket;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClockClient extends JFrame {
    private JLabel clockLabel;

    public ClockClient() {
        setTitle("Clock");
        setSize(200, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(clockLabel, BorderLayout.CENTER);

        setVisible(true);

        // Kết nối đến server và cập nhật thời gian
        connectToServer();
    }

    private void connectToServer() {
        try {
            while (true) {
                Socket socket = new Socket("localhost", 12345);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                // Gửi yêu cầu "time" đến server
                writer.println("time");

                // Đọc thời gian từ server và cập nhật lên đồng hồ
                String currentTime = reader.readLine();
                clockLabel.setText(currentTime);

                // Đóng kết nối
                socket.close();

                // Chờ 1 giây trước khi gửi yêu cầu tiếp theo
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClockClient();
    }
}
