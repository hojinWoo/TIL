# [Telegram API](https://core.telegram.org/)

### API token이 필요

- `botfather`를 통해 챗봇 토큰을 받을 수 있다.

- URL : https://api.telegram.org/bot#{token}/getUpdates

  ```json
  //token 자리에 받은 token을 넣으면 된다.
  {
    "ok": true,
    "result": [
      
    ]
  }
  ```

  

#### [telegram keyboard ruby](https://github.com/atipugin/telegram-bot-ruby)

```ruby
require 'telegram/bot'

token = 'YOUR_TELEGRAM_BOT_API_TOKEN'

Telegram::Bot::Client.run(token) do |bot|
  bot.listen do |message|
    case message.text
    when '/start'
      bot.api.send_message(chat_id: message.chat.id, text: "Hello, #{message.from.first_name}")
    when '/stop'
      bot.api.send_message(chat_id: message.chat.id, text: "Bye, #{message.from.first_name}")
    when '/manager'
      bot.api.send_message(chat_id: message.chat.id)
    end
  end
end
```



#### [heroku scheduler](https://elements.heroku.com/addons/scheduler) 설치

- heroku에서 [Resources] - [Find more add-ons]로 찾을 수 있다.
- cf. zip code : ks014



```ruby
# 텔레그램 챗봇
# must be private for security
# input your token received by 'botfather'
token = 'YOUR_TELEGRAM_BOT_API_TOKEN'

require 'rest-client'
require 'json'
#json data 요청된 것 받아오기
url = "#{token}"
id_url = RestClient.get "https://api.telegram.org/bot"+url+"/getUpdates" , {accept: :json}
#puts id_url

id_json = JSON.parse(id_url) #encoding을 ruby에서 설정할 수 있도록 해줌
#puts id_json

#user_id = id_json["result"][0]["message"]["from"]["id"]
user_id = 608620602
puts user_id.class
msg = "안녕 짜식아 재미있냐?"
msg_url = URI.encode("https://api.telegram.org/bot"+url+"/sendmessage?chat_id=#{user_id}&text=#{msg}")
#reqeust url
RestClient.get msg_url
```

