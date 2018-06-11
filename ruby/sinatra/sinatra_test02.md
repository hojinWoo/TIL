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
   #    @n += 1 if @lotto_1.include?(x)
   #end   
    
   # 여러 개의 array에서 같은 것 찾기 더 짧게...
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


### Get Parameter

```ruby
#아래에 있는 method들을 실행하기 전에 먼저 실행할 것
before do
    p "****"
    p params
    p "****"
end


#form page에서 keyword를 통해 search page로 값을 보냄.
get '/form' do
    erb :form
end

get '/search' do
    @keyword = params[:keyword]
    url = "https://search.naver.com/search.naver?query="
    erb :search
    
    # redirect to : sinatra에서 지원 url을 다시 재요청 해준다
    # 한글 encoding을 따로 해줘야 한글 가능
    redirect to (url + @keyword)
end
```



### Exercise (LoL opgg) Crawling web page

`gem install httparty nokogiri`

```ruby
# 필요한 것 추가
require 'httparty'
require 'nokogiri'
# ruby 내부 모듈
require 'uri'
require 'date'
require 'csv'

### exercise
get '/opgg' do
    "http://www.op.gg/summoner/userName="
    erb :opgg
end

get '/opggresult' do
    name = params[:userName]
    url = "http://www.op.gg/summoner/userName="
    
    #encoding
    @encodeName = URI.encode(name)
    
    #실제 HTML문서를 가져오는 방법
    @res = HTTParty.get(url + @encodeName)
    @doc = Nokogiri::HTML(@res.body)
    
### HTML문서 탐색 방법 - Tree구조
    # F12 - copy selector 
    # # : id, . : class
    @win = @doc.css("#SummonerLayoutContent > div.tabItem.Content.SummonerLayoutContent.summonerLayout-summary > div.SideContent > div.TierBox.Box > div.SummonerRatingMedium > div.TierRankInfo > div.TierInfo > span.WinLose > span.wins")
    @lose = @doc.css("#SummonerLayoutContent > div.tabItem.Content.SummonerLayoutContent.summonerLayout-summary > div.SideContent > div.TierBox.Box > div.SummonerRatingMedium > div.TierRankInfo > div.TierInfo > span.WinLose > span.losses")
    @rank = @doc.css("#SummonerLayoutContent > div.tabItem.Content.SummonerLayoutContent.summonerLayout-summary > div.SideContent > div.TierBox.Box > div.SummonerRatingMedium > div.TierRankInfo > div.TierRank > span")
    
### 파일 다루기
    
    # File.open(file_name, IO option)
    File.open('opgg.txt', 'a+') do |f|
        f.write("#{@encodeName} : #{@win}, #{@lose}, #{@rank}\n")    
    end
    
    # CSV
    CSV.open('dpgg.csv','a+') do |c|
        c << [@userName, @win, @lose, @rank]
    end

    erb :opggresult
end
```



#### CSV document

`http://ruby-doc.org/stdlib-2.0.0/libdoc/csv/rdoc/CSV.html`

```ruby
get '/oplog' do
    @log = []
    CSV.foreach('dpgg.csv') do |row|
        @low << row
    end
    erb :oplog
end
```

