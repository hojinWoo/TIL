### 구조

|         [Local]         |           |                       |              |        |            | [Github] |
| :---------------------: | :-------: | :-------------------: | :----------: | :----: | ---------- | -------- |
| [WD(Working Directory)] |     →     | [Staging Area(index)] |      →       | [HEAD] | →          |          |
|                         | $ git add |                       | $ git commit |        | $ git push |          |



#### git site

- [git scm](https://git-scm.com/)
- [git 배우기](https://backlog.com/git-tutorial/kr/)



```bash
$ mkdir placeA
$ cd init

$ git init
#(master)
$ vi a.txt
$ vi b.txt

$ git status
# NO commit yet
#	a.txt
#	b.txt
$ git add a.txt
$ git status

$ git commit -m "add a.txt"

# 원격 저장소
$ git remote add origin YOUR_GITHUB_SITE

$ git remote
#origin

$ git push origin master

$ cd ..
$ mkdir placeB
$ cd placeB
$ git clone CLONE_SITE

$ git HEAD
# 
$ git diff (-p)
# 가장 최신의 commit 상태와 WD에 있는 파일의 상태를 서로 비교해서 알려준다

$ git commit --amend
# 가장 최근의 commit message 수정

$ git checkout HEAD~1    # 하나 직전 commit으로 돌아감
$ git checkout HASH_CODE # 해당 해시코드를 가진 commit 상태로 감
$ git checkout master    # master 상태로 다시 갈 수 있음

# master에서 merge 하기 전에 stash
$ git stash -all #stash에 모든 것 올려놓기(커밋하지 않고 임시보관)
$ git stash pop  #stash 내용을 apply+drop

```



- 오류 나는 경우

  - 가장 오류 많이 나는 경우가 같은 line에 code가 다르게 있기 때문에 auto merge가 되지 않는다.

    `git pull origin master` 

  - 윈도우에서 commit 할 때 오류 나는 것 중 `^m` 이 있는데 encoding 문제..

    > windows
    >
    > `git config --global core.autocrlf true`
    >
    > vagrant(server)
    >
    > `git config --global core.autocrlf input`




- 강제로 commit 하기

  `git push -f origin master`

  > 대신 다른 곳에서 했던 것 중 부딪친 것은 강제적으로 지운다..
  >
  > (복구는 지워진 것을 갖고 있는 local에서만 가능한데 최대한 안 쓰는 것이 좋다.)



```bash
$ git log
#입력 했을 시 'HEAD -> master'와 'origin/master'
```

### git 유용한 command

```bash
#git의 내장 GUI
$ gitk

# 콘솔에서 git output을 컬러로 출력하기
$ git config color.ui true

# log에서 확정본 1개를 딱 한줄로만 표시하기
$ git config format.pretty oneline

# 파일을 추가할 대 대화식으로 추가하기
$ git add -i
```



### git branch 전략

```bash
$ git branch dev
$ git branch # 해당 branch
# dev
# * master

$ git checkout dev # dev로 이동
# 파일 생성~~~
$ git add .
$ git commit -m ""

$ git checkout master # master로 이동

# merge하기
$ git merge dev

$ git checkout -b "new" # new branch 만들면서 이동
$ git branch -d "new"   # master에서 new branch 삭제

$ git branch -v  # 각 branch에서 최근 commit을 볼 수 있다.
```

### Rebase

**이미 공개 저장소에 Push 한 커밋을 Rebase 하지 마라** 

```bash
$ git rebase master #
```

