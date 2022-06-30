

<img width="280" alt="image" src="https://user-images.githubusercontent.com/79190824/176671747-b86179e6-e77e-4e2b-8c98-a9b182b868a3.png"><img width="280" alt="image" src="https://user-images.githubusercontent.com/79190824/176671775-e39b4219-af5d-4aea-bae3-9b1b4f33a405.png"><img width="280" alt="image" src="https://user-images.githubusercontent.com/79190824/176671789-913bf9a2-4bf2-468b-9138-18591459c168.png">
<img width="280" alt="image" src="https://user-images.githubusercontent.com/79190824/176671812-2c25c7d8-a6f5-4ac7-814f-c901b3280622.png"><img width="280" alt="image" src="https://user-images.githubusercontent.com/79190824/176671826-6176f0ed-cf74-4d61-b98d-e20b52b48a19.png">


## 📃 Summary

---

검색 기능, 지도 좌표, 금액 설정, 날짜 설정 등 앱에서 많이 사용되는 기능들을 구현해보고, 유저의 이용방식에 따른 변수들과 화면의 이동을 생각하고, 이해하기 위해서 airbnb 앱을 개발해보았습니다.

## 💻 Part

---

- `REST API`를 이용한 서버 통신 구현
- compose를 이용한 첫 화면 개발
- 체크인, 체크아웃 날짜 설정을 위한 커스텀 캘린더
- 인원 설정 기능

## ✏️ Learn

---

- composeView를 이용한 부분 compose 개발

- LazyHorizontalGrid를 이용한 가로 RecyclerView 구현 학습
    - 공식문서에서 한글로 찾았을때 업데이트가 되어있지 않아서 영어로 전환해서 LazyHorizontalGrid를 찾고, github에서 LazyHorizontalGrid를 이용한 레파지토리를 검색하여 버전을 찾아서 LazyHorizontalGrid를 이용할 수 있었습니다.
    
- Custom Calendar를 제작하며 DiffUtil에 대한 이해와 성능 개선에 대해 생각해보았습니다.
    - 처음에 List로 1년간의 날짜를 구현하고 전체 순회를 하면서 클릭한 체크인, 체크아웃 날짜를 찾고 그 사이의 날짜들의 background를 변경하니 깜빡임과 느린 속도가 문제가 되었습니다. 그래서 List를 이용하던 것을 Map으로 전환하고 각 day마다 id를 부여해서 간단한 캐싱을 구현하여 전체 순회가 아닌 id값을 이용해서 클릭한 날짜를 찾고 그 사이의 날짜들의 ui를 함께 변경하였더니 속도가 많이 빨라졌습니다.
    - DiffUtil에서 hashcode를 이용해서 값의 변경을 체크하던 것을 hear로 변경해서 체크인, 체크아웃 날짜와 그 사이의 day들만 변경할 수 있도록 리팩토링했더니 클릭시 달력 전체가 깜빡이던 현상이 사라졌습니다.

## 🕶Team

---

안드로이드 : 2명

백엔드 : 2명
