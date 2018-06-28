# Javascript

## 자료형

1. 자료형

   1. 숫자
   2. 문자
   3. null : 아무것도 없다는 값으로 들어있음
   4. undefined : 값이 할당되어 있지 않음
   5. 논리(boolean) : true, false
   6. 객체(Object) : { } // hash
      1. 키와 값을 쌍으로 가짐
         1. var o = {key: value}
      2. 함수를 값으로 가질 수 있음
         1. method? property?
            1. value에 함수가 들어있을 경우, method
            2. 그 외의 값은 property
   7. 배열(array) => [ ]
      1. 유용한 methods
         1. pop() : 맨 뒤에 값을 빼낸다(stack)
         2. shift() : 맨 앞에 값을 빼낸다(queue)
         3. unshift(Object) : 맨 앞에 Object를 넣는다
         4. push(Object) : 맨 뒤에 Object를 넣는다
         5. sort() : 정렬
         6. reverse() : 역순 정렬
         7. indexOf(Object) 
            배열에서 Object의 위치 반환(0부터 시작)
         8. forEach(function(Element))
            배열의 요소 하나하나를 인자로 function()을 실행
         9. map(function(Element))
            forEach의 결과를 배열로 반환 → function()에 return이 있어야 한다.
         10. filter(function(Element)) 
             배열의 요소 하나하나를 인자로 function()을 실행한 후  값이 true인 인자들 만 반환 -> 즉, function()의 return은 boolean이어야 한다.
         11. reduce(function(Element1, Element2)) 
             배열의 요소 처음 두 개를 인자로 function()을 실행한 후 그 결과와 배열의 다음 요소를 인자로 수행하기를 반복한다.

2. 함수

   1. 함수?

   2. ##### 함수 표현식

      - 함수 선언문
      - 함수 표현식

      ```javascript
      // 실제 작성 코드1: 함수 선언문
      sum(1,2);
      function sum(x,y) {
          console.log(x+y);
      }
      /* 위의 코드가 실행되는 순서
      function sum(x,y) {
          console.log(x+y);
      }
      sum(1,2);
      */
      ```

      ```javascript
      // 실제 작성 코드2: 함수 표현식
      sum(1,2);
      var sum = function(x,y) {
          console.log(x+y);
      }
      /* 위의 코드가 실행되는 순서
      var sum;
      sum(1,2);
      sum = function(x,y) {
          console.log(x+y);
      }*/
      // 함수 표현식에서는 호출 전에 미리 써야 한다.
      ```

      > Javasctipt는 변수와 함수의 선언문을 코드 맨 위로 올리는 작업을 선행한다.

   3. ##### 함수의 다양한 용도(argument, return value)

      ```javascript
      //map 만들어보기
      var arr = [1,2,3,4,5];
      var double = function(x) {return x*2};
      //var arr2 = arr.map(double)
      
      var arr2 = map(double, arr)
      function map(func, arr){
          var new_arr = [];
          for (element of arr){
              new_arr.push(func(element));
          }
          return new_arr;
      }
      arr2 //[2, 4, 6, 8, 10]
      //////////////////////////////////////////////////////////////////////////
      //filter 만들어보기
      var positive = function(x){return x>0};
      //positive(1) => true
      //positive(-1) => false
      var arr = [-1, 3, -5, 7, -9]
      //var arr2 =  arr.filter(positive)
      
      var arr2 = filter(positive, arr) 
      function filter(func, arr){
          var new_arr = [];
           for (element of arr){
              if(func(element)){
              	new_arr.push(element);
              }
          }
          return new_arr;
      }
      arr2 //[3,7]
      //////////////////////////////////////////////////////////////////////////
      // return func
      function func1(){
          return function func2(){
              //inner function
              console.log("hello js");
          }
      }
      var test = func1(); // test = func2
      test(); //hello js
      ```

   4. ##### 함수의 인자 사용법

      ```javascript
      function sum(a,b){
          console.log(a+b);
      }
      sum(1); 	//NaN(not a number)오류가 아닌 결과가 나온다 (1+undefined)
      		    //받은 값을 사용하고 못 받은 값은 undefined로 사용하기 때문 
      sum(1,2,3); //3  더 들어온 인자도 arguments로 사용 가능
      //arguments = [1,2,3]
      
      function test (a,b){
          console.log(arguments);
      }
      test(1) 	//Arguments [1, callee: ƒ, Symbol(Symbol.iterator): ƒ]
      test(1,2) 	//Arguments(2) [1, 2, callee: ƒ, Symbol(Symbol.iterator): ƒ]
      test(1,2,3) //Arguments(3) [1, 2, 3, callee: ƒ, Symbol(Symbol.iterator): ƒ]
      
      // 모든 argument 사용하는 방법
      function sumT (a,b){
          var total = 0;
          for(element of arguments){
              total += element
          }
          console.log(total);
      }
      sumT(1,2,3,4,5) //15
      
      function multipleT(a,b){
          var total = 1;
          for(element of arguments){
              total *= element
          }
          console.log(total); 
      }
      multipleT(1,2,3,4,5)
      ```

   5. ##### 변수 scope

      ```javascript
      var i = 0;
      function changeI(){
          //선언하지 않았기 때문에 전역변수인 i를 가져와서 값을 바꾼다.
          i = 10;
          console.log(i);
      }
      changeI();		//10
      console.log(i);	//10
      
      var i = 0;
      function changeI(){
          //지역변수
          var i = 10;
          console.log(i);
      }
      changeI();		//10
      console.log(i);	//0
      //////////////////////////////////////////////////////////////////////////
      //정적 유효범위
      // ex1-1)
      var i = 0;
      function a(){
          var i =10;
          b(); //함수 호출 시 변수 참조X
      }
      function b(){
          console.log(i); //함수 선언 시 변수 참조 
      }
      a() //0     10이 아닌 0이 나온 이유는 함수 선언 시 var i = 0 (전역변수)이기 때문
      // ex1-2)
      i = 10;
      a() //10
      // ex2)
      var i = 0
      function a(){
          var i = 10;
          function b(){
          	console.log(i); //var i = 10;
      	}
          b();
      }
      a() //10
      //////////////////////////////////////////////////////////////////////////
      // closure : java api 사용 시 많이 사용하게 된다.
      var i = 0;
      function a(){
          var i = 10;
          return function b(){
              console.log(i);
          }
      }
      var closure = a();
      closure() //10
      ```

   6. ##### Hoisting (끌어올림)

      ```javascript
      //Ex1) 
      var i = 0;
      
      func(); 
      //func!!
      
      function func(){
          console.log("func!!")
      }
      /////////////////////실행 시 코드///////////////////
      //변수와 함수가 먼저 메모리에 저장되어서 실행
      function func(){
          console.log("func!!")
      }
      var i;
      console.log(i); //undefined
      i=0;
      
      func();        //func!!
      ```

      ```javascript
      //Ex2) 함수 내에서도 이루어지는 hoisting
      var i = 0;
      function func2(){
          console.log(i);
          var i = 10;
      }
      func2()
      //undefined
      //undefined (return 된 것이 없기 때문)
      /////////////////////실행 시 코드///////////////////
      var i = 0;
      function func2(){
          var i;
          console.log(i);
          i = 10;
      }
      func2()
      ```

      ```javascript
      var language = 'JS';
      function checkScript(script){
          if (script) {
              var language = "ruby";
              console.log(language);
          }else{
              console.log(language);
          }
      }
      checkScript(true); //ruby
      checkScript(false); //undefined	
      /////////////////////실행 시 코드///////////////////
      var language = 'JS';
      function checkScript(script){
          var language;
          if (script) {
              language = "ruby";
              console.log(language);
          }else{
              console.log(language);
          }
      }
      
      /////////////////////해결 코드1///////////////////
      var language = 'JS';
      function checkScript(script){
          var language = "ruby";
          if (script) {
              language = "ruby";
              console.log(language);
          }else{
              console.log(language);
          }
      }
      /////////////////////해결 코드2///////////////////
      var language = 'JS';
      function checkScript(script){
          if (script) {
              let language = "ruby";
              console.log(language);
          }else{
              console.log(language);
          }
      }
      ```

   7. #####  this

      ```javascript
      var globalThis = null;
      
      //1. In function
      function this1(){
          globalThis = this; //현재 코드가 실행되는 browser의 창
      }
      this1();
      globalThis
      //Window {postMessage: ƒ, blur: ƒ, focus: ƒ, close: ƒ, frames: Window, …}
      
      //2. In Methods
      // this는 method를 사용하는 객체
      var o = {
          p1: 'property1',
          m1: this1
      }
      o.m1();
      globalThis //{p1: "property1", m1:this1()}
      
      //2-1 ex2)
          var o2 = {
              prop1: 1,
              method: function(){
                  console.log(this.prop1) //this.prop1 == o2.prop1
              }
          }
          o2.method() //1
      
      //ex 3-1). 생성자에서 사용되는 this
      //다른 언어처럼 class로 만드는 것은 (최신 버전에서는 가능은 하나) 안 쓰고 함수를 만든다
      // 생성자 함수 (class와 같은 역할)
      function Person(name){
          // this는 생성된 객체를 의미.
          this.name = name;
      }
      var p1 = new Person("sada"); //this == p1
      p1.name //sada
      
      //ex 3-2)
      //받을 대상이 없는 경우 windolw 촐ㅇ
      var globalThis = null;
      function this1(){
          globalThis = this; 
      }
      var o1 = new this1();
      
      gloabalThis // o1
      ```

   8. ##### this와 관련된 methods 예제

      1. call(this에 해당하는 대상, arguments1, arguments2)

         ```javascript
         //Ex1)
         function Person(name){
             this.name = name;
         }
         var p1 = new Person('asdf');
         var p2 = {};
         Person.call(p2, "fdsa"); //call(this, arguments)
         p2 //{name: "fdsa"}
         
         //Ex2)
         function Person(name, age){
             this.name = name;
             this.age = age;
         }
         var p3 = {};
         Person.call(p3, 'hhh', 33)
         p3 //{name: "hhh", age: 33}
         
         //Ex3)
         var globalThis = null;
         function testFunc(){
             globalThis = this; //전역변수 참조
         }
         var testVar = 20;
         testFunc.call(testVar); //call을 이용해서 값을 전달 => 첫번째 인자는 this로 이동함
         gloablThis //Number {20}
         
         //Ex4)
         var globalThis = null;
         function testFunc(a,b){
             globalThis = this; //전역변수 참조
             console.log(a+b)
         }
         testFunc.call(20, 1, 2); //3
         gloablThis				//20
         ```

      2. apply

         ```javascript
         //call methods와 비슷하다. argument를 넣는 방식만 다르다.
         Person.call(p3, 'hhh', 33) 	  //','로 argument를 일일이 전달해야 한다.
         Person.apply(p3, ['hhh', 33]) // 배열 안에 넣어서 전달이 가능하기 때문에 더 자주 사용
         //(this, [argument1, argument2, ..])
         ```

      3. bind

         ```javascript
         //bind(this에 해당하는 대상) & 함수가 실행X - 지정만 한다
         var globalThis = null;
         function testFunc(a, b){
             globalThis = this;
             console.log(a+b)
         }
         testFunc(1,2); //3
         globalThis;    //window 객체, 아무것도 없기 때문에
         
         var bindedFunc = testFunc.bind(20); //this에 20이 지정 된다
         bindedFunc();
         globalThis //Number {20}
         ```

   9. ##### Closure (외부 함수의 변수들에 접근 가능한 내부 함수)

      ```javascript
      // 정적 유효 범위 : 함수 선언 할 때 전역변수를 추적한다. 
      var arr = [];
      for(var i=0; i<10; i++){
          arr.push(function(){
              return i*20;
          })
      }
      // array의 index를 순서대로 표현할 수 있다.
      for(j in arr){
          console.log(arr[j]());
      }//10 200 (200이 10번 나옴.) i==10이 된 것을 계속 추적한다
      
      
      var arr = [];
      for(i=0; i<10; i++){
          var k = 0;
          arr.push(function(){
              k += 1;
              return k*20;
          })
      }
      for(j in arr){
          console.log(arr[j]());
      }//20 40 60 .. 200
      
      
      //closure
      //외부 함수는 내부 함수에 접근 불가
      var i = 0;
      function outer(i, j, k){
          var i = 10;
          var j = 20;
          var k = 30;
          function inner(){
              console.log(i);
              console.log(j);
              console.log(k);
          }
          return inner;
      }
      var closure = outer(10, 20, 30); // i == 10, j == 20, k == 30
      					//inner 함수에서는 함수 outer의 변수인 i, j, k에 접근 가능
      					// outer에서는 inner에 있는 변수 접근X
      closure() // 10, 20, 30
      
      //다른 표현 방법 : 함수 호출 및 변수 선언
      var closure = function outer(i){
          return function inner(){
              console.log(i)
          }
      }(1);
      
      //closure를 사용한 함수 
      //ex1
      var arr = [];
      for(var i=0; i<10; i++){
          arr[i] = function outer(i){
              function inner(){
                  return i*20;
              }
              return inner;
          }(i);
      }
      for(j in arr){
          console.log(arr[j]());
      } // 0, 20, 40, 60, ... 180
      //ex2
      var arr = [];
      function outer(i){
          return function inner(){
              return i*20;
          }
      }
      for(var i=0; i<10; i++){
          arr[i] = outer(i);
      }
      for(j in arr){
          console.log(arr[j]());
      }// 0, 20, 40, 60, ... 180
      ```

      
