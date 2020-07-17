--------------------------------------------------------
--  File created - Tuesday-November-21-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table DEPOSIT
--------------------------------------------------------

  CREATE TABLE DEPOSIT
   (	"ID" VARCHAR2(10 BYTE), 
	"YEAR" NUMBER(*,0), 
	"INTEREST" NUMBER(*,0), 
	"AMOUNT" NUMBER(*,0), 
	"DEPOSIT_DATE" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into DEPOSIT
SET DEFINE OFF;
