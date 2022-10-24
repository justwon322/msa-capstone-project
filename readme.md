## 프로젝트 소개
음식 배달 서비스 cover<br>
해당 서비스 예제는 MSA/DDD/Event Storming/EDA 를 포괄하는 분석/설계/구현/운영 전 단계를 커버할 수 있도록 구성한 예제로 제시되는 평가포인트에 맞춰 개발 진행되었습니다.
 - 평가포인트 : https://workflowy.com/s/c10811dfdb67/UhOZB2crKOhNPUYp#/fc30a50ea43b
## Table of contents
- [서비스 시나리오](#서비스-시나리오)
- [분석/설계](#분석/설계)
- [체크포인트](#체크포인트)
---
### 서비스 시나리오
배달의 민족 커버하기 - https://1sung.tistory.com/106

- 기능적 요구사항
1. 고객이 메뉴를 선택하여 주문한다
2. 고객이 결제한다
3. 주문이 되면 주문 내역이 입점상점주인에게 전달된다
4. 상점주인이 확인하여 요리해서 배달 출발한다
5. 고객이 주문을 취소할 수 있다
6. 주문이 취소되면 배달이 취소된다
7. 고객이 주문상태를 중간중간 조회한다
8. 주문상태가 바뀔 때 마다 카톡으로 알림을 보낸다

- 비기능적 요구사항
1. 트랜잭션<br>
결제가 되지 않은 주문건은 아예 거래가 성립되지 않아야 한다 Sync 호출
2. 장애격리<br>
상점관리 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다 Async (event-driven), Eventual Consistency
결제시스템이 과중되면 사용자를 잠시동안 받지 않고 결제를 잠시후에 하도록 유도한다 Circuit breaker, fallback
3. 성능<br>
고객이 자주 상점관리에서 확인할 수 있는 배달상태를 주문시스템(프론트엔드)에서 확인할 수 있어야 한다 CQRS
배달상태가 바뀔때마다 카톡 등으로 알림을 줄 수 있어야 한다 Event driven

---
### 분석/설계

---
### 체크포인트
---
#### Saga(Pub/Sub)
SAGA 패턴이란 마이크로서비스들끼리 이벤트를 주고 받아 특정 마이크로서비스에서의 작업이 실패하면 이전까지의 작업이 완료된 마이크서비스들에게 보상 (complemetary) 이벤트를 소싱함으로써 분산 환경에서 원자성(atomicity)을 보장하는 패턴
- 주문이 발생할 경우(Order Service - ordered(event)) publish -> 주문 목록이 업데이트 된다. (Store - UpdateOrderList(Polish)) Subscribe
- 주문이 취소될 경우(Order Service - ordercanceled(event)) publish -> 주문결제가 취소된다. (Payment - CancelPayment(Polish)) Subscribe
<br>
- 주문 생성
<img width="960" alt="주문생성" src="https://user-images.githubusercontent.com/115772322/197435954-fa78862f-d1d2-4182-b40e-ba3b2b681d26.png">
<br>
- 주문 취소
<img width="665" alt="주문취소" src="https://user-images.githubusercontent.com/115772322/197435970-62094945-e7f8-41b9-a25a-ae1c16983e1f.png">
<br>
- Kafka에 접속하여 이벤트 확인
<img width="1186" alt="스크린샷 2022-10-24 오전 11 14 05" src="https://user-images.githubusercontent.com/115772322/197435566-e66e34ec-7267-4c46-a110-c1bc52610b90.png">

-----------------------------------------------------------------------------
### 유용원 수정
-----------------------------------------------------------------------------

#### Circuit Breaker
Circuit Breaker 패턴이란 하나의 컴포넌트가 느려지거나 장애가 나면 그 장애가난 컴포넌트를 호출하는 종속된 컴포넌트까지 장애가 전파될때, 장애가 전파되지 않도록 회로 차단기 개념으로 장애 발생시 전파가 되지 않도록 하는 패턴을 말한다. 즉, Service A 와 B간의 통신중, B에 오류가 발생 할 경우 Circuit Breaker 가 작동하여 B를 호출하는 A 쓰레드들을 모두 차단하여 A가 더이상 B 서비스를 기다리지 않도록 하는 개념이다.

본 프로젝트에서는 Hystrix 라이브러리를 사용하였고 아래와 같이
요청처리 쓰레드에서 처리시간이 610 밀리가 넘어서기 시작하여 어느정도 유지되면 CB 회로가 닫히도록 (요청을 빠르게 실패처리, 차단) 설정하였다.

<img width="450" alt="CircuitBreaker설정Yml" src="https://user-images.githubusercontent.com/5373350/197447628-5eb3dbeb-672a-42b2-be9b-e312aa7b7ca4.png">

아래는 siege 를 활용하여 시스템 부하를 통해 Circuit Breaker가 동작하는 모습이다.

![image](https://user-images.githubusercontent.com/5373350/197448196-4fdfdd30-2cd1-4a3c-a67a-0fa6537fe35d.png)


#### GateWay
여러 개의 클라이언트가 여러 개의 서버 서비스를 각각 호출하게 된다면 매우 복잡한 호출 관계가 생성 되는데, 이를 해결하기 위해 단일 진입점을 만드는 것이 게이트웨이다. 아래 그림과 같이 다양한 클라이언트가 다양한 서비스에 액세스하기 위해서는 단일 진입점을 만들어 놓으면 여러모로 효율적이다. 다른 유형의 클라이언트에게 서로 다른 API조합을 제공할 수도 있고 각 서비스 접근 시 필요한 인증/인가 기능을 한 번에 처리할 수도 있다. 또 정상적으로 동작하던 서비스에 문제가 발생하여 서비스 요청에 대한 응답 지연이 발생하면 정상적인 다른 서비스로 요청 경로를 변경하는 기능이 작동되게 할 수도 있다.

<img width="584" alt="MSA2 16" src="https://user-images.githubusercontent.com/5373350/197448502-b216e83b-3461-48bd-b84d-ce76f505b704.png">

아래는 게이트 웨이의 진입점 포트(8088)와 연결되어있는 서비스들에 대한 설정 부분이고

<img width="373" alt="image" src="https://user-images.githubusercontent.com/5373350/197448604-29049486-5ec4-46f7-b7fb-4021d1423ec9.png">

아래 두개 화면은 8088 포트로 주문과 주문확인을 진행 하였을때, 내부적으로 어떻게 호출하는지에 대한 부분이다.


1. 8088 포트로 주문시 8082 포트로 올려진 order서비스 호출됨

<img width="776" alt="gateway서비스를 통한 주문" src="https://user-images.githubusercontent.com/5373350/197448995-420c66dc-9e4d-4408-92a3-4b08c968b355.png">

2. 주문확인

<img width="549" alt="gateway서비스를 통한 주문내역확인" src="https://user-images.githubusercontent.com/5373350/197449086-ff6700fa-0aaf-40ea-9e9d-9bd2587d22b6.png">







