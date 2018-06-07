## Sinatra

시나트라 intro (by korean)

http://sinatrarb.com/intro-ko.html



#### sinatra setting

```bash
$ mkdir sinatra-test
$ cd sinatra-test/
$ touch app.rb
$ gem install sinatra

# IP binding(외부 접속 가능)
$ ruby app.rb -0 $IP

$ $IP
$ $PORT

# 시나트라에서는 변경사항 확인하려면 서버 죽였다가 다시 켜야 한다 (reload 해주는 것 따로 있다)
```


### Test

```ruby
#app.rb
require 'sinatra'

# get
get '/' do
  'Hello world!'
end
```

