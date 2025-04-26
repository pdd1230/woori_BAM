package com.woori.BAM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in); 
       
		int lastArticleId = 1;  //게시글 번호이니까...마지막게시글번호로 수정
		List<Article> articles = new ArrayList<>();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim(); 
			
			if (cmd.equals("exit")) {
				break;
			} 
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해 주세요");
				continue;
			}
			// List 메서드중 size() 이용 (Data 유무를 객체의 객수(크기)로 변환)
			if (cmd.equals("article write")) {
		
				System.out.printf("제목 : ");
				String title = sc.nextLine().trim(); 
				System.out.printf("내용 : ");
				String body = sc.nextLine().trim(); 
				
				Article article = new Article(lastArticleId , title, body); 
				articles.add(article); 
				
				System.out.println(lastArticleId +"번글이 생성되었습니다");
				lastArticleId++;
				
			} else if(cmd.equals("article list")){

				if (articles.size() == 0 ) {
					System.out.println("존재하는 게시글이 없습니다");
					continue;
					
				}
				
				System.out.printf("번호    |     제목\n");
				for (int i = articles.size() -1 ; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d       |     %s\n", article.id, article.title);
				}
				
			} else if (cmd.startsWith("article detail ")) { // article detail 로 시작하니?
				String[] cmdBits = cmd.split(" ");  // 번호 ==> cmdBits[2]
				
				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);  
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다");
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}

				
				Article foundArticle = null; 
				
				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) { // null 검증
					System.out.println(id + "번 게시물이 존재하지 않습니다");
					continue;
				}

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : ~~~");
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);

			} else if (cmd.startsWith("article delete ")) { 
				String[] cmdBits = cmd.split(" ");  
				
				int id = 0;  
				
				try {
					id = Integer.parseInt(cmdBits[2]);  
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다");
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				Article foundArticle = null;  // 재활용과 null 검증을 위해
				
				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article; // 객체지향프로그래밍 , 무조건 이용
						break;
					}
				}
				
//			    int foundIndex = -1 ; // null과 같은 의미로 -1 로 초기화 (존재하지 않는 index 의미)
//
//			    int indexId = 0;
//			    
//			    for(Article article : articles) {
//			    	if (article.id == id) {
//			    		foundIndex = indexId;
//			    		foundArticle = article; // 누락된 명령어, null 검증 위해 필요
//			    		break;
//			    	}
//			    	indexId++;
//			    }
			
//				for (int i = 0; i < articles.size(); i++) {
//					Article article =articles.get(i);
//					if (article.id == id) {
//						foundArticle = article;  // 누락된 명령어, null 검증 위해 필요
//						foundIndex = i;
//						break;
//					}
//				}
				
				// null 검증
				if (foundArticle == null) { 
					System.out.println(id + "번 게시물이 존재하지 않습니다");
					continue;
				}
				
				articles.remove(foundArticle);   // 2가지 방법이 있지요 
//				articles.remove(foundIndex);     // index 이용하는 방법도 주석처리
				
				System.out.println(id + "번 게시물이 삭제 되었습니다");
				
			}else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}
		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}
class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}