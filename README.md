# 코드로 배우는 스프링부트 웹 프로젝트 Part4 Chapter7
## 프로젝트 명 : mreview  
![Springboot](pictures/Springboot.png)
***
> ### Part 4 N:N(다대다) 관계와 파일 업로드 처리  
> >  #### 7. M:n(다대다) 관계의 설계와 구현 
> > > 7.1 M:N(다대다) 관계의 특징(345p)  
> > > 7.2 예제 프로젝트 생성(349p)  
> > > 7.3 M:N(다대다) Repository와 테스트(359p)   


* ### 설정 
1. #### Gradle projects 에서 'Build and run using', 'Run tests using' 변경하기  
   * Ctrl + Alt + S 눌러서 setting 들어가기  
   * ![](pictures/IDE_Setting1.jpg)  

2. #### Annotation Processors 체크  
   * ![](pictures/IDE_Setting2.jpg)  

***  
### 변경이력  
|branch|commit|날짜|비고|
|:----:|:-----|:---:|:---|
|main|프로젝트 생성 @main|2021-09-10|프로젝트 첫 생성후 'build.gradle'에 타임리프 날짜 라이브러리 주입<br>application.properties의 파일형식 yml로 변경 및 마리아디비의 bootex디비 서버에 연결<br>백업을 제대로 해두자... 프로젝트 다시 생성하고 원격저장소도 2번이나 갈아 엎었음 |
|main|프로젝트 생성_업데이트 @main|2021-09-10|놓친 파일들 에드하고 커밋후 푸쉬|
|syc|README.md 수정 @syc|2021-09-10|변경이력 테이블 수정|