# Feature

### 로그인 및 회원가입

<img alt="sign" src="https://github.com/user-attachments/assets/07d9039c-29f4-43e5-b97b-1e6940b11f3c"  width="300" />

- Requirements
    1. 소셜로그인으로 로그인 및 회원가입 할 수 있어야 한다.
    2. 신규 유저는 이용약관 동의, 닉네임 입력 등의 과정을 거쳐야 한다.
- Try
    - 다양한 소셜 로그인 대응하기 위해 전략패턴 사용

### 그룹 생성 및 딥링크 생성

<img alt="organizationCreation" src="https://github.com/user-attachments/assets/85405aba-d320-493e-80f4-ca720789ce0f"  width="300" />

- Requirements
    1. 사용자는 누구나 그룹을 만들 수 있어야 한다.   
    2. 선택 사항과 필수 사항은 구분 되어야한다.
    3. 첫 화면을 제외한 모든 화면에서 이전 단계로 돌아가도 상태가 저장되어야한다.
    4. 딥링크를 통해 그룹에 참여하려는 다른 사용자를 초대할 수 있다.
- Try
    - 각 단계 화면을 `enum class` 로 관리 -> 다른 추가 단계가 들어와도 대응 가능
    - button 의 활성 여부를 `EnumMap` 으로 관리
    - 딥링크 생성시 `AES` 대칭키 암호화 알고리즘 사용


### 대기 멤버 수락

<img alt="pending" src="https://github.com/user-attachments/assets/713b04a6-e62d-443a-92e6-31f23e0b796f"  width="300" />

- Requirements
    1. 그룹 내 역할이 "리더"는 가입 대기 멤버를 수락할 수 있어야 한다.


### 공지 생성

<img alt="announcementCreation" src="https://github.com/user-attachments/assets/419db9db-5d8f-41a8-a5ea-d35920c00481"  width="300" />

- Requirements
    1. 그룹 내 역할이 "리더"는 자유롭게 공지를 만들 수 있어야 한다.   
    2. `약속 날짜 및 시간` , `약속 장소` , `약속을 위해 할 일` , `약속 시간 전 리마인드 알림`은 사용자가 선택할 수 있다.
    3. 그룹의 프로모션 여부에 따른 공지 썸네일을 선택할 수 있다.
- Try
    - `TextField` 내의 커서 위치를 계산하고 줄바꿈이나 커서 재조정 등의 행위가 일어났을 때 커서 위치를 계산하고 화면이 이동하도록하여 사용자 경험을 개선하기 위해 노력
    - 공지 옵션에 대해 `ViewModel`의 책임을 분리하고 `sealed class` 로 데이터를 정의하여 일관성 있는 구조 구축
    - `LinkedHashMap` 을 활용하여 옵션 선택 여부 등의 상태를 관리함으로써 타입 안정성과 확장성을 확보


### 그룹 상세 및 열람자/미열람자 확인

<img alt="announcement" src="https://github.com/user-attachments/assets/a213c97d-17ec-4725-b3ef-65a0a6bcb754"  width="300" />

- Requirements
    1. 홈 화면뿐 아니라 그룹 상세 화면에서도 그룹에 해당하는 공지를 확인할 수 있다.
    2. 리더는 해당 공지의 열람자/미열람자를 확인할 수 있다.
- Try
    - `BottomSheetScaffold` 사용

### 새 공지 알림 및 리마인드 알림
<img alt="notification" src="https://github.com/user-attachments/assets/f13db485-ea51-4836-acd7-0de41334777a"  width="300" />

- Requirements
    1. 속한 그룹의 새로운 공지를 푸시 알림으로 받을 수 있어야 한다.
    2. 새로운 공지가 리마인드 알림을 옵션으로 선택했을 경우, 설정한 시간에 푸시 알림으로 받을 수 있어야 한다.
- Try
    - `FCM` 사용
    - `navDeepLink` 적용


### 그 외
- 그룹 내 권한 변경
- 그룹 정보 변경
- 그룹 내 사용자 내보내기
- 할 일 확인 및 체크
- 사용자 이름 변경
- 로그아웃 / 탈퇴
