DROP TABLE IF EXISTS ACCOUNT;
 
CREATE TABLE ACCOUNT (
  id INT   PRIMARY KEY,
  code VARCHAR(250) NOT NULL,
  balance number NOT NULL  
);