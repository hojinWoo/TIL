# AJAX(Asynchronous JavaScript and XML )

비동기식 웹 프로그래밍을 위한 프래임워크



## Rails 에서 aJax 구현하기

### 글 작성

1. data-remote = true (html 태그 기준)

```html
<form action="/posts/<%= @post.id%>/comments" method="post" data-remote=true>
  <input type="text" name="content" /><br />
  <input type="hidden" name="authenticity_token" value="<%= form_authenticity_token %>">
  <input type="submit" />
</form>
```

2. CommentsController => create action

```
  def create
    @comment = Post.find(params[:post_id]).comments.new(comment_params)
    @comment.user_id = current_user.id
    if @comment.save
      respond_to do |format| #요청에 따라 html.erb 혹은 js.erb 파일을 리턴
        format.html {redirect_to :back}
        format.js {render 'create_temp'}
      end
    else
      redirect_to :back
    end
  end
```

3. create.js.erb 작성 (escape_javascript의 약어는 j)

```
var a = "<p><%= j(@comment.content) %>"
var b = "<%= j(link_to '댓글 삭제하기', destroy_comment_path(@comment.id), method: :delete, remote: true, class: 'delete_comment') %>"
var c = "</p><hr />"
$("div#comments").append(a+b+c);
```

4. show.html.erb파일에 ajax 결과에 따른 event handler 작성

```
//https://github.com/rails/jquery-ujs/wiki/ajax 에서 이벤트명 참고
<script>
  $('form').on('ajax:success', function() {
    $('input[name="content"]').val('');
  });
</script>
```



### 글 삭제

1. remote: true (rails코드 기준)

```
<%= link_to '댓글 삭제하기', destroy_comment_path(comment.id), remote: true, method: :delete, class:"delete_comment" %>
```

2.  CommentsController => destroy action

```
  def destroy
    @comment = Comment.find(params[:comment_id])
    @comment.destroy
    respond_to do |format|
      format.html {redirect_to :back}
      format.js {}
    end
  end
```

3. destroy.js.erb 작성

```
var parent = $('a[href="/comments/<%= params[:comment_id] %>"]').parent(); //p tag
var hr = parent.next();
parent.remove();
hr.remove();
```



## jQuery로 aJax 구현

### 글 작성

1. script 생성

```
<script>
$('input[type="submit"]').on('click', function(e) {
    e.preventDefault();
    alert('start!');
    $.ajax({
      url: $('form').attr('action'),
      type: "POST",
      data: {content: $('input[name="content"]').val(),
            authenticity_token: $('[name="csrf-token"]').attr('content')},
      dataType: 'script',
      success: function(){
        alert('success')
        $('input[name="content"]').val('');
      },
      error: function(){
        alert('fail!');
      }
    });
  });
</script>
```

2. 이후 rails의 ajax 구현 순서(2, 3, 4)와 동일.



### 글 삭제

1. script 생성

```
<script>
$('.delete_comment').on('click', function(e) {
    e.preventDefault();
    alert("start_delete");
    $.ajax({
      url: this.href,
      type: "DELETE",
      data: {authenticity_token: $('[name="csrf-token"]').attr('content')},
      dataType: "script",
      success: function() {
        alert("delete_complete!");
      },
      error: function() {
        alert("delete_error!");
      }
    });
  });
</script>
```

2. 이후 rails의 ajax 구현 순서(2, 3)와 동일