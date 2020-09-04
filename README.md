# books

## 테스트
- localhost:8080/suggestion
- application.properties 변경 후 실행

## 구성
- Entity : DB 테이블과 1:1로 호환
- Repository : Spring Data JPA에서 사용하는 인터페이스. SQL문에 대응하는 메소드들로 이루어짐.
ex) findAll() = 'SELECT * FROM TABLE', findById(int id) = 'SELECT * FROM TABLE WHERE id = TABLE.id'
- Service : Repository를 활용하여 DB CRUD 기능 구현
- Controller : API 로직 구현, 페이지 주소 설정
- Configuration : 보안 및 기타 설정
- application.properties : 동작 환경, DB, 포트 설정
- pom.xml : maven 라이브러리 관리
- resources/static : css 및 JavaScript 파일
- resources/templates : html 파일