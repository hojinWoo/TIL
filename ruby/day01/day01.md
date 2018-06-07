# 멋쟁이 사자처럼 1일차 

### Session1 Ruby 프로그래밍 맛보기

#오픈소스 #컴프 #루비

C9를 통해 web에서 할 수 있음

목표 : 시나트라를 통해 web service만들기 (2일) 



##### 왜 프로그래밍을 배워야 하는가? 

1.  일자리 변화
   1. 프로그래밍 이해가 있는 직원들과의 communication
   2. S/W중심
2. 프로그래밍 == MS office 
   1. 업무 위해 필수 도구인 office (10~20년 전 excel) 
   2. sw시대의 필수 도구 
3. 프로그래밍 == 요리 
   1. 필요한 것을 직접 만들 수 있다 



##### 프로그래밍의 혜택 

Life Hacking, Work Hacking



Computer : 저장 연산

programming language: 시키기 위해 사용 



## Ruby

저장, 조건, 반복

모든 것이 객체다 (nil, 숫자 또한 객체)



##### 확장자 

.rb



##### bash shell에서 실행하기

```
# rubby command 실행하기
$ irb 

# ruby 파일 실행
$ ruby aaa.rb

# install library awesome print
# (https://github.com/awesome-print/awesome_print)
$ gem install awesome_print
```

```ruby
require 'awesome_print'
# ap object, options = {}

#Integer
ap 1.methods

#String
ap "who".methods
```



#### Data type

Numeric, String, Boolean, 

Array (num = [10,20,30])

Hash  (num = {"수학" => 40, "과학" => 50 })



#### 반복

```ruby
# num 자체가 객체로서 사용된다.
5.times do
  puts "안녕?"
end


for i in[1,2,3]
  puts i
end
array = ["20층","고기","밥"]
for asdf in array
  puts asdf
end

[1,2,3].each do |x|
  puts x
end
```



### 예제 소스



#### 미세먼지 API 사용

```ruby
require 'httparty'
url = URI.encode("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=강남구&dataTerm=MONTH&numOfRows=100&ServiceKey=")+key
response = HTTParty.get(url)
dust = response['response']['body']['items']['item'][0]['pm10Value']
puts dust
#class 확인
puts dust.class
# String to Integer
puts dust.to_i
puts dust.to_i.class

if(dust > "150")
  puts "매우나쁨"
elsif(dust  > "80")
  puts "나쁨"
elsif(dust > "30")
  puts "보통"
else
  puts "좋음"
end

#가지고 있는 method 확인
puts dust.methods
```



##### 로또

```ruby
puts [*1..45].sample(6).sort!
# output sample 6 number
```



코스피 지수 가져오기 (네이버 사이트)

```ruby
# 원하는 것 : zam이라고 명명
require 'httparty'
require 'nokogiri'
# 1. 원하는 정보가 있는 주소 접근
url = 'http://finance.naver.com/sise/'

# 2. 요청을 보내고 응답을 저장 (by httparty)
# 모듈 사용
res = HTTParty.get(url)
# 받은 정보의 결과 파악
#puts res.force_encoding('ISO-8859-1').encode('UTF-8')

# 3. 조작하기 편하게 바꾸기
# 코드 예쁘게 바꾸기
data = Nokogiri::HTML(res.body)

# 4. 원하는 코스피 정보만 뽑아서 출력
# 내부에 selector 정보가 들어간다,
# F12 - 코스피에서 우측 버튼 - 검사 -%tio copy selector
# Html은 #을 통해 ID를 체크
customData = data.css("#KOSPI_now")
puts customData # <span id="KOSPI_now" class="num ">2,453.76</span>
puts customData.text # 2,453.76
```

```ruby
# 클래스로 접근
class Integer
    def dollar
        self*1100
    end
end
# :dollar

3.methods
# method의 dollar가 생긴다

3.dollar
# 3300
```

