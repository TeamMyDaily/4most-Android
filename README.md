# ​🧡​4most 🧡

![img](/wiki/app_description.png)


- SOPT 27th AppJam **Team MyDaily**​:seedling:
- 프로젝트 기간 : `2020.12.26` ~ `2021.01.16`


<br>

# :green_book:​Contents

[:one:​ Specification](#one-specification)<br>
[:two:​ Flow Chart](#two-flow-chart)<br>
[:three:​ Package Structure](#three-package-structure)<br>
[:four:​ WIKI](#four-wiki)<br>
[:five:​ 핵심 기능 구현 방법 설명](#five-핵심-기능-구현-방법-설명)<br>
[:six:​ Contributor](#six-contributor)<br>




<br>

## ​:one:​ Specification

<table class="tg">
<tbody>
  <tr>
    <td><b>Architecture</b></td>
    <td>MVVM</td>
  </tr>
<tr>
    <td><b>Design Pattern</b></td>
<td>Repository Pattern</td>
</tr>
<tr>
    <td><b>Jetpack Components</b></td>
<td>DataBinding, LiveData, ViewModel, Lifecycle</td>
</tr>
<tr>
    <td><b>Dependency Injection</b></td>
<td>Koin</td>
</tr>
<tr>
    <td><b>Network</b></td>
<td>OkHttp, Retrofit2</td>
</tr>
<tr>
    <td><b>Strategy</b></td>
<td>Git Flow</td>
</tr>

<tr>
    <td><b>Third Party Library</b></td>
    <td><a href="https://github.com/skydoves/ExpandableLayout">ExpandableLayout</a>,
 <a href="https://github.com/woxingxiao/BubbleSeekBar">BubbleSeekBar</a>,
  <a href="https://github.com/airbnb/lottie-android">Lottie</a></td>

</tr>
<tr>
    <td><b>Other Tool</b></td>
<td>Notion, Slack</td>
</tr>
</tbody>
</table>

<br>

<br>

## :two:​ Flow Chart

![img](/wiki/image/210104_flowchart.png)



<br>

## :three:​ Package Structure

```
📦 org.mydaily
 ┣ 📂 data
 ┃ ┗ 📂 local
 ┃ ┗ 📂 model
 ┃ ┃ ┣ 📂 domain
 ┃ ┃ ┗ 📂 network
 ┃ ┗ 📂 remote
 ┃ ┃ ┣ 📂 api
 ┃ ┃ ┗ 📂 datasource
 ┃ ┗ 📂 repository
 ┣ 📂 di
 ┣ 📂 network
 ┣ 📂 ui
 ┃ ┗ 📂 adapter
 ┃ ┗ 📂 base
 ┃ ┗ 📂 view
 ┃ ┃ ┣ 📂 custom
 ┃ ┃ ┣ 📂 goal
 ┃ ┃ ┣ 📂 keyword
 ┃ ┃ ┣ 📂 mypage
 ┃ ┃ ┣ 📂 remind
 ┃ ┃ ┣ 📂 splash
 ┃ ┃ ┣ 📂 task
 ┃ ┃ ┗ 📂 user
 ┃ ┗ 📂 viewmodel
 ┣ 📂 util
 ┃ ┗ 📂 extension
 ┗ 📜 MyDailyApplication.kt
```



<br>

## :four:​ WIKI

```
👉 회의록 및 Project Rule(Git, Coding Convention)
```
- [HOME](https://github.com/TeamMyDaily/4most-Android/wiki)
  - [1. Git 사용법](https://github.com/TeamMyDaily/4most-Android/wiki/1.-Git-%EC%82%AC%EC%9A%A9%EB%B2%95)
  - [2. Git Commit Message Convention](https://github.com/TeamMyDaily/4most-Android/wiki/2.-Git-Commit-Message-Convention)
  - [3. Android Coding Convention](https://github.com/TeamMyDaily/4most-Android/wiki/3.-Android-Coding-Convention)
  - [4. 회의록](https://github.com/TeamMyDaily/4most-Android/wiki/4.-%ED%9A%8C%EC%9D%98%EB%A1%9D)




<br>

## :five:​ 핵심 기능 구현 방법 설명

```
👉 WIKI에 핵심 기능 구현 코드 및 방법 정리
```

[1. 스플래시](https://github.com/TeamMyDaily/4most-Android/wiki/5.1.-%EC%8A%A4%ED%94%8C%EB%9E%98%EC%8B%9C)

[2. 로그인](https://github.com/TeamMyDaily/4most-Android/wiki/5.2.-%EB%A1%9C%EA%B7%B8%EC%9D%B8)

[3. 회원가입](https://github.com/TeamMyDaily/4most-Android/wiki/5.3.-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85)

[4. 키워드 선택](https://github.com/TeamMyDaily/4most-Android/wiki/5.4.-%ED%82%A4%EC%9B%8C%EB%93%9C-%EC%84%A0%ED%83%9D)

[5. 키워드 초기 설정](https://github.com/TeamMyDaily/4most-Android/wiki/5.5.-%ED%82%A4%EC%9B%8C%EB%93%9C-%EC%B4%88%EA%B8%B0-%EC%84%A4%EC%A0%95)

[6. 기록](https://github.com/TeamMyDaily/4most-Android/wiki/5.6.-%EA%B8%B0%EB%A1%9D)

[7. 목표](https://github.com/TeamMyDaily/4most-Android/wiki/5.7.-%EB%AA%A9%ED%91%9C)

[8. 회고](https://github.com/TeamMyDaily/4most-Android/wiki/5.8.-%ED%9A%8C%EA%B3%A0)

[9. 마이페이지](https://github.com/TeamMyDaily/4most-Android/wiki/5.9.-%EB%A7%88%EC%9D%B4%ED%8E%98%EC%9D%B4%EC%A7%80)




<br>

## :six:​ Contributor

```
👉 팀원 소개와 역할 분담
```

<table class="tg">
<tbody>
    <tr>
        <td>김슬기</td>
        <td>문다빈</td>
        <td>신지환</td>
    </tr>
    <tr>
        <td><a href="https://github.com/4z7l">@4z7l</a></td>
        <td><a href="https://github.com/mdb1217">@mdb1217</a></td>
        <td><a href="https://github.com/sgh002400">@sgh002400</a></td>
    </tr>
    <tr>
        <td><img src="/wiki/contributor/4z7l.png" width="300px"/></td>
        <td><img src="/wiki/contributor/mdb1217.jpeg"  width="300px"/></td>
        <td><img src="/wiki/contributor/sgh002400.png"  width="300px"/></td>
    </tr>
    <tr>
        <td>기록, 목표, 마이페이지</td>
        <td>로그인, 회원가입, 회고</td>
        <td>키워드 선택, 키워드 설정, 스플래쉬</td>
    </tr>
</tbody>
</table>

