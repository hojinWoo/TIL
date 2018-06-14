# 멋쟁이 사자차럼 3일차 

## Ruby 문제 풀기 

- #### test를 할 때 주로 사용하는 방법- 검증도 된다

   - ruby는 Tdd방식에 최적화 되어있음
   - Rails 할 때 더 자주 사용하게 됨.
   - 시간 체크도 된다.

  ```ruby
  # test.rb
  # 파일의 경로를 찾아서 추가
  require_relative 'q1.rb'
  require 'test/unit'
  
  # < : inheritance
  class TestInteger < Test::Unit::TestCase
      @@integer_test = IntegerQuiz.new
      
      # methods
      def test_evencheck
          # assertions - validate
          assert_equal("Even", @@integer_test.evencheck(5)) 
      end
  end
  
  
  $ ruby test.rb
  ```

- unit test vs rspec
- [assert](https://ruby-doc.org/stdlib-2.1.1/libdoc/test/unit/rdoc/Test/Unit/Assertions.html)

- 변수
  - Instance변수
    `@num`
  - class 변수
    `@@num`



### Integer

* 짝수/ 홀수

  ```ruby
  #q1.rb
  
  # num이 짝수일 경우 "Even", 홀수일 경우 "Odd"를 반환
  class IntegerQuiz
  	def solution(num)
  		num%2==0 ? "Even" : "Odd"
      	# num.even? ? "Even" : "Odd"
  	end
  end
  ```


* 최대공약수/최소공배수

  ```ruby
  # 두 수를 입력 받아 최대공약수와 최소공배수를 배열로 반환
  # 예) n=2, m=12 
  # => [3,12] 
  def solution(n,m)
  	# n.gcdlcm(m)
      def gcd(n,m)
          if n < m 
             #숫자 좌우 바꾸기
             m, n = n, m 
          end
          if m == 0
              #return은 생략 가능
              return n 
          end
          if n % m ==0
              m
          # euclid algorithm & recursive
          else
              gcd(m, n%m)
          end
      end
      
      def gl(n, m)
          a[0] = gcd[n,m]
          a[1] = (n*m)/a[0]
  		return a
      end
  end
  ```

* 소수 찾기

  ```ruby
  # 1부터 입력받은 숫자 n 사이의 소수의 개수를 반환
  # 예) n = 10
  # => 4
  def solution(n)
  	#Prime.each(n).count
      
      primes = (2..n).to_a
      #primes = [2,3,4,5,...,n]
      (2..n).each do |i|
      	(2...i).each do |j|
              if(i%j==0)
                  primes.delete(i)
                  break
              end
          end
      end
      return primes.length
  end
  ```


* 약수의 합

  ```ruby
  # 자연수 n을 입력받아 n의 약수를 모두 더한 값을 반환
  # 예) n = 12 
  # => 28
  def solution(n)
      # Array
  	(1..n).select{|div| n % div==0}.sum
  end
  ```
### String

* 문자열을 정수로

  ```ruby
  # 문자열 s를 숫자로 변환
  # s = "4885"
  # => 4885
  # s2 = "-486"
  # => -486
  def solution(s)
    #코드입력
  end
  ```
  
* 수박수박수박수박수?

  ```ruby
  # 길이가 n인 수박수...패턴의 문자열을 리턴
  # 예) n = 4 => "수박수박"
  # 예) n = 5 => "수박수박수"
  def solution(n)
    #코드입력
  end
  ```
  
* 문자열을 내림차순으로

  ```ruby
  # 문자열 s의 문자들을 큰것부터 작은 순으로 정렬
  # 대소문자로 구성, 대문자 < 소문자
  # 예) "Likelion" => "onlkiieL"
  def solution(s)
    #코드입력
  end
  ```
  
* 가운데 글자

  ```ruby
  # 문자열 s의 가운데 글자를 리턴
  # s의 길이가 짝수이면 가운데 두글자를 리턴
  # 예) "abc" => "b", "likelion" => "el"
  def solution(s)
    #코드입력
  end
  ```
