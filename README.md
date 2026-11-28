# 17-sprint-mission
Spring 백엔드 트랙 17기 스프린트 미션 제출 리포지토리입니다.

---

## 서비스 구현체 비교

> `JCF*Service` 구현체와 `File*Service` 구현체를 비교하여 공통점과 차이점을 발견해보세요.
#### 공통점
`JCF*Service`와 `File*Service`는 같은 `*Service` 인터페이스의 구현체로, 동일한 시그니처의 CRUD 메서드를 제공한다는 공통점이 있다.

#### 차이점
`JCF*Service`는 구현체가 데이터를 필드에 직접 소유하고 있다.
그렇기 때문에 여러 인스턴스가 생성됐을 때 인스턴스마다 각각의 데이터를 가지고 있어, 데이터 조회 시 인스턴스마다 다른 결과가 나타나게 된다.
이를 해결하기위해 데이터를 `static` 필드로 소유하거나 구현체에 싱글톤 패턴을 적용해 여러 인스턴스가 생성되는걸 막아야 한다.

`File*Service`는 직렬화를 통해 데이터를 관리한다.
`JCF*Service`와 달리 데이터를 직접 소유하지 않는다. 그렇기 때문에 위와 같은 문제가 발생하지는 않는다. 데이터 소유 방식 자체가 다르다보니, 저장 로직에 큰 차이가 있다.

> "비즈니스 로직"과 관련된 코드를 식별해보세요.

`create`, `update`에서 객체에 데이터를 삽입.
(추후 기능에 따라 추가될 입력된 데이터의 유효성 체크)
Message를 생성할때 author, channel이 유효한지 확인하는 절차 등

> "저장 로직"과 관련된 코드를 식별해보세요.

`findById`, `findAllById`, `findAll`, `delete`
`create`의 (JCF) 'data.put' , (File) 'serialize'


> `Basic*Service`, `Basic*Repository` 가 이전에 작성했던 코드(`JCF*Service` 또는 `File*Service`)와 비교해 어떤 차이가 있는지 정리해보세요.

`Basic*Service`는 비즈니스 로직을 담당하고,
`Basic*Repository`는 저장 로직을 담당한다.

`JCF/File*Service`와 달리
`Repository`가 의존성 주입된 `Basic*Service`는 저장 로직이 외부에 직접 노출되지 않는다.

저장 로직의 책임을 분리하였기 때문에, 저장 방식이 달라지더라도
`Basic*Service`은 그대로 재사용할 수 있다. 

---
