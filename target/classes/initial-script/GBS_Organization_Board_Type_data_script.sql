/**
 * MOVED TO GBS-DML.SQL
 */
INSERT INTO GBS.ORGANZIATION_TYPE(ORGANIZATION_TYPE_ID, ORGANIZATION_TYPE_NAME, ORGANIZATION_TYPE_DESCRIPTION, CREATED_BY, CREATED_ON, UPDATED_BY, UPDATED_ON)
  VALUES(DEFAULT, 'Source', NULL, NULL, NULL, NULL, NULL);
INSERT INTO GBS.ORGANZIATION_TYPE(ORGANIZATION_TYPE_ID, ORGANIZATION_TYPE_NAME, ORGANIZATION_TYPE_DESCRIPTION, CREATED_BY, CREATED_ON, UPDATED_BY, UPDATED_ON)
  VALUES(DEFAULT, 'Destination', NULL, NULL, NULL, NULL, NULL);
  
  
  /****************************************************************************************************************/
  
INSERT INTO GBS.BOARD_TYPE(BOARD_TYPE_ID, BOARD_TYPE_NAME, BOARD_TYPE_NAME_NORMALIZE, CREATED_BY, CREATED_ON, UPDATED_BY, UPDATED_ON, HAVE_EXPIRY_DATE, DISPLAY_SEQUENCE, REQUIRED_LEGISLATIVE_TOOL, GOVERNMENT_BOARD_TYPE_ID)
  VALUES(DEFAULT, 'لجنة دائمة', 'لجنه داىمه', NULL, NULL, NULL, NULL, 0, 1, 0, 1);
INSERT INTO GBS.BOARD_TYPE(BOARD_TYPE_ID, BOARD_TYPE_NAME, BOARD_TYPE_NAME_NORMALIZE, CREATED_BY, CREATED_ON, UPDATED_BY, UPDATED_ON, HAVE_EXPIRY_DATE, DISPLAY_SEQUENCE, REQUIRED_LEGISLATIVE_TOOL, GOVERNMENT_BOARD_TYPE_ID)
  VALUES(DEFAULT, 'لجنة غير دائمة', 'لجنه غىر داىمه', NULL, NULL, NULL, NULL, 1, 2, 0, 1);
INSERT INTO GBS.BOARD_TYPE(BOARD_TYPE_ID, BOARD_TYPE_NAME, BOARD_TYPE_NAME_NORMALIZE, CREATED_BY, CREATED_ON, UPDATED_BY, UPDATED_ON, HAVE_EXPIRY_DATE, DISPLAY_SEQUENCE, REQUIRED_LEGISLATIVE_TOOL, GOVERNMENT_BOARD_TYPE_ID)
  VALUES(DEFAULT, 'مجلس دائم', 'مجلس داىم', NULL, NULL, NULL, NULL, 0, 3, 1, 2);
INSERT INTO GBS.BOARD_TYPE(BOARD_TYPE_ID, BOARD_TYPE_NAME, BOARD_TYPE_NAME_NORMALIZE, CREATED_BY, CREATED_ON, UPDATED_BY, UPDATED_ON, HAVE_EXPIRY_DATE, DISPLAY_SEQUENCE, REQUIRED_LEGISLATIVE_TOOL, GOVERNMENT_BOARD_TYPE_ID)
  VALUES(DEFAULT, 'مجلس غير دائم', 'مجلس  غىر داىم', NULL, NULL, NULL, NULL, 1, 4, 1, 2);
