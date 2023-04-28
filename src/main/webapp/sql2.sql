create table user(
id varchar(20), --아이디
user_pass varchar(20), --비밀번호
user_name varchar(20), --이름
user_birth date, --생년월일
user_zipcode int, --우편번호
user_addr varchar(50), --주소
user_addr2 varchar(50), --상세주소
user_email varchar(50), --이메일
user_phone int, --전화번호
user_bye boolean, --탈퇴여부
primary key(id)
);

create table order(
order_num int AUTO_INCREMENT, --구매번호
id varchar(20), --아이디
order_date date, --주문일자
order_memo varchar(100), --주문상세요청
order_state varchar(20), --주문상태
order_totalmoney int, --주문총금액
primary key(order_num)
);

create table qna(
qna_num int AUTO_INCREMENT, --문의번호
id varchar(20), --아이디
qna_subject varchar(100), --문의제목
qna_content varchar(200), --문의내용
qna_date date, --문의날짜
qna_answer boolean, --답변완료여부
qna_reply varchar(200), --답변내용
primary key(qna_num)
);

create table delivery(
deli_num int AUTO_INCREMENT, --배송지순번
id varchar(20), --아이디
deli_name varchar(20), --배송지명
deli_zipcode int, --우편번호
deli_addr varchar(50), --배송지
deli_addr2 varchar(50), --배송지 상세주소
deli_username varchar(20), --수령인
deli_phone int, -- 수령인 전화번호
primary key(deli_num)
);

create table cart(
cart_num int AUTO_INCREMENT,
p_num int,
id varchar(20),
cart_qty int,
primary key(cart_num)
);


