package chattingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1"; // 서버 IP (자기자신)
        int port = 9999; // 서버 포트 번호

        try (Socket socket = new Socket(serverIP, port)) {
            System.out.println("서버에 연결되었습니다.");

            // 입력 스트림 (서버로부터 받기)
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 출력 스트림 (서버에게 보내기)
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 메세지 수신 쓰레드
            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("서버: " + msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receiveThread.start();

            // 메세지 전송 (클라이언트가 입력한 내용을 서버에게 전송)
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String sendMsg = scanner.nextLine();
                out.println(sendMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
