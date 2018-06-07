# 멋쟁이 사자처럼 2일차 

ruby : 2.4.0

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



#### Inclusive and Exclusive Ranges

```ruby
#... : don't include the highest number in the range
for num in 1...5
    print num
end
#1234

#.. : include the highest number in the range
for num in 1..5
    print num
end
#12345
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

- ##### return 

  ```ruby
  # <ex1>
  def add(a,b)
      return a+b
  end
  add 1,2  #3
  
  # <ex2>
  # return이 없는 경우 마지만 연산 값 자동 return
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

  

- '?' 사용 및 argument사용

  ```ruby
  def can_divide_by?(num)
  end
  
  #삼항 연산자 사용
  #: 왼쪽 조건문 true, 오른쪽 false
  def factorial(n)
      n == 0 ? 1 : n*factorial(n-1)
  end
  factorial(5) #120
  factorial # 호출 시 argument가 없을 경우 ArgumentError
  
  #default로 n의 값 정의
  def factorial_default(n=5)
      n == 0 ? 1 : n*factorial(n-1)
  end
  factorial_default #120
  ```

- 스플랫 (잘 사용은 안함)

  

- ##### Block : {} or  do...end

  ```ruby
  3.times {puts "hello"}
  
  3.times do |asdf|
      puts asdf
  end
  #0
  #1
  #2
  # => 3
  
  # implicit
  def isblock
      return "No block" unless block_given?
      yield # block안에 있는 내용을 가리키게 된다.
  end
  isblock {puts "bb"}
  
  # explicit
  def isblock(&block)
      return "No block" if block.nil?
      block.call
  end
  ```

  ​	

- 파일 읽기, 쓰기

  ```ruby
  # <ex1>
  File.foreach('test.txt') do |line|
      puts line
      p line
      p line.chomp
      p line.split 
  end
  
  # exception ex1
  begin
      File.foreach('') do |line|
          puts line.chomp
  end
  
  rescue
  end
  
  # exception ex2
  if File.exist? ''
      File.foreach('') do |line|
          puts line.chomp
  	end
  end
  ```



- ENV (OS 환경변수)

  ```ruby
  set # local path 등에 대한 설명을 출력
  ```



### 2. String

- https://ruby-doc.org/core-2.4.0/String.html

  

- Single quote vs Double quote

  ```ruby
  # single quote
  a = 'hello everyone\n welcome'
  puts a #hello everyone\\n welcome
  
  # double quote cf. %Q{}로도 쓸 수 있다..
  b = "hello everyone\nwelcome"
  puts b
  #hello everyone
  #welcome
  
  hi = %Q{hihi}
  puts hi #hihi
  
  name = "hojin"
  puts '이 글 쓴 사람의 이름은 #{name}입니다.'
  #이 글 쓴 사람의 이름은 #{name}입니다.
  puts "이 글 쓴 사람의 이름은 #{name}입니다."
  #이 글 쓴 사람의 이름은 hojin입니다.
  ```

- #### ! 사용

  객체를 바꾼다.

  

- #### Symbol

  - ':' 

  - 문자열을 아니다

  - 고유(unique)하고 변경이 불가능

  - String과 서로 변환 가능

  - Pointer와 약간 비슷하다

    

### 3. Array

  - https://ruby-doc.org/core-2.4.0/Array.html

  - 동적 할당

  - 생성
    - [obj1, obj2, ...]
    - Arrays.new(len)
    - %w {str1, str2}
    - 생성시 이질적 배열 (Heterogeneous arrays)으로 사능

  - python과 같이 음수 및 범위 (range)로 index 쓸 수 있다

  - 추가 

    - <<
    - .push

  - 삭제

    - .pop  (가장 오른쪽에 있는 것)
    - .shift (가장 왼쪽에 있는 것)




### 4. Hash

- key - value로 구성

- 생성

  ```ruby
  #key와 ':'는 띄어쓰기 하지 말 것, 붙여서 써야 한다
  hash1 = {"key" => value}
  
  # key (symbol) & hash rocket
  hash2 = {:key => value}
  
  hash3 = {key: value} 
   
  # 3가지를 혼합해서 사용해도 가능
  hash0 = {name: "hojin", :age => 26, "live" => youngin}
  
  # 0을 return
  h0 = Hash.new(0)
  
  # 반복문
  hash0.each do |k,v|
      puts "#{k} : #{v}"
  end
  ```

- index

  - 보통 key는 string이 아닌 symbol이다
  - key가 없을 시 nil이 리턴

- Block과 헷갈리지 말 것



## 참고 주소

### 알아두면 도움이 되는 55가지 루비 기법

https://gist.github.com/nacyot/7624036