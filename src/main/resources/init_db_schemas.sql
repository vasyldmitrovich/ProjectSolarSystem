create schema if not exists solar_system;

create table if not exists solar_system.planets
(
    id                int auto_increment
        primary key,
    name              varchar(30) not null,
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

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/resources/pictures/first_image_mercury.jpg', 1) AS tmp
                WHERE NOT EXISTS (
                    SELECT path_to_the_file FROM solar_system.images WHERE
                        path_to_the_file='/resources/pictures/first_image_mercury.jpg'
                    ) LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'mercury', 88, 4879, 3.7, 0, 'This short desc mercury',
                 'This is full description mercury', 'en') AS tmp
                 WHERE NOT EXISTS (
                     SELECT name FROM solar_system.planets WHERE
                             name='mercury'
                     ) LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'venus', 224.7, 12.104, 8.9, 0, 'This short desc venus',
                      'This is full description venus', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE
                name='venus'
    ) LIMIT 1;

