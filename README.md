# Diary
Diary 앱은 제목과 내용, 그리고 이미지를 등록하여 내 일상을 기록할 수 있는 앱입니다.

<br>
<br>
<br>

### 순서도
----------
![image](https://user-images.githubusercontent.com/72846127/229265252-267adc0f-73b6-41e1-8755-531906b0e239.png)

<br>
<br>
<br>

### UI
----------
#### 게시글 등록

![KakaoTalk_20230328_032520971_05](https://user-images.githubusercontent.com/72846127/229265376-793b7946-31e8-4a82-92f1-5bcf8bd34e5e.jpg)

- 앱을 실행했을 시 초기 화면입니다.
- 플로팅 버튼을 통해 게시글을 등록할 수 있습니다.

![KakaoTalk_20230328_032520971](https://user-images.githubusercontent.com/72846127/229265396-7e1a2f8a-f28f-4d65-83e7-3bc8cfe1e7fd.jpg) ![KakaoTalk_20230328_032520971_04](https://user-images.githubusercontent.com/72846127/229265404-4e2a84c9-502d-42e1-8b3d-b87daadf9689.jpg)
- 글 제목과 글 내용이 모두 들어가있는지 확인합니다.
- 사진을 추가할 시 카메라로 사진을 찍거나 내부 폴더의 이미지를 가져올 수 있습니다.

![KakaoTalk_20230328_032520971_03](https://user-images.githubusercontent.com/72846127/229265427-6cdaafaf-c784-4600-a0fb-1ddf30015647.jpg) ![KakaoTalk_20230328_032520971_02](https://user-images.githubusercontent.com/72846127/229265449-a776bd39-14e4-4677-89b0-a641a4a0f467.jpg)
- 게시글 등록이 완료되었다는 메세지와 함께 화면에 내가 등록한 글이 보입니다.
- 해당 뷰를 터치하면 디테일 액티비티로 이동하여 글을 보다 자세히 볼 수 있고, 플로팅 버튼이 수정 기호로 바뀝니다.

#### 게시글 수정
![KakaoTalk_20230328_032520971_01](https://user-images.githubusercontent.com/72846127/229265487-eb1b9883-ca5c-4013-bf3a-62c2adf1e41f.jpg) ![KakaoTalk_20230328_032520971_08](https://user-images.githubusercontent.com/72846127/229265491-825a01f7-6692-4707-97af-8270a4999230.jpg)
- 글 수정 액티비티로 이동하면 내가 작성했던 글이 EditText에 미리 들어가있습니다.
- 게시글 제목의 오타를 수정하고 내용 수정 버튼을 누르면 현재 액티비티가 종료되고 내가 수정한 내용이 디테일 액티비티에 반영되어있습니다.

#### 게시글 삭제
![KakaoTalk_20230328_032520971_07](https://user-images.githubusercontent.com/72846127/229265532-98bb9961-da9b-4200-9816-adc80b198cbf.jpg) ![KakaoTalk_20230328_032520971_06](https://user-images.githubusercontent.com/72846127/229265536-a512133b-9000-4a56-b1f6-58ddaabbbbc6.jpg)
- 삭제하고 싶은 게시글을 길게 누를 시 Dialog가 나타납니다.
- 삭제를 하면 해당 게시글이 사라지는 걸 볼 수 있고 Toast 메시지로 '삭제되었습니다.' 라는 문구가 출력됩니다.

