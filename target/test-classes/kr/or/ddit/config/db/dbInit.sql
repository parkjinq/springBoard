--운영 DB에는 없는 테이블
SELECT * FROM notexistsinprd;

--jspuser data 초기화
DELETE FROM attachments;
DELETE FROM comments;
DELETE FROM posts;
DELETE FROM board;
DELETE FROM jspuser;

INSERT INTO jspuser VALUES ('brown','브라운','brownpass','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/08/08','brown@gmail.con','123123123','D:\A_TeachingMaterial\6.JspSrpgin\upload\ddit.png');
INSERT INTO jspuser VALUES ('sally','샐리','sallypass','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/04/27','sally@gmail.con','123123123','/profile/sally.png');
INSERT INTO jspuser VALUES ('cony','코니','conypass','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/08/08','cony@gmail.con','123123123','/profile/cony.png');
INSERT INTO jspuser VALUES ('moon','문','moonpass','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/10/06','moon@gmail.con','123123123','/profile/moon.png');
INSERT INTO jspuser VALUES ('james','제임스','jamespass','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/02/14','james@gmail.con','123123123','/profile/james.png');
INSERT INTO jspuser VALUES ('parkjinq','박진','java','대전 서구 청사로 5','하나로아파트 105동 903호','35210','91/12/23','parkjinq@gmail.com','010-4548-0041','profile/스튜디오+증명-24584(선)_12_명_백_반-1.jpg');

--월단위 달력 생성 데이터 초기화
delete from calendar where ymd like '201812%';

--board 테이블 초기화(delete > insert)

INSERT INTO board VALUES ('bd001', '게시판1', 'Y', SYSDATE, 'parkjinq');
INSERT INTO board VALUES ('bd002', '게시판2', 'N', SYSDATE, 'parkjinq');
INSERT INTO board VALUES ('bd003', '게시판3', 'Y', SYSDATE, 'brown');

--posts 테이블 초기화(delete > insert)

INSERT INTO posts VALUES ('ps001', 'bd001', SYSDATE, '게시글1', '게시글내용', null, 'parkjinq', '1', 'N');
INSERT INTO posts VALUES ('ps002', 'bd001', SYSDATE, '게시글2', '게시글내용', null, 'brown', '2', 'N');
INSERT INTO posts VALUES ('ps003', 'bd001', SYSDATE, '게시글3', '게시글내용', null, 'parkjinq', '3', 'N');
INSERT INTO posts VALUES ('ps004', 'bd002', SYSDATE, '게시글4', '게시글내용', null, 'brown', '4', 'N');
INSERT INTO posts VALUES ('ps005', 'bd001', SYSDATE, '게시글5', '게시글내용', 'ps001', 'parkjinq', null, 'N');
INSERT INTO posts VALUES ('ps006', 'bd001', SYSDATE, '게시글6', '게시글내용', 'ps001', 'brown', null, 'N');

--attachments 테이블 초기화(delete > insert)

INSERT INTO attachments VALUES ('att001', 'ps001', 'a-t-t-a-c-h-m-e-n-t-s-1.jpg', 'attachments1.jpg');
INSERT INTO attachments VALUES ('att002', 'ps001', 'a-t-t-a-c-h-m-e-n-t-s-2.jpg', 'attachments2.jpg');
INSERT INTO attachments VALUES ('att003', 'ps001', 'a-t-t-a-c-h-m-e-n-t-s-3.jpg', 'attachments3.jpg');

--comments 테이블 초기화(delete > insert)

INSERT INTO comments VALUES ('cm001', 'ps001', '댓글내용1', SYSDATE, 'parkjinq', 'N');
INSERT INTO comments VALUES ('cm002', 'ps001', '댓글내용2', SYSDATE, 'brown', 'N');
INSERT INTO comments VALUES ('cm003', 'ps001', '댓글내용3', SYSDATE, 'sally', 'Y');
INSERT INTO comments VALUES ('cm004', 'ps001', '댓글내용4', SYSDATE, 'james', 'N');
