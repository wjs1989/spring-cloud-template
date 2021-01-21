CREATE DEFINER=`root`@`%` PROCEDURE `createSerialNum`(OUT `rSerialNum` bigint(20))
BEGIN

	DECLARE serialNum bigint(20) DEFAULT 1;
	DECLARE currentTimestamp bigint(20) DEFAULT unix_timestamp();



	/*声明一个变量，标识是否有sql异常*/
  DECLARE hasSqlError int DEFAULT FALSE;
  /*在执行过程中出任何异常设置hasSqlError为TRUE*/
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET hasSqlError=TRUE;

	 START TRANSACTION;

	  INSERT INTO `unix_timestamp_notice`(`unix_timestamp`, `num`) VALUES ( currentTimestamp, serialNum);

		/*根据hasSqlError判断是否有异常，做操作*/
    IF hasSqlError THEN
			update unix_timestamp_notice set num = num + 1  where unix_timestamp = currentTimestamp;
			select num into serialNum from unix_timestamp_notice where unix_timestamp = currentTimestamp;
    END IF;
	COMMIT;



	set rSerialNum = CONCAT('5687',currentTimestamp,LPAD(serialNum,5,0)) ;

END


CREATE TABLE `unix_timestamp_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unix_timestamp` bigint(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unix_timestamp` (`unix_timestamp`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36749 DEFAULT CHARSET=utf8;
