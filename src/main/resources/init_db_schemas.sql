create database
    if not exists solar_system
DEFAULT CHARACTER SET utf8
        DEFAULT COLLATE utf8_general_ci;

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

create table if not exists solar_system.planets_ua
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

create table if not exists solar_system.feedback
(
    First_Name varchar(30) null,
    Last_Name  varchar(30) null,
    Email      varchar(30) null,
    Subject    varchar(30) null,
    Comments   tinytext    null
);


#Planets
INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Mercury', 88, 4879, 3.7, 0, 'Mercury is the smallest and innermost planet in the Solar System. Its orbit around the Sun takes 87.97 days, the shortest of all the planets in the Solar System. It is named after the Roman deity Mercury, the messenger of the gods.',
                 'Like Venus, Mercury orbits the Sun within Earths orbit as an inferior planet, and its apparent distance from the Sun as viewed from Earth never exceeds 28°. This proximity to the Sun means the planet can only be seen near the western horizon after sunset or eastern horizon before sunrise, usually in twilight. At this time, it may appear as a bright star-like object, but is often far more difficult to observe than Venus.
The planet telescopically displays the complete range of phases, similar to Venus and the Moon, as it moves in its inner orbit relative to Earth, which recurs over its synodic period of approximately 116 days. Mercury rotates in a way that is unique in the Solar System. It is tidally locked with the Sun in a 3:2 spin-orbit resonance, meaning that relative to the fixed stars, it rotates on its axis exactly three times for every two revolutions it makes around the Sun.
As seen from the Sun, in a frame of reference that rotates with the orbital motion, it appears to rotate only once every two Mercurian years. An observer on Mercury would therefore see only one day every two Mercurian years.', 'en') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM solar_system.planets WHERE name='Mercury') LIMIT 1;

INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Меркурій', 88, 4879, 3.7, 0, 'Меркурій - найменша і найближча до Сонця планета. Вона так повільно обертається, що проходячи повне коло навколо сонця, обертається навколо своєї осі всього 1,5 раза, через що сонячна доба на планеті триває 175 земних діб.
Тому на нічній половині Меркурія температура опускається до -180 °C, а на денній половині планети розжарюється до +430°С.',
                      'Атмосфера Меркурія дуже розряджена і практично прирівнюється до вакууму. Поверхня нагадує місячну - безліч кратерів (від зіткнення з астероїдами), і гори висотою до 4 км (місячні гори можуть бути в півтора раза вищими). На відміну від супутника Землі, на зворотному боці Меркурія розташовані здуття, які утворилися під дією сонячних припливів.
Також є високі уступи, чия протяжність може досягати декількох сотень кілометрів.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Меркурій') LIMIT 1;


INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Venus', 224.7, 12.104, 8.9, 0, 'Venus is the second planet from the Sun. It is named after the Roman goddess of love and beauty. As the second-brightest natural object in the night sky after the Moon,
Venus can cast shadows and, rarely, is visible to the naked eye in broad daylight. Venus lies within Earths orbit, and so never appears to venture far from the Sun, either setting in the west just after dusk or rising in the east a bit before dawn. Venus orbits the Sun every 224.7 Earth days.',
                      'With a rotation period of 243 Earth days, it takes longer to rotate about its axis than any planet in the Solar System and rotates in the opposite direction to all but Uranus (meaning the Sun rises in the west and sets in the east).
Venus does not have any natural satellites, a distinction it shares only with Mercury among planets in the Solar System. Venus is a terrestrial planet and is sometimes called Earths "sister planet" because of their similar size, mass, proximity to the Sun, and bulk composition. It is radically different from Earth in other respects.
It has the densest atmosphere of the four terrestrial planets, consisting of more than 96% carbon dioxide. The atmospheric pressure at the planets surface is 92 times that of Earth, or roughly the pressure found 900 m (3,000 ft) underwater on Earth.', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Venus') LIMIT 1;

INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Венера', 224.7, 12.104, 8.9, 0, 'Венера - Найближча до Землі планета. Її оточує шар дуже щільних хмар, внаслідок парникового ефекту. Температура поверхні планети розігріта до + 470°C, відсоток вмісту в атмосфері вуглекислого газу набагато більший, ніж в гірських породах, при цьому планета розташована зовсім недалеко від Сонця, що і призводить до такого ефекту підвищення температури.
На Венері постійно відбуваються спалахи блискавок, які перевищують за інтенсивністю спалахи на Землі.',
                      'Венеру дуже часто називають «сестрою» Землі, оскільки їх розміри та маса дуже наближені один до одного, але істотні відмінності спостерігаються в атмосфері і поверхні планет. Адже якщо більша частина Землі покрита океанами, то на Венері побачити воду просто неможливо.
За припущеннями вчених колись поверхня планети була також представлена ​​водним простором, але в певний момент сталося сильне незначне підвищення температури всередині Венери і всі океани просто випарувалися, а пари були віднесені в космос сонячним вітром.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Венера') LIMIT 1;


INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Earth', 365.2, 12.756, 9.8, 1, 'Earth is the third planet from the Sun and the only astronomical object known to harbor life. According to radiometric dating and other evidence, Earth formed over 4.5 billion years ago. Earth''s gravity interacts with other objects in space, especially the Sun and the Moon, which is Earth''s only natural satellite.
Earth orbits around the Sun in 365.256 days, a period known as an Earth sidereal year. During this time, Earth rotates about its axis about 366.256 times.',
                      'Earths axis of rotation is tilted with respect to its orbital plane, producing seasons on Earth. The gravitational interaction between Earth and the Moon causes tides, stabilizes Earth''s orientation on its axis, and gradually slows its rotation. Earth is the densest planet in the Solar System and the largest and most massive of the four rocky planets.', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Earth') LIMIT 1;

INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Земля', 365.2, 12.756, 9.8, 1, 'Планета Земля має атмосферу, яку утримують сили гравітації, до складу атмосфери входять важливі елементи водню та вуглецю, які роблять можливим на Землі життя. Атмосфера Землі складається з декількох шарів: Нижній - тропосфера розташована на висоті 10-15 км від поверхні Землі. У цьому шарі формуються хмари та інші природні явища, температура тропосфери -40°C -50°C.',
                      'Вище розташований інший шар - стратосфера, який містить газ озон, він поглинає хвилі сонячної радіації, під впливом яких в стратосфері температура підвищується до + 15 °C. Ще вище - іоносфера, де температура знижується до -90 °C.
Поверхня Землі складається на 2/3 з води, решта це континенти, де і в воді і на суші розвивається життя. Кисень на Землі, не критична температура на поверхні планети і інші властивості дали сприятливу можливість для існування рослинного, тваринного світу та життя людини на Землі
Наша планета відіграє унікальну роль у Сонячній системі, адже Земля - ​​єдина планета, на якій є життя! Розташувалася Земля вкрай вдалим чином. Вона подорожує по орбіті на відстані майже 150 000 000 кілометрів від Сонця, а це означає лише одне - на Землі досить тепло, щоб вода зберігалася в рідкому вигляді.
За умови жарких температур вода б просто випарується, а в холоді перетворювалася б у лід. Тільки на Землі присутня атмосфера, в якій може дихати людина і всі живі організми.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Земля') LIMIT 1;


INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Mars', 687.0, 6792, 3.7, 1, 'Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System after Mercury. In English, Mars carries a name of the Roman god of war and is often referred to as the Red Planet. The latter refers to the effect of the iron oxide prevalent on Mars surface, which gives it a reddish appearance distinctive among the astronomical bodies visible to the naked eye.',
                      'Mars is a terrestrial planet with a thin atmosphere, having surface features reminiscent both of the impact craters of the Moon and the valleys, deserts, and polar ice caps of Earth.
The days and seasons are likewise comparable to those of Earth, because the rotational period as well as the tilt of the rotational axis relative to the ecliptic plane are very similar. Mars is the site of Olympus Mons, the largest volcano and highest known mountain on any planet in the Solar System, and of Valles Marineris, one of the largest canyons in the Solar System. The smooth Borealis basin in the northern hemisphere covers 40% of the planet and may be a giant impact feature. Mars has two moons, Phobos and Deimos, which are small and irregularly shaped. These may be captured asteroids,[20][21] similar to 5261 Eureka, a Mars trojan.', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Mars') LIMIT 1;

INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Марс', 687.0, 6792, 3.7, 1, 'Марс — червона планета, четверта за віддаленістю від Сонця, яка ще з часів Галілео Галілея є обєктом пильної уваги вчених і дослідників з усього світу.',
                      'Відносна наближеність Марса до планети Земля і відсутність хмарного шару над планетою створюють ідеальні умови для астрономів і вчених, які намагаються дізнатися якомога більше про планету або навіть знайти життя на ній.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Марс') LIMIT 1;


INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Jupiter', 4331, 142.984, 23.1, 1, 'Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a gas giant with a mass one-thousandth that of the Sun, but two-and-a-half times that of all the other planets in the Solar System combined.
Jupiter is one of the brightest objects visible to the naked eye in the night sky, and has been known to ancient civilizations since before recorded history. It is named after the Roman god Jupiter.',
                      'When viewed from Earth, Jupiter can be bright enough for its reflected light to cast shadows, and is on average the third-brightest natural object in the night sky after the Moon and Venus.
Jupiter is primarily composed of hydrogen with a quarter of its mass being helium, though helium comprises only about a tenth of the number of molecules. It may also have a rocky core of heavier elements, but like the other giant planets, Jupiter lacks a well-defined solid surface. Because of its rapid rotation, the planets shape is that of an oblate spheroid (it has a slight but noticeable bulge around the equator).', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Jupiter') LIMIT 1;

INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Юпітер', 4331, 142.984, 23.1, 1, 'Юпітер - найбільша планета Сонячної системи, що складається з газу, шари якого знаходяться в постійних вихреподібних рухах. Діаметр Юпітера величезний - 143 000 км (для порівняння: діаметр Землі 13 000 км). Не дивлячись на свої великі розміри, Юпітер дуже швидко обертається навколо своєї осі (за 9 год 50 хв) через що діаметри на полюсах планети стиснуті, а екватор розтягнутий...',
                      'Юпітер - пята від Сонця планета. Розташована вона на відстані 5,2 астрономічних років від Сонця, це приблизно 775 млн км. Планети Сонячної системи поділяються астрономами на дві умовні групи: планети земного типу і газові гіганти. Найбільшою планетою з групи газових гігантів є Юпітер.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Юпітер') LIMIT 1;


INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Saturn', 10.747, 120.536, 9.0, 1, 'Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius about nine times that of Earth. It has only one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.',
                      'Saturn is named after the Roman god of wealth and agriculture; its astronomical symbol (♄) represents the gods sickle.
Saturns interior is most likely composed of a core of iron–nickel and rock (silicon and oxygen compounds). This core is surrounded by a deep layer of metallic hydrogen, an intermediate layer of liquid hydrogen and liquid helium, and finally a gaseous outer layer. Saturn has a pale yellow hue due to ammonia crystals in its upper atmosphere.
An electrical current within the metallic hydrogen layer is thought to give rise to Saturns planetary magnetic field, which is weaker than Earths, but has a magnetic moment 580 times that of Earth due to Saturns larger size.', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Saturn') LIMIT 1;


INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Сатурн', 10.747, 120.536, 9.0, 1, 'Ця дивовижна і красива планета має чітко окресленні кільця, які легко розгледіти у звичайний телескоп. Сатурн є шостою за рахунком планетою від Сонця - середня відстань до світила становить 1430 млн км.',
                      'Період обертання планети по орбіті складає 29 років, а час обороту навколо своєї осі - майже 10 год 40 хв. Екваторіальний радіус Сатурна становить 60268 км, а його маса - понад 568 тисяч мільярдів мегатонн. Таким чином, Сатурн є другою за розміром і масою планетою Сонячної системи після Юпітера.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Сатурн') LIMIT 1;


INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Uranus', 30.589, 51.118, 8.7, 1, 'Uranus (from the Latin name Ūranus for the Greek god Οὐρανός) is the seventh planet from the Sun. It has the third-largest planetary radius and fourth-largest planetary mass in the Solar System. Uranus is similar in composition to Neptune, and both have bulk chemical compositions which differ from that of the larger gas giants Jupiter and Saturn.',
                      'For this reason, scientists often classify Uranus and Neptune as "ice giants" to distinguish them from the gas giants.
Uranus atmosphere is similar to Jupiters and Saturns in its primary composition of hydrogen and helium, but it contains more "ices" such as water, ammonia, and methane, along with traces of other hydrocarbons.', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Uranus') LIMIT 1;


INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Уран', 30.589, 51.118, 8.7, 1, 'Ця незвичайна планета видніється в синіх і зелених кольорах внаслідок поглинання воднем і метаном інфрачервоного спектра. На поверхні Урана вирують вітри з величезною швидкістю до 600 км/год, рухаючись по ходу обертання планети.',
                      'Унікальність Урана ще в тому, що його вісь обертання сильно нахилена, майже паралельно до площини екліптики, тому з Землі полюси планети можна побачити тільки наполовину і то, тільки протягом 42 років. Поки єдина теорія цього феномена така - можливо, в історії планети було зіткнення з якимось великим небесним тілом.
Розвиток оптики в новий час призвів до того, що 13 березня 1781 р. були розширені межі сонячної системи відкриттям планети Уран, відкриття було зроблено Вільямом Гершелем.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Уран') LIMIT 1;


INSERT INTO solar_system.planets (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Neptune', 59.800, 49.528, 11.0, 1, 'Neptune is the eighth and farthest known planet from the Sun in the Solar System. In the Solar System, it is the fourth-largest planet by diameter, the third-most-massive planet, and the densest giant planet. Neptune is 17 times the mass of Earth, slightly more massive than its near-twin Uranus.',
                      'Neptune is denser and physically smaller than Uranus because its greater mass causes more gravitational compression of its atmosphere. Neptune orbits the Sun once every 164.8 years at an average distance of 30.1 au (4.5 billion km; 2.8 billion mi).
It is named after the Roman god of the sea and has the astronomical symbol ♆, a stylised version of the god Neptunes trident.', 'en') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Neptune') LIMIT 1;

INSERT INTO solar_system.planets_ua (name, orbital_period, diameter, gravity, is_satellites,
                                  short_description, full_description, language_id)
SELECT * FROM (SELECT 'Нептун', 59.800, 49.528, 11.0, 1, 'Ця планета, подібно Урану, складається з газу до основного складу якого входять вода, метан і аміак. Саме, через велику концентрацію в атмосфері метану планета набула блакитного кольору. Над поверхнею Нептуна простягаються хмари з аміаку і води, а над ними щільний шар метанових хмар, крім того в атмосфері планети присутній водень і гелій.',
                      'Сама атмосфера володіє підвищеною активністю, де потужні вітри дмуть зі швидкістю понад 2000 км/год, утворюючи величезні плями розміром з нашу планету.
Нептун - це останній з чотирьох газових гігантів, що входять в сонячну систему. Він розташований на восьмому місці по віддаленості від сонця. Завдяки синьому кольору планета отримала свою назву на честь давньоримського владики океану - Нептуна. Планета має 14 супутників, відомих на даний момент, і 6 кілець.', 'ua') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM solar_system.planets WHERE name='Нептун') LIMIT 1;

#Pictures
INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Mercury.jpg', 1) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Mercury.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Mercury2.jpg', 1) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Mercury2.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Venus.jpg', 2) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Venus.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Venus2.jpg', 2) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Venus2.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Earth.jpg', 3) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Earth.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Earth2.jpg', 3) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Earth2.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Mars.jpg', 4) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Mars.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Mars2.jpg', 4) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Mars2.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Jupiter.jpg', 5) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Jupiter.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Jupiter2.jpg', 5) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Jupiter2.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Saturn.jpg', 6) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Saturn.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Saturn2.jpg', 6) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Saturn2.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Uranus.jpg', 7) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Uranus.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Uranus2.jpg', 7) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Uranus2.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Neptune.jpg', 8) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Neptune.jpg'
    ) LIMIT 1;

INSERT INTO solar_system.images (path_to_the_file, id_planet)
SELECT * FROM (SELECT '/images/resources/Neptune2.jpg', 8) AS tmp
WHERE NOT EXISTS (
        SELECT path_to_the_file FROM solar_system.images WHERE
                path_to_the_file='/images/resources/Neptune2.jpg'
    ) LIMIT 1;
