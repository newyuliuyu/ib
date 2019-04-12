DROP TABLE IF EXISTS ib_subject;
CREATE TABLE ib_subject(
  id INT NOT NULL,
  `name` VARCHAR(10) NOT NULL COMMENT'科目名称',
  PRIMARY KEY (id)
)ENGINE=INNODB;
INSERT INTO ib_subject(id,NAME) VALUES(1000,'语文');
INSERT INTO ib_subject(id,NAME) VALUES(1001,'英语');
INSERT INTO ib_subject(id,NAME) VALUES(1002,'数学');
INSERT INTO ib_subject(id,NAME) VALUES(1003,'物理');
INSERT INTO ib_subject(id,NAME) VALUES(1004,'化学');
INSERT INTO ib_subject(id,NAME) VALUES(1005,'生物');
INSERT INTO ib_subject(id,NAME) VALUES(1006,'政治');
INSERT INTO ib_subject(id,NAME) VALUES(1007,'地理');
INSERT INTO ib_subject(id,NAME) VALUES(1008,'历史');

DROP TABLE IF EXISTS ib_learn_segment;
CREATE TABLE ib_learn_segment(
  id INT NOT NULL,
  `name` VARCHAR(10) NOT NULL COMMENT'学段名称',
  PRIMARY KEY (id)
)ENGINE=INNODB;

INSERT INTO ib_learn_segment(id,NAME) VALUES(1,'小学');
INSERT INTO ib_learn_segment(id,NAME) VALUES(2,'初中');
INSERT INTO ib_learn_segment(id,NAME) VALUES(3,'高中');


DROP TABLE IF EXISTS ib_test_paper;
CREATE TABLE ib_test_paper(
  id BIGINT NOT NULL,
  `name` VARCHAR(255) NOT NULL COMMENT'试卷名称',
  `subject` INT NOT NULL COMMENT'科目ID',
   learnSegment INT NOT NULL COMMENT'学段ID',
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='试卷';
ALTER TABLE ib_test_paper ADD knowledgeSys BIGINT DEFAULT 0 COMMENT '知识体系ID';
ALTER TABLE ib_test_paper ADD `timestamp` BIGINT DEFAULT 0 COMMENT '试卷创建时间';


DROP TABLE IF EXISTS ib_test_paper_attr;
CREATE TABLE ib_test_paper_attr(
	testpaperId BIGINT NOT NULL,
	itemNum int default 0 comment'试题数量',
	relationKnowledgeNum int default 0 comment'试题关联知识点的数量',
	PRIMARY KEY (testpaperId)
)ENGINE=INNODB COMMENT='试卷属性';


DROP TABLE IF EXISTS ib_test_paper_item;
CREATE TABLE ib_test_paper_item(
  testPaperId BIGINT NOT NULL COMMENT'试卷ID',
  itemId BIGINT NOT NULL COMMENT'题目ID',
  KEY testPaperId (testPaperId),
  KEY itemId (itemId)
)ENGINE=INNODB COMMENT='试卷与题目关联';


DROP TABLE IF EXISTS ib_item_stem;
CREATE TABLE ib_item_stem(
  id BIGINT NOT NULL COMMENT'题干ID',
  content TEXT NOT NULL COMMENT'题干内容',
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='题干';

DROP TABLE IF EXISTS ib_item_analysis;
CREATE TABLE ib_item_analysis(
  id BIGINT NOT NULL COMMENT'解题分析ID',
  content TEXT NOT NULL COMMENT'解题分析内容',
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='题目解题思路分析';

DROP TABLE IF EXISTS ib_item_answer;
CREATE TABLE ib_item_answer(
  id BIGINT NOT NULL COMMENT'试题解析ID',
  content TEXT NOT NULL COMMENT'试题解析内容',
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='题目解析';

DROP TABLE IF EXISTS ib_item_comment;
CREATE TABLE ib_item_comment(
  id BIGINT NOT NULL COMMENT'试题评论ID',
  content TEXT NOT NULL COMMENT'试题评论内容',
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='题目点评';

DROP TABLE IF EXISTS ib_knowledge;
CREATE TABLE ib_knowledge(
  id BIGINT NOT NULL COMMENT'知识点ID',
  content VARCHAR(255) NOT NULL COMMENT'知识点内容',
  parentId BIGINT COMMENT'父级知识点内容',
  knowledgeSystemId BIGINT COMMENT'所属知识点体系ID',
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='知识点';
ALTER TABLE ib_knowledge ADD deep TINYINT DEFAULT 1 COMMENT '知识点深度';

ALTER TABLE ib_knowledge ADD `subject` INT DEFAULT 0 COMMENT '所属科目ID';
ALTER TABLE ib_knowledge ADD learnSegment BIGINT DEFAULT 0 COMMENT '所属学段ID';

DROP TABLE IF EXISTS ib_knowledge_system;
CREATE TABLE ib_knowledge_system(
  id BIGINT NOT NULL COMMENT'知识点体系ID',
  `name` VARCHAR(50) NOT NULL COMMENT'知识点题型名称',
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='知识点体系';



DROP TABLE IF EXISTS ib_item;
CREATE TABLE ib_item(
  id BIGINT NOT NULL,
  stem BIGINT DEFAULT 0,
  analysisId BIGINT DEFAULT 0,
  answerId BIGINT DEFAULT 0,
  commentId BIGINT DEFAULT 0,
  subjectId BIGINT DEFAULT 0,
  learnSegmentId BIGINT DEFAULT 0
  PRIMARY KEY (id)
)ENGINE=INNODB COMMENT='题目信息';

DROP TABLE IF EXISTS ib_item_x_knoledge;
CREATE TABLE ib_item_x_knoledge(
  itemId BIGINT NOT NULL,
  knowledgeId BIGINT NOT NULL
)ENGINE=INNODB COMMENT='题目关联知识点';

-- 把试题增加一个录题时间
alter table `ib_item` add timestamp bigint default 0 comment'录题时间';



