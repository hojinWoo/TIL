# Sinatra 02

- `gem install rest-client`

  rest-client로 URL을 요청

  gem 설치 이후 or 라우팅을 바꾼 경우에는 무조건 서버를 다시 시작해야 한다.

  ```ruby
  require 'rest-client'
  RestClient.get(url, headers{})
  RestClient.post(url, payload, headers={})
  ```


- ruby는 JSON을 지원하지 않기 때문에 Hash로 변경해야 한다.

  ```ruby
  require 'json'	
  JSON.parse()
  ```



### 로또 게임 

```ruby
get '/lotto-sample' do
   @lotto = (1..45).to_a.sample(6).sort

   url = "http://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=809"
   @lotto_info = RestClient.get(url)

   # JSON parsing to Hash
   @lotto_hash = JSON.parse(@lotto_info)

   # create array
   @lotto_1=[]
   @lotto_hash.each do |k,v|
       @lotto_1.push(v) if k[0..5]=="drwtNo"
       #@lotto_1 << v if k.include?('drwtNo')
   end

   #몇 개 일치하는 지 찾기
   #@n=0
   #@lotto.each do |x|
      # @n += 1 if @lotto_1.include?(x)
   #end
   # array 같은 것 찾기 더 짧게...
   @n = (@lotto&@lotto_1).length
 
   #if 문으로도 할 수 있음.
   @bonusnum =@lotto_hash["bnusNo"] 
    case @n 
        when 3..6
            @matchnum = 8 - @n - (@n==5 && @lotto.include?(@bonusnum) ? 1:0)
            #@matchnum = 8 - @n - (@lotto.include?(@bonusnum) ? 1/@n:0)
        when 6
            @matchnum = 1
        else
            @matchnum = "꽝"
    end    
    
   # case문에 2가지로 조건을 줄 수도 있다.
   @result = 
   case [@n, @lotto.include?(@bonusnum)]
    when [6,false]
       then "1등" 
    end
   erb :lotto
end
```