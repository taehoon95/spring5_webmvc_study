select * from member;

delete from `member` where ID < 67;

select ID,EMAIL,PASSWORD,NAME,REGDATE from member;

select ID,EMAIL,PASSWORD,NAME,REGDATE from member WHERE email = "3@3";

select count(*) from member;

select email, PASSWORD,name,REGDATE from member where email = "test@test.co.kr";

select *
  from `member` 
where REGDATE between '2021-05-17' and '2021-05-25'
order by REGDATE desc;