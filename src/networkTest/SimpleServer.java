package networkTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer{
    public static void main(String[] args) {
        int port = 9999; // 개발자 지정

        try (ServerSocket serverSocket = new ServerSocket(port)) { // [1단계] ServerSocket 생성 
            System.out.println("서버가 준비되었습니다. 클라이언트를 기다리는 중...");

            // [3단계] 클라이언트 연결 받기 (수락) ---------------------

            // 3-1) 클라이언트 연결을 수락 
            Socket clientSocket = serverSocket.accept(); // (대기 상태)
            System.out.println("클라이언트가 연결되었습니다!");

            
            
            // [5단계] 클라이언트로부터 메시지 받기 ----------------

            // 5-1) 입력 스트림(바이트 스트림) 얻기
            InputStream is = clientSocket.getInputStream();

            // 5-2) 바이트 스트림을 문자 스트림으로 변환
            InputStreamReader isr = new InputStreamReader(is);

            // 5-3) 문자 스트림을 BufferedReader로 감싸기 (라인 단위 읽기)
            BufferedReader in = new BufferedReader(isr);

            // 5-4) 클라이언트가 보낸 메시지 한 줄 읽기
            String clientMessage = in.readLine();

            // 5-5) 받은 메시지를 콘솔에 출력
            System.out.println("클라이언트 메시지: " + clientMessage);

            
            
            // [6단계] 클라이언트에게 응답 보내기 ------------------

            // 6-1) 출력 스트림 생성.    true --> 자동전송 옵션 설정
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);  // 단계를 1줄로 축약

            // 6-2) 응답 메시지 전송
            out.println("서버도 안녕하세요!");

            
            
            // [8단계] 연결 종료 ----------------------------------

            // 8-1) 클라이언트 소켓 닫기
            clientSocket.close();
            System.out.println("서버 종료");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
