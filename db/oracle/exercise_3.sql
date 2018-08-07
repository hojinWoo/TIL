select * from BONUS;
select * from EMP;
select * from salgrade;
select * from dept;
-- 1. DALLAS에서 근무하는 사원의 이름, 직위, 부서번호, 부서이름을 출력하시오.(5개)
--이름         직위        부서이름          
---------- --------- --------------
--SMITH      CLERK     RESEARCH      
--JONES      MANAGER   RESEARCH      
--SCOTT      ANALYST   RESEARCH      
--ADAMS      CLERK     RESEARCH      
--FORD       ANALYST   RESEARCH      
select e.Ename as 이름,e.job as 직위, e.deptno as 부서번호, d.DName as 부서이름
from emp e join dept d
on e.deptno=d.deptno
where loc= 'DALLAS';


-- 2. 이름에 'A'가 들어가는 사원들의 이름과 부서이름을 출력하시오.(7개)
--이름         부서이름          
---------- --------------
--ALLEN      SALES         
--WARD       SALES         
--MARTIN     SALES    
select Ename as 이름, Dname as 부서이름
from emp e join dept d
on e.deptno=d.deptno
where Ename like '%A%';


-- 3. 사원이름과 그 사원이 속한 부서의 부서명, 그리고 월급을 
--출력하는데 월급이 3000이상인 사원을 출력하시오. (3개)
--사원이름       부서명                    월급
---------- -------------- ----------
--KING       ACCOUNTING           5000
--FORD       RESEARCH             3000
--SCOTT      RESEARCH             3000
select Ename as 사원이름, Dname as 부서명,sal as 월급
from emp e join dept d
on e.deptno=d.deptno
where sal>=3000;

-- 4. 직위가 'SALESMAN'인 사원들의 직위와 그 사원이름, 그리고
-- 그 사원이 속한 부서 이름을 출력하시오. (4개)
--직위        사원이름       부서이름          
--------- ---------- --------------
--SALESMAN  TURNER     SALES         
--SALESMAN  MARTIN     SALES         
--SALESMAN  WARD       SALES         
--SALESMAN  ALLEN      SALES       
select job as 직위, Ename as 사원이름, Dname as 부서명
from emp e join dept d
on e.deptno=d.deptno
where job='SALESMAN';

-- 5. 커미션이 책정된 사원들의 사원번호, 이름, 연봉, 연봉+커미션,
-- 급여등급을 출력하되, 각각의 컬럼명을 '사원번호', '사원이름',
-- '연봉','실급여', '급여등급'으로 하여 출력하시오. (6개)
--  사원번호 사원이름               연봉        실급여       급여등급
---------- ---------- ---------- ---------- ----------
--  7521 WARD            15000      15200          2
--  7654 MARTIN          15000      15300          2
--  7844 TURNER          18000      18000          3
select Empno as 사원번호 , Ename as 사원이름 , sal*12 as 연봉, (sal)*12+comm as 실급여,grade as 급여등급
from emp , salgrade 
where sal  between losal and hisal
and comm is not null;


-- 6. 부서번호가 10번인 사원들의 부서번호, 부서이름, 사원이름,
-- 월급, 급여등급을 출력하시오. (3개)
--   부서번호 부서이름           사원이름               월급       급여등급
---------- -------------- ---------- ---------- ----------
--      10 ACCOUNTING     CLARK            2450          4
--      10 ACCOUNTING     KING             5000          5
--      10 ACCOUNTING     MILLER           1300          2 
select Deptno as 부서번호,Dname as 부서이름 ,Ename as 사원이름,sal as 월급, grade as 급여등급
from emp e join dept d 
using (deptno), salgrade
where Deptno=10 and sal between losal and hisal;

-- 7. 부서번호가 10번, 20번인 사원들의 부서번호, 부서이름, 
-- 사원이름, 월급, 급여등급을 출력하시오. 그리고 그 출력된 
-- 결과물을 부서번호가 낮은 순으로, 월급이 높은 순으로 정렬하시오. (8개)
--   부서번호 부서이름           사원이름               월급       급여등급
---------- -------------- ---------- ---------- ----------
--    10 ACCOUNTING     KING             5000          5
--    10 ACCOUNTING     CLARK            2450          4
select Deptno as 부서번호,Dname as 부서이름 ,Ename as 사원이름,sal as 월급, grade as 급여등급
from emp e join dept d 
using (deptno),salgrade
where (Deptno=10 or Deptno=20) and sal between losal and hisal
order by Deptno asc , sal desc;

-- 8. 사원들의 이름, 부서번호, 부서이름을 출력하시오. (15개) 
-- 단, 직원이 없는 부서도 출력하며 이경우 이름을 '미정'이라고
-- 출력한다.        
--이름               부서번호 부서이름          
---------- ---------- --------------
--SMITH              20 RESEARCH      
--ALLEN              30 SALES         
--WARD               30 SALES         
--JONES              20 RESEARCH      
select nvl(Ename,'미정') as "사원들의 이름" ,Deptno as 부서번호,Dname as 부서이름
from emp e right join dept d 
using (deptno);

-- 9. 사원들의 이름, 부서번호, 부서이름을 출력하시오. (15개) 
-- 단, 아직 부서 배치를 못받은 직원도  출력하며 이경우 부서번호는  0 으로
-- 출력한다.        
--이름               부서번호 부서이름          
---------- ---------- --------------
--SMITH              20 RESEARCH      
--ALLEN              30 SALES         
--WARD               30 SALES         
--JONES              20 RESEARCH      
select Ename as 이름 ,Dname as 부서이름,nvl(Deptno,'0') as 부서번호
from emp e left join dept d 
using (deptno);

-- 10. 사원들의 이름, 부서번호, 부서이름을 출력하시오. (16개) 
-- 단, 직원이 없는 부서도 출력하며 이경우 이름과 부서번호를 '미정'이라고
-- 출력한다. 아직 부서 배치를 못받은 직원도  출력하며 부서 번호와 부서 이름을
-- '미정' 이라고 출력한다.     
--이름               부서번호 부서이름          
---------- ---------- --------------
--SMITH              20 RESEARCH      
--ALLEN              30 SALES         
--WARD               30 SALES         
--JONES              20 RESEARCH    
select  nvl(Ename,'미정') as 이름 ,nvl(Dname,'미정')as 부서이름 , nvl(to_char(Deptno),'미정') as 부서번호 
from emp e full join dept d
using (deptno);

--11. 보너스를 받는 모든 직원의 이름, 보너스, 부서이름, 위치를 조회하는 질의를 작성하시오.

--ENAME           COMM        DNAME         	LOC
----------------------------------------------------
--KING		3500	ACCOUNTING	NEW YORK
--JONES		30	RESEARCH	DALLAS
--TURNER	0	SALES		CHICAGO
--MARTIN	300	SALES		CHICAGO
--WARD		200	SALES		CHICAGO
--ALLEN		300	SALES		CHICAGO         
select Ename , comm as 보너스, Dname as 부서이름 ,loc as 위치
from emp e join dept d
using (deptno)
where comm is not null;

-- 12. DALLAS에서 근무하는 사원의 이름,  월급, 등급을 출력하시오.(5개)
--이름         월급        등급          
---------- --------- --------------
--SMITH      800         1      
--JONES      2975       4   
--SCOTT            
--ADAMS           
--FORD             
select Ename as 이름,sal as 월급 ,grade as 등급
from emp e join dept d using (deptno),salgrade
where loc='DALLAS' and sal between losal and hisal;

--13. 사원번호와 사원이름, 그리고 그 사원을 관리하는 관리자의 
-- 사원번호와 사원이름을 출력하되 각각의 컬럼명을 '사원번호',
-- '사원이름', '관리자번호', '관리자이름'으로 하여 출력하시오. (15개)
      관리자가 없으면 '없음'을 대신 출력한다.
--    사원번호 사원이름            관리자번호 관리자이름     
---------- ---------- ---------- ----------
--  7902 FORD             7566 JONES     
--  7788 SCOTT            7566 JONES     
--   7900 JAMES            7698 BLAKE   
select e.Empno as 사원번호, e.Ename as 사원이름
, nvl(to_char(m.Mgr),'없음') as 관리자번호,nvl( m.Ename,'없음') as 관리자이름
from emp e  left outer join emp m
on e.Mgr=m.Empno;
