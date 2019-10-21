## Instagram Open API Copy App

### 인스타그램의 API를 이용하여 로그인한 사용자의 프로필과 포스트한 피드 정보를 확인해 볼 수 있는 토이 프로젝트 입니다.

LoginActivity: WebView를 이용해 아이디/패스워드를 입력하여 리디렉션 엑세스토큰을 가져오는 역할을 합니다.
MainActivity: 가져온 엑세스토큰을 이용하여 프로필과 포스트 리스트 정보를 가져와 화면에 나타냅니다.
PostActvity: 포스트를 선택해서 보여지는 상세 화면 입니다. 별 다른 기능과 동작은 없습니다.

- Android Studio 3.4.2
- Android Jetpack(LiveData, ViewModel), Glide, Retrofit2, circleimageview 등을 이용했습니다.

- 아이디: jaydev.android
- 비밀번호: dlwjddnr