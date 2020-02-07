create table planets
(
    id             int auto_increment
        primary key,
    name           varchar(30)    not null,
    start_date     date           not null,
    axisX          double         not null,
    axisY          double         not null,
    gravity        double         not null,
    orbital_period double         not null,
    diameter       double         not null,
    info           varchar(65000) null
);

create table satellite
(
    id             int auto_increment
        primary key,
    name           varchar(30)    not null,
    start_date     date           not null,
    axisX          double         not null,
    axisY          double         not null,
    gravity        double         not null,
    orbital_period double         not null,
    diameter       double         not null,
    info           varchar(65000) null,
    id_planet      int            not null,
    constraint satellite_planets_id_fk
        foreign key (id_planet) references planets (id)
);
