package networkTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1"; // localhost
        int port = 9999;

        try (Socket socket = new Socket(serverIP, port)) {  // [2단계} socket생성 --> 연결 요청 (대기)

            // [4단계] 서버에 데이터 보내기 ---------------------

            // 4-1) 소켓의 출력 스트림 (바이트 스트림) 얻기
            OutputStream os = socket.getOutputStream();

            // 4-2) 바이트 스트림을 문자 기반 PrintWriter로 감싸기
            PrintWriter out = new PrintWriter(os, true); // true --> 자동전송 옵션 설정

            // 4-3) 서버로 메시지 전송
            out.println("안녕하세요");
            

            // [7단계] 서버 응답 받기 --------------------------

            // 7-1) 소켓의 입력 스트림 (바이트 스트림) 얻기
            InputStream is = socket.getInputStream();

            // 7-2) 바이트 스트림을 문자 스트림으로 변환
            InputStreamReader isr = new InputStreamReader(is);

            // 7-3) 문자 스트림을 버퍼로 감싸서 한 줄씩 읽기
            BufferedReader in = new BufferedReader(isr);

            // 7-4) 서버 응답 한 줄 읽기
            String serverResponse = in.readLine();

            // 7-5) 응답 결과 출력
            System.out.println("서버 응답: " + serverResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
