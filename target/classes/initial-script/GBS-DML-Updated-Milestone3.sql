/* GBS - DML Script (Milestone 3) */

UPDATE GBS.ATTACHMENT_TYPE SET ATTACHMENT_TYPE_NAME='التشكيل' WHERE SHOW_FIELDS = 1 AND FUNCTION_USED = 'ADD_BOARD'
UPDATE GBS.ATTACHMENT_TYPE SET ATTACHMENT_TYPE_NAME='مرجع آخر' WHERE SHOW_FIELDS = 0 AND FUNCTION_USED IS NULL
UPDATE GBS.ATTACHMENT_TYPE SET ATTACHMENT_TYPE_NAME='تعيين الأعضاء' WHERE SHOW_FIELDS = 1 AND FUNCTION_USED = 'UPDATE_BOARD'

  
UPDATE GBS.STATUS_TYPE SET  STATUS_TYPE_NAME='Board' WHERE STATUS_TYPE_ID = 1
UPDATE GBS.STATUS_TYPE SET  STATUS_TYPE_NAME='Member' WHERE STATUS_TYPE_ID = 2