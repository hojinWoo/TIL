--1. 테이블명 : visitor
--    컬럼 사양 : name - 한글 기준으로 최대 6자를 저장할 수 있게
--                     writedate - 날짜를 저장할 수 있게 char(18)
--                     memo - 한글 기준으로 최대 60자를 저장할 수 있게
--
--                    [visitor 테이블에 데이터를 3개 행을 입력]
--	    날짜 데이터 :                       1개 : 현재시간,  
--                                         1개 : 2016년 12월 25일, 
--                                         1개 : 1999년 8월 11일
create table visitor(
name varchar(18),
writedate  varchar(18),
memo  varchar(180));
insert into visitor(name,writedate,memo) values('김호식이',sysdate,'후라이드한마리');
insert into visitor(name,writedate,memo) values('김두마리',to_date('2016년12월25일','yyyy"년"fmmm"월"dd"일"'),'양념반후라이드반');
insert into visitor(name,writedate,memo) values('김치킨',to_date('1999년8월11일','yyyy"년"fmmm"월"dd"일"'),'양념한마리');
commit;
select * from visitor
delete from visitor
-- 2. 테이블명 : member
--    컬럼 사양 : memId -  영문 기준으로 최대 12자를 저장할 수 있게 - primary key
--	     memPwd - 영문 기준으로 최대 12자를 저장할 수 있게 - not null
--	     memName - 한글 기준으로 최대 6자를 저장할 수 있게  - not null
--                     memPic - 이미지(바이너리 형식)를 저장할 수 있게(blob 타입)                     
--                     joindate - 가입날짜 정보를 저장할 수 있게 - 기본값 설정(현재 날짜)
create table member(
memid varchar(12) primary key,
mempwd varchar(12) not null,
memName varchar(18) not null,
mempic blob,
joindate date default sysdate);

-- 3. 테이블명 : news
--     컬럼사양 : id - 길이가 5인 숫자 타입 - primary key
--                    writer - 한글 기준으로 최대 6자를 저장할 수 있게
--                    title - 한글 기준으로 최대 40자를 저장할 수 있게
--	    content - 한글 기준으로 최대 300자를 저장할 수 있게
--                    writedate - 작성날짜 정보를 저장할 수 있게
--                    cnt - 길이가 8인 숫자 타입
create table news(
id number(5) primary key,
writer varchar(18),
title varchar(120),
content varchar(900),
writedate date,
cnt number(8));


select * from visitor;



update goodsins
set goods_classify='약품'
where goods_name='티셔츠';