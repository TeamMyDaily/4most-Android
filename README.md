# MyDaily (2021/01/06 Ver.)

> 대충 로고 넣을 예정

기록은 더 쉽게<br>
회고는 더 깊게<br>
어제보다 나은 하루의 시작, 마이데일리<br>


<br>

# Project
- SOPT 27th AppJam MyDaily
- 프로젝트 기간 : `2020.12.26` ~ `2021.01.16`


<br>

# Contents

* [1. Specification](#1-specification)
* [2. Package Structure](#2-package-structure)
* [3. WIKI](#3-wiki)
* [4. 핵심 기능 구현 방법 설명](#4-핵심-기능-구현-방법-설명)
* [5. Contributor](#5-contributor)



<br>

## 1. Specification

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
<td>DataBinding, LiveData, ViewModel Lifecycles</td>
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
<td><a href="https://github.com/skydoves/ExpandableLayout">ExpandableLayout</a></td>
</tr>
<tr>
    <td><b>Other Tool</b></td>
<td>Notion, Slack</td>
</tr>
</tbody>
</table>

<br>

## 2. Package Structure

```
📦 org.mydaily
 ┣ 📂 data
 ┃ ┗ 📂 local
 ┃ ┗ 📂 model
 ┃ ┗ 📂 remote
 ┃ ┗ 📂 repository
 ┣ 📂 di
 ┣ 📂 ui
 ┃ ┗ 📂 adapter
 ┃ ┗ 📂 base
 ┃ ┗ 📂 view
 ┃ ┗ 📂 viewmodel
 ┣ 📂 util
 ┃ ┗ 📂 extension
 ┗ 📜 MyDailyApplication.kt
```



<br>

## 3. WIKI

```
👉 회의록 및 Project Rule(Git, Coding Convention)
```
- [HOME](https://github.com/TeamMyDaily/MyDaily_Android/wiki)
  - [1. Git 사용법](/TeamMyDaily/MyDaily_Android/wiki/1.-Git-사용법)
  - [2. Git Commit Message Convention](/TeamMyDaily/MyDaily_Android/wiki/2.-Git-Commit-Message-Convention)
  - [3. Android Coding Convention](/TeamMyDaily/MyDaily_Android/wiki/3.-Android-Coding-Convention)
  - [4. 회의록](/TeamMyDaily/MyDaily_Android/wiki/4.-회의록)


## 4. 핵심 기능 구현 방법 설명

```
👉 WIKI에 핵심 기능 구현 코드 및 방법 정리
```

- [키워드](https://github.com/TeamMyDaily/MyDaily_Android/wiki/%ED%82%A4%EC%9B%8C%EB%93%9C)
- [평가 및 회고](https://github.com/TeamMyDaily/MyDaily_Android/wiki/%ED%8F%89%EA%B0%80-%EB%B0%8F-%ED%9A%8C%EA%B3%A0)
- [기록](https://github.com/TeamMyDaily/MyDaily_Android/wiki/%EA%B8%B0%EB%A1%9D)




<br>

## 5. Contributor

```
👉 팀원 소개와 역할 분담
```

<table class="tg">
<tbody>
    <tr>
        <td><img src="https://avatars2.githubusercontent.com/u/45537782?s=460&u=cd7933246795bbd5c14c5a6d69372f084e5761ee&v=4" width="100px"/>
</td>
        <td><a href="https://github.com/4z7l">@4z7l</a></td>
        <td>로그인, 기록, 기록 상세</td>
    </tr>
    <tr>
      <td><img src="https://avatars1.githubusercontent.com/u/70698151?s=460&u=8a26000faa6a3dbbf44379c6685e029a904368e5&v=4" width="100px"/>
</td>
        <td><a href="https://github.com/mdb1217">@mdb1217</a></td>
        <td>회원가입, 평가 및 회고</td>
    </tr>
    <tr>
      <td><img src="https://avatars0.githubusercontent.com/u/62228195?s=460&u=0db0c848c4347b49176824d285f37ef04d17401a&v=4" width="100px"/></td>
        <td><a href="https://github.com/sgh002400">@sgh002400</a></td>
        <td>키워드 선택, 키워드 추가</td>
    </tr>
</tbody>
</table>

