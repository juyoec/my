CREATE DATABASE collectors DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

-- 유저 생성(Create user)
CREATE USER 'winter'@'localhost' IDENTIFIED BY 'cndnj!@#';
CREATE USER 'summer'@'localhost' IDENTIFIED BY 'ejdnj!@#';

USE mysql;


-- DB 별 권한 부여 (Grant permission for each database)
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv) VALUES('localhost','display','summer','Y','Y','Y','Y','N','N','N','N');
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv) VALUES('localhost','display','winter','Y','Y','Y','Y','Y','Y','Y','Y');


-- 마무리.(Last Step)
FLUSH PRIVILEGES;

USE collectors;

CREATE TABLE COLLECTOR_CODES (
  seq bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  code varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'user 가입력하는 코드',
  acceptYN tinyint(4) NOT NULL COMMENT '여부',
  createdAt datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
  modifiedAt datetime DEFAULT CURRENT_TIMESTAMP COMMENT '변경일시',
  PRIMARY KEY (seq),
  KEY COLLECTORS_IDX01 (code)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COLLATE=utf8_bin COMMENT='북마크를 보고싶은 사용자';

CREATE TABLE bookmark_items (
  seq bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  listSeq BIGINT(11) NOT NULL COMMENT 'listSeq',
  url varchar(5000) COLLATE utf8_bin NOT NULL COMMENT 'lin url',
  dateHourIndex INT NOT NULL,
  description varchar(5000) COLLATE utf8_bin NOT NULL COMMENT 'description',
  createdAt datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
  PRIMARY KEY (seq),
  KEY show_list_IDX01 (havingListSeq)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COLLATE=utf8_bin COMMENT='북마크를 보고싶은 사용자';

CREATE TABLE bookmark_collectors_rel (
  seq bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  listSeq BIGINT(11) NOT NULL COMMENT 'doaminSeq',
  collectorSeq BIGINT(11) NOT NULL COMMENT 'collectorSeq',
  createdAt datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
  PRIMARY KEY (seq),
  KEY having_collectors_rel_IDX01 (collectorSeq),
  KEY having_collectors_rel_IDX02 (havingListSeq)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COLLATE=utf8_bin COMMENT='북마크저와 도메인 연결';

CREATE TABLE bookmark_list (
  seq bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  url varchar(200) COLLATE utf8_bin NOT NULL COMMENT '파싱할 대상 url',
  description varchar(1000) COLLATE utf8_bin NOT NULL COMMENT '파싱할 대상 url',
  PRIMARY KEY (seq)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COLLATE=utf8_bin COMMENT='파싱할 list를 가빈 페이지';


