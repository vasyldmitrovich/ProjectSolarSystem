create schema if not exists solar_system;

create table if not exists solar_system_temp.planets
(
    id                int auto_increment
        primary key,
    name              varchar(30) not null,
    start_date        date        not null,
    orbital_period    double      not null,
    diameter          double      not null,
    gravity           double      not null,
    is_satellites     tinyint(1)  not null,
    short_description text        null,
    full_description  text        null,
    language_id       varchar(2)  not null
);


create table if not exists solar_system_temp.satellite
(
    id                int auto_increment
        primary key,
    name              varchar(30) not null,
    start_date        date        not null,
    orbital_period    double      not null,
    diameter          double      not null,
    gravity           double      not null,
    short_description text        null,
    full_description  text        null,
    language_id       varchar(2)  not null,
    id_planet         int         not null,
    constraint satellite_planets_id_fk
        foreign key (id_planet) references solar_system.planets (id)
            on delete cascade
);

create table if not exists solar_system_temp.images
(
    id               int auto_increment
        primary key,
    path_to_the_file varchar(1000) not null,
    id_planet        int           not null,
    constraint images_planets_id_fk
        foreign key (id_planet) references solar_system.planets (id)
            on delete cascade
);

INSERT INTO solar_system_temp.images
    (path_to_the_file, id_planet)
    VALUES ('/resources/pictures/first_image_mercury.jpg', 1);

INSERT INTO solar_system_temp.planets
    (name, start_date, orbital_period, diameter, gravity,
     is_satellites, short_description, full_description, language_id)
     VALUES ('mercury', '2020-02-27', 88, 4879, 3.7, 0, 'This short desc',
             'This is full description mercury', 'en');