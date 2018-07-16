# [Ruby Regular Expression](http://rubular.com/)

| Regex quick reference | Meaning |
| ---------- | -------------------------------------------- |
| **`[abc]`** | A single character of: a, b, or c            |
| **`[^abc]`** | Any single character except: a, b, or c      |
| **`[a-z]`** | Any single character in the range a-z        |
| **`[a-zA-Z]`** | Any single character in the range a-z or A-Z |
| **`^`**    | Start of line                                |
| **`$`**    | End of line                                  |
| `\A`       | Start of string                              |
| `\z`       | End of string                                |
| `.`  | Any single character, 개행문자 제외                   |
| `\s` | Any whitespace character                        |
| `\S` | Any non-whitespace character                    |
| **`\d`** | Any digit                                       |
| `\D` | Any non-digit                                   |
| **`\w`**,  **`[0-9a-zA-Z_]`** | Any word character (letter, number, underscore) |
| `\W` | Any non-word character                          |
| `\b` | Any word boundary                               |
| `(...)`  | Capture everything enclosed |
| `(a|b)`  | a or b                      |
| **`a?`** | Zero or one of a            |
| **`a*`**, **`a{0, }`** | Zero or more of a           |
| **`a+`**, **`a{1,}`** | One or more of a            |
| `a{3}`   | Exactly 3 of a              |
| `a{3,}`  | 3 or more of a              |
| `a{3,6}` | Between 3 and 6 of a        |



### Example

1. 단순패턴 사용

   ```ruby
   /app/
   #This app is made by apple!
   ```

   This **app** is made by **app**le!



2. 문자열 사용하기

   1. `^` : 문장 가장 앞
      `/^apple/`

      - pineapple => X
      - apple => `apple`

   2. `$` : 문장 가장 뜻

      `/$apple/`

      - pineapple =>  `apple`
      - apple => X

   

3. 응용
   `[a-z]+` : 단어 단위로 찾게 된다.
   `[a-z]` : 알파벳 단위로 찾게 된다.

   

4. `gsub` 사용

   ```ruby
   text = "hello ruby! this is made by ruby"
   text.gsub(/hello/,'hi')
   #"hi ruby! this is made by ruby"
   ```

   

5. `.`
   `a.e` : `ate`, `apple`
   `a.+e` : `ate`, `apple` 

   

6. **Match Group**
   `(i.+?n)` : i부터 n으로 끝나는 단어들을 그룹으로 묶어준다

   ##### example, html코드에서 문법 맞는 것 찾기

   `<(h[1-6])>[a-z]+<\/\1>`

   ```ruby
   <h1>hi</h1>
   <h2>title</h2>
   <h3>hello</h4> #일치하지 않는 경우는 찾아서 그룹으로 하지 않는다
   ```

   > `<h[1-6]>[a-z]+<\/h[1-6]>`  : 틀린 tag로 되어도 찾게 된다.

   

7. ##### Greedy

   ex) `internationalization`

   `i\w+n` => `internationalization`

   `i\w+?n` => `intern`at`ion`al`ization`

   

8. ##### 전방탐색/후방탐색

   - 전방탐색 `(?=_)`
     `\d+(?=원)` : 뒤에가 원이라고 써져 있는 전방(숫자)을 탐색
     

   - 부정형 전방탐색

     `\.\d+(?!\.)` : 정수가 아닌 소수에 있는 숫자만 탐색
     

   - 후방탐색 `(?<=_)`
     `(?<=원)+\d+` : 앞에가 원이라고 써져 있는 후방(숫자)을 탐색



### [이메일 찾기](https://ko.wikipedia.org/wiki/%EC%A0%84%EC%9E%90_%EC%9A%B0%ED%8E%B8_%EC%A3%BC%EC%86%8C)

##### 로컬 파트

전자 우편 주소의 로컬 파트는 아래의 ASCII 문자 중 어느 것이든 올 수 있다:

- 대소문자 [로마자 기본](https://ko.wikipedia.org/wiki/%EB%A1%9C%EB%A7%88%EC%9E%90_%EA%B8%B0%EB%B3%B8) A부터 Z, a부터 z
- 숫자 0부터 9
- 특수 문자 !#$%&'*+-/=?^_`{|}~
- 점 (.)
- 공백과 "(),:;<>@[\] 문자
- 괄호 안에 사용되는 주석 (예: john.smith(comment)@example.com, (comment)john.smith@example.com)

##### 도메인

이메일 주소 중 도메인 이름은 명확한 지침을 따라야 한다. [호스트명](https://ko.wikipedia.org/wiki/%ED%98%B8%EC%8A%A4%ED%8A%B8%EB%AA%85)의 요구 사항, 점(.)으로 구분되는 [DNS](https://ko.wikipedia.org/wiki/DNS) 레이블의 목록, 각 레이블이 63자를 넘지 않는 등의 요구 사항을 충족해야 하며, 다음으로 구성되어야 한다:[[2\]](https://ko.wikipedia.org/wiki/%EC%A0%84%EC%9E%90_%EC%9A%B0%ED%8E%B8_%EC%A3%BC%EC%86%8C#cite_note-rfc3696-2)

- 대소문자 [로마자 기본](https://ko.wikipedia.org/wiki/%EB%A1%9C%EB%A7%88%EC%9E%90_%EA%B8%B0%EB%B3%B8) A부터 Z, a부터 z
- 숫자 0부터 9 (최상위 도메인 이름이 모두 숫자가 아닌 경우)
- 하이픈 - (처음 또는 마지막 글자가 아닌 경우)



```ruby

```

