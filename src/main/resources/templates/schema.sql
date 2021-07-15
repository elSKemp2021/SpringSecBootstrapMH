# DROP TABLE IF EXISTS `customers`;
# DROP TABLE IF EXISTS `role`;
# DROP TABLE IF EXISTS `roles`;
# CREATE TABLE `users`(
#     `id` INT(200) AUTO_INCREMENT,
#     `age` INT(4),
#     `email` VARCHAR(30),
#     `name` VARCHAR(30),
#     `lastname` VARCHAR(30),
#     `password` VARCHAR(30),
#     `username` VARCHAR(30),
#      PRIMARY KEY (`id`)
# );

# CREATE TABLE IF NOT EXISTS `roles` (
#                          `id` bigint(20) ,
#                          `role` varchar(250) DEFAULT NULL,
#                          PRIMARY KEY (`id`)
# );

# CREATE TABLE IF NOT EXISTS `users_roles` (
#                         `user_id` bigint(20) unsigned DEFAULT NULL,
#                          `role_id` bigint(20)
#
# );

# INSERT INTO `users` VALUES
#       (1,20, 'kemp@mail.ru', 'kemp', 'kemp', '1234', 'kemp'),
#       (2,20, 'ben@mail.ru', 'ben', 'ben', '1234', 'ben');
#
#
# INSERT INTO `roles` (`id`, `role`)
#       VALUES
#       (1,'ROLE_ADMIN'),
#       (2,'ROLE_USER');
#
# insert into users_roles
#       values
#       (1, 1),
#       (1, 2),
#       (2, 2);