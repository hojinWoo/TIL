--1
select *
from tab;
--2
desc bonus
desc DEPT
desc EMP
desc SALGRADE
--3
select *
from EMP;
--4
select Empno,Ename,sal
from EMP;
--5
select distinct sal
from Emp;
--6
select Ename as 이름,sal as 월급
from EMP;
--7
select Ename ,sal ,sal+comm as 실급여
from EMP
where sal+comm is not null;
--8
select Empno,Ename,sal
from EMP
where ename='SCOTT';
--9
select Empno,Ename,job
from EMP
where job='SALESMAN';
--10
select Empno,Ename,Sal
from EMP
where Empno='7499'or Empno='7521' or Empno='7654';
--11
select Empno,Ename,sal
select Emp
where 1500<=sal or sal<=3000;
--12
select Ename
from EMp
where DepTno is null;
--13
select *
from Emp
order by sal desc;
