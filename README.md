# musinsa-assignment

## 개발 환경

```
Language : Java 21
Framework : Spring boot 3.2.4
Build : Gradle 8.7
Database : H2
Doc : Swagger
```

## 프로젝트 구조(multi module)

```
musinsa-assignment [multimodule-root]
├─ api             [API 모듈]
├─ backoffice      [관리자 모듈]
└─ core            [공통 모듈]
```
## 요구사항
```
1. 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 합니다.
2. 고객은 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액이 얼마인지 확인할 수 있어야 합니다.
3. 고객은 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인할 수 있어야 합니다.
4. 운영자는 새로운 브랜드를 등록하고, 모든 브랜드의 상품을 추가, 변경, 삭제할 수 있어야 합니다.
```

## 구현범위

### api module

<b> 요구사항 1,2,3) 클라이언트 요청 결과를 반환하는 API 기능 구현</b> <br/>

```
1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
 조회하는 API
3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
4. Unit test 및 Integration test 작성
```

### backoffice module

<b> 요구사항 4) 브랜드 및 상품을 추가 / 업데이트 / 삭제 API 기능 구현</b> <br/>

```
1. 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
2. Unit test 및 Integration test 작성
```

### core module

```
api, backoffice 모듈에서 공통으로 사용되는 기능을 제공
```
## 테이블 구조

### [브랜드 테이블] BRAND

| Column Name | Data Type    | Constraints                | Description |
|-------------|--------------|----------------------------|-------------|
| id          | INT          | AUTO_INCREMENT,PRIMARY KEY | BRAND ID    |
| name        | VARCHAR(255) | NOT NULL UNIQUE            | 브랜드명        |

### [카테고리 테이블] CATEGORY

| Column Name | Data Type    | Constraints                | Description |
|-------------|--------------|----------------------------|-------------|
| id          | INT          | AUTO_INCREMENT,PRIMARY KEY | 카테고리 ID     |
| name        | VARCHAR(255) | NOT NULL UNIQUE            | 카테고리명       |

### [상품테이블] PRODUCT

| Column Name | Data Type | Constraints                | Description |
|-------------|-----------|----------------------------|-------------|
| id          | INT       | AUTO_INCREMENT,PRIMARY KEY | 상품 ID       |
| brand_id    | BIGINT    | FOREIGN KEY                | 브랜드 ID      |
| category_id | BIGINT    | FOREIGN KEY                | 카테고리 ID     |
| price       | BIGINT    |                            | 금액          |

## ENTITY 설계
#### BRAND(1) : PRODUCT(N) / OneToMany
#### CATEGORY(1) : PRODUCT(N) / OneToMany
#### PRODUCT(N) : BRAND(1) / ManyToOne
#### PRODUCT(N) : CATEGORY(1) / ManyToOne

## 프로젝트 실행 방법

<h2>Git Clone</h2>

```
$ git clone https://github.com/yonggunjoo/musinsa-assignment.git
cd musinsa-assignment
```
## Module Test
```
$ ./gradlew clean :api:test  
$ ./gradlew clean :backoffice:test  
```

## Build And Run

### api module
```
$ ./gradlew :api:clean :api:build :api:bootRun -x test 
```

### backoffice module
```
$ ./gradlew :backoffice:clean :backoffice:build :backoffice:bootRun -x test
```

## Swagger URL
### api
```
localhost:8081/api/swagger-ui/index.html
```
### backoffice
```
localhost:8082/admin/swagger-ui/index.html
```
