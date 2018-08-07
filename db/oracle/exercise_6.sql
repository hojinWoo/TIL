--QUESTION
select * from BONUS;
select * from EMP;
select * from salgrade;
select * from dept;
--1. 'MARRY'보다 월급을 많이 받는 사원들의 이름과 월급을 출력하시오.(12개)
--ENAME             SAL
---------- ----------
--ALLEN            1600
--WARD             1250
select Ename,sal
from Emp
where sal>(select sal
           from Emp
           Where Ename='MARRY');

--2. 10번 부서의 사원들과 같은 월급을 받는 사원들의 이름, 월급, (3개)
--   부서번호를 출력하시오.
--ENAME             SAL     DEPTNO
------------ ---------- ----------
--CLARK            2450         10
--KING             5000         10
--MILLER           1300         10
select Ename,sal,Deptno
from EMp
where sal in (select sal
              from Emp
              where Deptno=10);

--3. 'BLAKE'와 같은 부서에 있는 사원들의 이름과 고용일을 뽑는데 'BLAKE'는 빼고 출력하시오. (5개)
--ENAME      HIREDATE
---------- --------
--JAMES      81/10/03
--TURNER     81/09/08
select Ename,Hiredate
from emp
where Ename != 'BLAKE' and Deptno in (select Deptno
              from Emp
              where Ename='BLAKE' );

--4. 평균급여보다 많은 급여를 받는 사원들의 사원번호, 이름, 월급을
-- 출력하되, 월급이 높은 사람 순으로 출력하시오.(6개)
--  EMPNO ENAME             SAL
---------- ---------- ----------
--   7839 KING             5000
--   7902 FORD             3000
select Empno,Ename,sal
from Emp
where sal > (select avg(sal)
                  from emp)
                  order by sal desc;
                  
                   

--5. 이름에 'T'를 포함하고 있는 사원들과 같은 부서에서 근무하고
--   있는 사원의 사원번호와 이름을 출력하시오.(11개)
--     EMPNO ENAME     
---------- ----------
--      7902 FORD      
--      7876 ADAMS     
--      7788 SCOTT   
select Empno,Ename
from Emp
where Deptno in (select Deptno
                 from emp
                 where Ename like '%T%');

--6 자신의 급여가 평균급여보다 많고,이름에 S자가 들어가는 사원과 동일한
--  부서에서 근무하는 모든 사원의 사원번호,이름 및 급여를 출력하시오(4개)
--     EMPNO    ENAME      SAL
--    --------  --------  -------
--      7902     FORD       3000
--      7788     SCOTT      3000
--      7566     JONES      2975
--      7698     BLAKE     2850
select Empno,Ename,sal
from emp
where Deptno in (select Deptno
                 from emp
                 where Ename like '%S%' and 
                 where sal>avg(sal))
             
            



--7. 30번 부서에 있는 사원들 중에서 가장 많은 월급을 받는 사원보다
--   많은 월급을 받는 사원들의 이름, 부서번호, 월급을 출력하시오. (4개)
--   (단, ALL 또는 ANY 연산자를 사용할 것)
--  이름    부서번호   월급
--------------------------------
--  JONES     20     2975
--  SCOTT     20     3000
--  KING       10     5000
--  FORD      20     3000
select Ename as 이름, Deptno as 부서번호, sal as 월급
from emp
where sal > all (select sal
                 from emp
                 where Deptno=30);

--8. 'DALLAS'에서 근무하고 있는 사원과 같은 부서에서 일하는 사원의
--   이름, 부서번호, 직업을 출력하시오.(5개)
--ENAME          DEPTNO JOB      
---------- ---------- ---------
--FORD               20 ANALYST  
--ADAMS              20 CLERK    
--SCOTT              20 ANALYST  
--JONES              20 MANAGER  
--SMITH              20 CLERK 
select Ename, Deptno, job
from emp e join dept d
using (Deptno)
where Deptno in (select Deptno
                 from emp
                 where loc ='DALLAS');

--9. SALES 부서에서 일하는 사원들의 부서번호, 이름, 직업을 출력하시오.(6개)
--    DEPTNO ENAME      JOB      
---------- ---------- ---------
--    30 JAMES      CLERK    
--    30 TURNER     SALESMAN 
--    30 BLAKE      MANAGER  
select Deptno,Ename,job
from emp e join dept d
using (deptno)
where deptno in (select deptno
                 from emp
                 where Dname='SALES');

--10. 'KING'에게 보고하는 모든 사원의 이름과 급여를 출력하시오. (3개)
--     (KING에게 보고하는 사원이란 mgr이 KING인 사원을 의미함) 
--ENAME             SAL
---------- ----------
--CLARK            2450
--BLAKE            2850
--JONES            2975
select Ename,sal
from emp
where Ename in (select Ename
                 from emp
                 where Mgr = 7839);

--11. 커미션을 받는 사원과 / 부서번호, 월급이 같은 사원의
--    이름, 월급, 부서번호를 출력하시오. (6개)
--	ALLEN	1600	30
--	MARTIN	1250	30
--	WARD	1250	30
--	JONES	2975	20
--	KING	5000	10
--	TURNER	1500	30
select Ename,sal,Deptno
from emp
where Deptno in (select Deptno
                      from emp
                      where comm is not null)
                      and sal in (select sal
                                  from emp
                                  where comm is not null);

--12. 30번 부서 사원들의 월급과 커미션이 같지 않은
--    사원들의 이름, 월급, 커미션을 출력하시오.(30번 부서 제외) (2개)
--  ENAME      SAL    COMM
---------- ---------- ----------
--	JONES	2975	30
--	KING	5000	3500
select Ename,sal,comm
from emp
where sal not in (select sal
              from emp
              where Deptno=30) and
              comm in (select comm
                     from emp
                     where Deptno!=30);
              

***************************************************************
--4. 부서별 최대 월급을 "부서명", "최대월급" 으로 출력하시오.(최대월급이 큰 순)
--6.부서별 평균월급을 출력하되, 평균월급가 2000보다
-- 큰 부서의 부서 이름과 평균월급을 출력하시오.
--9. 커미션이 정해진 직원들 중에서 부서별 최대월급을 출력하시오. 
--최대 월급이 높은 순으로 정렬하여 출력하시오.

위 문제들은 어제 진행한 실습입니다. emp_dept 라는 이름의 뷰를 만들어서
구현하는 코드로 변경해 봅니다. 뷰의 명칭은 정해진 것이고 뷰를 만들때 어떤 컬럼들을
선택하여 생성할 것인지는 임의로 정합니다.

13. 뷰생성 SQL
create view emp_dept(Dname,sal,comm)
as
select Dname,sal,comm
from emp e join dept d
using (deptno);

14. 위의 4번 재작성
select Dname as 부서명, max(sal) as 최대월급
from emp_dept
group by Dname;
15. 위의 6번 재작성
select Dname as 부서이름 ,avg(sal) as 평균월급
from emp_dept
group by Dname
Having avg(sal)>2000;
16. 위의 9번 재작성 
select max(sal) as 최대월급
from emp_dept
where comm is not null
group by Dname;