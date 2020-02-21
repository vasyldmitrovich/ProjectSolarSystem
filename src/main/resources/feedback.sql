create table if not exists solar_system.feedback
(
    First_Name varchar(30) null,
    Last_Name  varchar(30) null,
    Email      varchar(30) null,
    Subject    varchar(30) null,
    Comments   tinytext    null
);

