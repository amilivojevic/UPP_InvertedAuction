
insert into authority (name) values ('USER');

--password: admin
--usernmae: admin
INSERT INTO `user`(`id`,`password`,`username`,`authority_id`)VALUES(1,"$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","admin",1);
--password: ver
--username: ver
INSERT INTO `user`(`id`,`password`,`username`,`authority_id`)VALUES(2,"$2a$10$SmLd5yKXvmiPdb03xmDoJO.0O/urG3vLsYT5W2MB5UadLCr8V7BoG","ver",1);