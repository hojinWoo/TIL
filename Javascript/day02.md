# Javascript day02

### jQuery DOM 조작 방법

1. $('선택자')

   1. $('#id') => id를 검색

      ```javascript
      $('#pw') // => id가 pw인 tag를 반환
      ```

   2. $('.class') => class를 검색

      ```javascript
      $('.email') // => email을 클래스로 가진 모든 tag를 반환
      ```

   3. $('[attribute]') => attribute를 검색

      ```javascript
      $('[value="example"]') // => value가 "example"인 thg를 반환 
      ```

   4. $('tag') => tag를 검색

      ```javascript
      $('span') // => 모든 span 태그를 반환
      ```

   5. $('Parent > Child')

      ```javascript
      $('parent > child > grandchild') // => parent 안에 있는 child 안의 grandchild를 반환
      ```

   6. 선택자들은 조합이 가능

      ```javascript
      $('a.class1.class2') // => class1과 class2를 가진 모든 a tag를 반환
      $('[href].class1.class2') // => href 특성을 지니고 class1과 class2를 가진 모든 tag를 반환
      $('.class1 > li.class2 > a[href="www.naver.com"]')
      ```

2. eq() or :eq()

   1. $().eq(index) => jQuery로 선택된 elements들 중에서 index에 해당하는 element만 반환

      ```javascript
      $('span.an_icon').eq(6).css("display", "none") // => an_icon을 클래스로 가진 span 태그들 중에서 7번째 태그에 접근해서 style="display: none"을 설정해줌
      ```

      ```javascript
      $('span.an_icon:eq(6)').css('display', 'none') // => 위와 동일하게 동작
      ```

3. text(), html(), val()

   1. $().text => 자식 tag들 안에 있는 text 반환

      ```javascript
      $("a.an_a.mn_cafe").text()
      //결과값 "카페"
      ```

   2. $().html => 자식 tag들의 html 소스를 text로 변환하여 반환

      ```javascript
      $("a.an_a.mn_cafe").html()
      //결과값 "<span class="an_icon"></span><span class="an_txt">카페</span>"
      ```

   3. $().val => input tag에 한정해서 쓰는 method로 input 태그 안에 있는 값을 반환

      ```javascript
      $('input#query').val()
      //결과값 "사실 좋은 저녁입니다2"
      ```

   4. 각 함수에 인자를 넣어 값을 바꿀 수 있다.

      ```javascript
      $("a.an_a.mn_cafe").text("커페")
      $("a.an_a.mn_cafe").html("<span>커페에에</span>")
      $('input#query').val("사실 좋은 저녁입니다")
      
      $("a.an_a.mn_cafe").text() // 결과값 => 커페에에
      $("a.an_a.mn_cafe").html() // 결과값 => "<span>커페에에</span>"
      $('input#query').val()     // 결과값 => "사실 좋은 저녁입니다"
      ```

4. find()

   ```javascript
   $('ul.an_l > li > a > span') // an_l을 클래스로 가진 ul 태그 안에 있는 li 태그 안에 있는 a 태그 안에 있는 span 태그들을 전부 찾아서 반환
   
   $('ul.an_l').find('span') // an_l을 클래스로 가진 ul 태그 안에서 span 태그들을 전부 찾아서 반환
   
   $('ul.an_l > span') // 1
   $('ul.an_l > li > span') // 2
   $('ul.an_l > li > a > span') // 3
   // find == 1+2+3
   ```

5. first(), last(), prev(), next(), parent(), children()

   ```javascript
   $().first() // => elements 중에 첫 번째 element를 반환
   $().last() // => elements 중에 마지막 element를 반환
   $().prev() // => 이전 element를 반환
   $().next() // => 다음 element를 반환
   $().parent() // => 부모 element를 반환
   $().children() // => 자식 elements를 반환
   ```

6. append(), prepend(), before(), after()

```javascript
<ul>
    <li></li>
    <li></li>
    <li></li>
</ul>

$('ul').append('<li class="new"></li>')

<ul>
    <li></li>
    <li></li>
    <li></li>
	<li class="new"></li>
</ul>

$('ul').prepend('<li class="new"></li>')

<ul>
    <li class="new"></li>
    <li></li>
    <li></li>
    <li></li>
	<li class="new"></li>
</ul>

$('ul').before('<li class="new"></li>')

<li class="new"></li>
<ul>
    <li class="new"></li>
    <li></li>
    <li></li>
    <li></li>
	<li class="new"></li>
</ul>

$('ul').after('<li class="new"></li>')

<li class="new"></li>
<ul>
    <li class="new"></li>
    <li></li>
    <li></li>
    <li></li>
	<li class="new"></li>
</ul>
<li class="new"></li>
```

1. remove(), hide(), show(), toggle()

```javascript
$().remove() // => element 제거
$().hide() // => element 숨김
$().show() // => element 표시
$().toggle() // => element 숨김/표시, 마치 플립플롭 처럼..
```

1. replaceWith()

   ```javascript
   $(element).replaceWith(new_element) // => element를 new_element로 교체
   ```

2. attr()

   ```javascript
   $(element).attr('href') // => element의 attribute에 있는 값을 반환
   $(element).attr('href', 'http://www.daum.net')
   // => element의 attribute 값을 변경
   ```

3. on() == addEventListner()

   ```javascript
   $(element).on(event, fuction(){...}) // => element에서 event 발생 시 function() 실행
   $(parent_element).on(event, child_element, function(){...}) // => parent_element 안에 있는 child_element에서 event 발생시 function(){} 실행 => delegated event handler
   
   //예시
   $(document).on('mouseover', 'a', function(){alert('why!?')});
   ```

## DOM 객체 이벤트

### 이벤트 중단 방법

```javascript
event.preventDefault() // => 현재 이벤트의 기본 동작을 중단, 상위로 전파는 막지 않음

event.stopPropagation() // => 현재 이벤트가 상위로 전파되지 않도록 중단

event.stopImmediatePropagation() // => 현재 이벤트가 상위 뿐 아니라 현재 레벨에 걸린 다른 이벤트도 동작하지 않도록 중단

return false // => ajax까지 실행되지 않도록 하는 걸 봐서는 그냥 이벤트 자체가 메모리에서 내려가는 듯.. return false를 안하면 ajax는 실행 된다
```