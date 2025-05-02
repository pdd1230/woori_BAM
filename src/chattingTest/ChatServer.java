package chattingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        int port = 10001; // 서버 포트 번호
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 포트 " + port + "에서 대기 중입니다...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트 연결됨: " + clientSocket.getInetAddress());

            // 입력 스트림 (클라이언트로부터 받기)
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // 출력 스트림 (클라이언트에게 보내기)
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // 메세지 수신 쓰레드
            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("클라이언트: " + msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receiveThread.start();

            // 메세지 전송 (서버가 입력한 내용을 클라이언트에게 전송)
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
