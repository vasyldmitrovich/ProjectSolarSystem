
create table if not exists planets
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
    language_id       varchar(2)  not null
);


create table if not exists satellite
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
        foreign key (id_planet) references planets (id)
            on delete cascade
);

create table if not exists images
(
    id               int auto_increment
        primary key,
    path_to_the_file varchar(1000) not null,
    id_planet        int           not null,
    constraint images_planets_id_fk
        foreign key (id_planet) references solar_system.planets (id)
            on delete cascade
);

