# 멋쟁이 사자처럼 2일차 

cf) 멋사는 코드 아카데미를 적극 추천한다. 

(기본 구문 및 내용 다시 참고하려면 code academy 다시 볼 것)



### 0. Ruby 기초

- 순수 객체 지향 언어 (everything is Object)

- 2005년 Ruby on Rails framework 등장으로 유명.



- 2칸 띄어스기 추천(Space indentation)
  : 파이썬과 달리 강제성 X
- 세미콜론으로 한 줄에 쓸 수 있으나 거의 안 쓴다.

```ruby
puts 123; puts 456
```



#### print vs put vs p 

> print : 한줄에 출력
>
> puts : 개행문자 포함
>
> p : inspect까치 출력

```ruby
array = [1,2,3]

print array
#[1,2,3] => nil

puts array
#1
#2
#3
# => nil

p array 
#[1,2,3]
# => [1,2,3]
```



#### Naming Conventions (ruby's recommend)

- 변수
  - snake_case
- 상수
  - CONSTANT (all upper letter)
- 클래스(Classes or Module)
  - CamelCase



#### IRB vs PRY

IRB, PRY는 python의 IDLE와 같음

pry는 GUI가 Highlighting 처리가 된다

- 설치

  - `gem install pry`

- 실행

  - `pry`

    

#### Inline Statement

```ruby
#if statement
puts "a = 0" if a==0 # a = 0 =>nil
puts "a = 0" if a==1 # =>nil

#while statement
c = 0
result = c + =2 while c <50
puts result #50
```

> 조건문을 오른쪽에 쓴다.



#### Ruby FoC

- 0 is true (false : false, nil)

- ''===' :  의미상 맞을 경우 true

- Case statement

  ```ruby
  name = "hojin"
  case name
  	when "ho"  
  		puts "hi ho"
  	when "hojin"  
  		puts "hi hojin"
  	else  
  		puts "who are you?"                                   
  end  
  # hi hojin
  ```

  

### 1. Ruby functions / Methods



- 대부분의 언어

  - class 밖 : function
  - class 안 : methods

- 루비에서는 모든 function은 method (ruby에서는 function 말 사용 X)
  (cf. ruby는 arg가 없는 경우 ()를 생략을 많이 한다..)

  ```ruby
  # Declare method
  
  # <ex1>
  def simple
      puts "hello simple"
  end
  simple #hello simple
  
  # <ex2>
  def simple()
      puts "hello simple"
  end
  ```

- return 키워드 선택적으로 사용 가능

  ```ruby
  # <ex1>
  def add(a,b)
      return a+b
  end
  add 1,2  #3
  
  # <ex2>
  def add2(a,b)
      a+b
  end
  add2(4,5) #9
  
  # <ex3>
  def divide(a,b)
      return "I don't know" if b==0
      a/b
  end
  divide(4,0) #I don't know
  divide(4,2) #2
  ```

  