# oEmbed 데이터 수집 프로젝트
## 의존성
- jdk 17, springboot 3.1.1
- spring webflux
- lombok

## 기능
- YouTube, Instagram, Twitter, Vimeo 등의 컨텐츠를 미리 볼 수 있는 서비스 제공
- 사용자가 입력한 URL에 해당하는  [oEmbed](http://oembed.com/)  데이터를 수집하여 표시

## 결과 예시
![main.png](asset%2Fmain.png)

## 코드 설명
- 서버 구동 시에 src/main/resources/static/providers.json에서 JSON 정보를 읽어 Bean으로 등록 
- providers.json에 명시된 URL 패턴(schemes)과 일치하는 provider에게 embed 정보를 요청하고 받아옴
- 요청은 Webclient릍 통해 실행되며, connection/read/write timeout : 2000ms로 설정
- 에러 처리는 CustomException을 이용하여 수행하고, 이후 CustomAdvisor를 통해 클라이언트에게 응답
- 요청 후 URL이 없을 경우(404 응답) 또한 CustomException으로 처리
