# 사용 프레임워크
* spring boot Data-JPA
* spring boot Security
* spring boot Web
* opencsv
* jjwt
* mysql-connector
* lombok

# 문제해결적략
* opencsv를 이용하여 input으로 들어온 csv파일을 파싱, 해당 Domain의 서비스지역에 파싱(정규표현식)이후 저장하도록 구현

* jjwt를 이용하여 사용자등록, 로그인 시 토큰 발급 및 토큰 갱신 API를 추가 

# 실행 방법
* resources/application.yml 설정파일에서 datasource 변경
* IntelliJ에서 ProblemApplication MainClass로 설정 후 실

## API
* CSV 벌크 업로드
```
curl -X POST \
  http://localhost:8080/program/bulk \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -H 'postman-token: 394e9376-2fc9-8f05-75c0-8eacd027ed90' \
  -F file=@program.csv
```
* 토큰 갱신
```
curl -X GET \
  http://localhost:8080/auth/refresh \
  -H 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNTU1MjM0MTc3LCJleHAiOjE1NTUyMzQyMzd9.z3t9B4tAAjJ0660KUxlci3HeechmdWeeCZKAB7OpXWg' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 1a9c7d7f-b37a-d953-4b3e-1195b2c1cdf1'
```

* 사용자 로그인
```
curl -X POST \
  http://localhost:8080/user/signin \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 10b61327-06fa-1028-eb3b-9bfd51b49ef4' \
  -d '{
	"username" : "test12",
	"password" : "test"
}'
```

* 사용자 등록
```
curl -X POST \
  http://localhost:8080/user/signup \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 6c926301-e475-6c3f-f395-d93f7bcb023b' \
  -d '{
	"username":"kakao",
	"password":"kakao"
}'
```