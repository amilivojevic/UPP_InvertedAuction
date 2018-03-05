
insert into authority (name) values ('INDIVIDUAL');
insert into authority (name) values ('AGENT');

INSERT INTO `job_category` (name) VALUES ('building');
INSERT INTO `job_category` (name) VALUES ('cleaning');
INSERT INTO `job_category` (name) VALUES ('driving');
INSERT INTO `job_category` (name) VALUES ('selling');


--password: admin
--usernmae: agent1
INSERT INTO `user`(`password`,`username`,`authority_id`,`name`,`email`,`address`)VALUES("$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","agent1",2,"marko markovic","inverttauctionnagent@gmail.com","markova adresa");
--password: admin
--usernmae: agent2
INSERT INTO `user`(`password`,`username`,`authority_id`,`name`,`email`,`address`)VALUES("$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","agent2",2,"pera peric","inverttauctionnagent@Gmail.com","perina adresa");
--password: admin
--usernmae: agent3
INSERT INTO `user`(`password`,`username`,`authority_id`,`name`,`email`,`address`)VALUES("$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","agent3",2,"zoran zoranovic","inverttauctionnagent@gmail.com","zokijeva adresa");
--password: admin
--usernmae: agent4
INSERT INTO `user`(`password`,`username`,`authority_id`,`name`,`email`,`address`)VALUES("$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","agent4",2,"milan milanic","inverttauctionnagent@gmail.com","zokijeva adresa");

--password: admin
--usernmae: indi
INSERT INTO `user`(`password`,`username`,`authority_id`,`name`,`email`,`address`)VALUES("$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","indi",1,"Maja Majic","inverttauctionnindvidual@gmail.com","Majina adresa");


--comp1
INSERT INTO `company` (`id`, `address`, `distance`, `email`, `name`, `agent_id`) VALUES ('1', 'address', '10', 'email', 'comp1', '1');
--building
INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('1', '1');
--cleaning
INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('1', '2');


--comp2
INSERT INTO `company` (`id`, `address`, `distance`, `email`, `name`, `agent_id`) VALUES ('2', 'address', '10', 'emai2', 'comp2', '2');
--building
INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('2', '1');

--driving
--INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('2', '3');


--comp3
INSERT INTO `company` (`id`, `address`, `distance`, `email`, `name`, `agent_id`) VALUES ('3', 'address', '10', 'emai3', 'comp3', '3');
INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('3', '1');
INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('3', '2');

--selling
INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('3', '4');


--comp4
INSERT INTO `company` (`id`, `address`, `distance`, `email`, `name`, `agent_id`) VALUES ('4', 'address', '10', 'emai4', 'comp4', '4');
INSERT INTO `company_job_categories` (`company_id`, `job_categories_id`) VALUES ('4', '4');