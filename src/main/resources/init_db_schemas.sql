create schema if not exists solar_system;

create table if not exists solar_system.planets
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


create table if not exists solar_system.satellite
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

create table if not exists solar_system.images
(
    id               int auto_increment
        primary key,
    path_to_the_file varchar(1000) not null,
    id_planet        int           not null,
    constraint images_planets_id_fk
        foreign key (id_planet) references solar_system.planets (id)
            on delete cascade
);

INSERT INTO solar_system.images (id, path_to_the_file, id_planet) VALUES (1, '/resources/pictures/first_image_mercury.jpg', 1);
INSERT INTO solar_system.images (id, path_to_the_file, id_planet) VALUES (2, '/resources/pictures/second_image_mercury.jpg', 1);
INSERT INTO solar_system.images (id, path_to_the_file, id_planet) VALUES (3, '/resources/pictures/third_image_mercury.jpg', 1);
INSERT INTO solar_system.images (id, path_to_the_file, id_planet) VALUES (4, '/resources/pictures/fourth_image__not__mercury.jpg', 2);

INSERT INTO solar_system.planets (id, name, start_date, orbital_period, diameter, gravity, is_satellites, short_description, full_description, language_id) VALUES (1, 'mercury', '2020-02-27', 88, 4879, 3.7, 0, 'This short desc mercuty', 'This is full description mercury', 'en');
INSERT INTO solar_system.planets (id, name, start_date, orbital_period, diameter, gravity, is_satellites, short_description, full_description, language_id) VALUES (2, 'venus', '2020-02-27', 224.7, 12.104, 8.9, 0, 'This short desc venus', 'This is full description venus', 'en');