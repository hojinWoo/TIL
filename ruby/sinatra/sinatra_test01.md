## Sinatra

시나트라 intro (by korean)

http://sinatrarb.com/intro-ko.html



### Sinatra Setting

```bash
$ mkdir sinatra-test
$ cd sinatra-test/
$ touch app.rb
$ gem install sinatra

# IP binding(외부 접속 가능)
$ ruby app.rb -0 $IP

$ $IP
$ $PORT

$ mkdir views && touch views/htmlfile.html
# imbeded ruby file 
# ruby 전용 html, MVC에서의 view 담당, jquery와 비슷
$ touch views/erbfile.erb

# cf. 시나트라에서는 변경사항 확인하려면 서버 죽였다가 다시 켜야 한다 (reload 해주는 것 따로 있다)
```



### Intro

```ruby
#app.rb
# routing + controller
require 'sinatra'

# get
get '/' do
  'Hello world! welcome'
end
#return이 생략되어 있다^^
```



### Sinatra :: Reloader

시나트라를 사용할 때 코드 수정해도 서버를 off/on하지 않아도 자동 적용되도록 해 준다.

`gem install sinatra-contrib`

http://sinatrarb.com/contrib/reloader

```ruby
require 'sinatra'
require 'sinatra/reloader'
```



### 라우터(Routes)

```ruby
# 실제로는 html로 하기 때문에 html파일로 띄우면 된다.
get '/htmlfile' do
  send_file 'views/htmlfile.html'
end

# possible to html but not use
get '/htmltag' do
    '<h1>possible to send html tag</h1>
    <ul>
        <li>1</li>
        <li>22</li>
        <li>333</li>
    </ul>'
end

# get parameter (string) use double quote
# params로 hash가 자동생성된다
get '/welcome/:name' do
    "hello #{params[:name]}"
end

# URL을 통해 들어오는 것은 무조건 String 
get '/cubeInt/:num' do
    "result is #{params[:num].to_i ** 3}" 
end


get '/erbfile' do
	@name = "hojin"
    erb :erbfile
end

# <ex1>
#배열을 만들어서 sample로 1개를 이름과 url을 넘겨서 erb를 랜더링한다.
get '/dinner-array' do
    @dinner = ["김밥","샌드위치","제육","라면"].sample
    erb :dinnerArray
end

# <ex2>
#key : munu name, value : image url
#hash can't sampling
get '/dinner-hash' do
    arr = ["김밥","샌드위치","제육","라면"]
    dinnerHash = {
        "김밥" => "http://recipe.ezmember.co.kr/cache/recipe/2016/11/02/91ec3f6b07a3ebd7d282ccc57d7396b31.jpg", 
        "샌드위치" => "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/BLT_sandwich_on_toast.jpg/220px-BLT_sandwich_on_toast.jpg",
        "제육" => "http://recipe.ezmember.co.kr/cache/recipe/2018/01/16/466b988c598541e0887051925d9c71841.jpg", 
        "라면" => "http://img.vogue.co.kr/vogue/2018/01/style_5a55dc28c936c.jpg"}
    @dinnersample = arr.sample
    @dinnerImg = dinnerHash[@dinnersample]
    erb :dinnerHash
end
```




