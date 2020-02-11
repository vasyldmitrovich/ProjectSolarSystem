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

#First planet
INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Mercury', 88, 4879, 3.7, 0, 'This short desc mercury',
                 'This is full description mercury', 'en') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM solar_system.planets WHERE name='Mercury') LIMIT 1;

# Second planet
INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Venus', 224.7, 12.104, 8.9, 0, 'This short desc venus',
                      'This is full description venus', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Venus') LIMIT 1;

# Others planets
INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Earth', 365.2, 12.756, 9.8, 1, 'This short desc earth',
                      'This is full description earth', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Earth') LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Mars', 687.0, 6792, 3.7, 1, 'This short desc mars',
                      'This is full description mars', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Mars') LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Jupiter', 4331, 142.984, 23.1, 1, 'This short desc Jupiter',
                      'This is full description Jupiter', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Jupiter') LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Saturn', 10.747, 120.536, 9.0, 1, 'This short desc Saturn',
                      'This is full description Saturn', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Saturn') LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Uranus', 30.589, 51.118, 8.7, 1, 'This short desc Uranus',
                      'This is full description Uranus', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Uranus') LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Neptune', 59.800, 49.528, 11.0, 1, 'This short desc Neptune',
                      'This is full description Neptune', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Neptune') LIMIT 1;

INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Pluto', 90.560, 2370, 0.7, 1, 'This short desc Pluto',
                      'This is full description Pluto', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Pluto') LIMIT 1;

