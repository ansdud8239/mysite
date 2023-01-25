-- user
desc user;
select * from user;		

-- join
insert into user values(null,'조문영','ansdud8239@naver.com',password('1234'),'female',now());

-- login
select * from user where email='ansdud8239@naver.com' and password = password('1234');

-- update
update user set name='조문영',email='moonyoung@naver.com',password=password('12345'),gender='female' where no='1';
