DROP TABLE IF EXISTS ACCOUNT;
 
CREATE TABLE ACCOUNT (
  id INT   PRIMARY KEY,
  code VARCHAR(50) NOT NULL,
  holder_name VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,  
  balance number NOT NULL  
);