DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS LUCKYDRAW;

CREATE TABLE Employee(ID long auto_increment PRIMARY KEY, FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), DEPARTMENT VARCHAR(255), SALARY NUMERIC(255), STARTDATE  DATE , OFFICELOCATION  VARCHAR(255));
CREATE TABLE LUCKYDRAW(LUCKYDRAWDATE DATE PRIMARY KEY, WINNER_EMPID long);
