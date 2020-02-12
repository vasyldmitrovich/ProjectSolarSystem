package org.solarsystem.web.service;

public class PlanetPosition {

    public static final double J2000 = 2451545.0;
    public static final double[][] TABLE_M_M0 = {{174.7948, 4.09233445},
            {50.4161, 1.60213034},
            {357.5291, 0.98560028},
            {19.3730, 0.52402068},
            {20.0202, 0.08308529},
            {317.0207, 0.03344414},
            {141.0498, 0.01172834},
            {256.2250, 0.00598103},
            {14.882, 0.00396},
    };
    public static final double[][] TABLE_Ec = {{23.4400, 2.9818, 0.5255, 0.1058, 0.0241, 0.0055, 0.0026},
            {0.7758, 0.0033, 0, 0, 0, 0, 0},
            {1.914, 0.0200, 0.0003, 0, 0, 0, 0},
            {10.6912, 0.6228, 0.0503, 0.0046, 0.0005, 0, 0.0001},
            {5.5549, 0.1683, 0.0071, 0.0003, 0, 0, 0.0001},
            {6.3585, 0.2204, 0.0106, 0.0006, 0, 0, 0.0001},
            {5.3042, 0.1534, 0.0062, 0.0003, 0, 0, 0.0001},
            {1.0302, 0.0058, 0, 0, 0, 0, 0.001},
            {28.3150, 4.3408, 0.9214, 0.2235, 0.0627, 0.0174, 0.0096},
    };
    public static final double[][] TABLE_P_e = {{230.3265, 0.0351},
            {73.7576, 2.6376},
            {102.9373, 23.4393},
            {71.0041, 25.1918},
            {237.1015, 3.1189},
            {99.4587, 26.7285},
            {5.4634, 82.2298},
            {182.2100, 27.8477},
            {184.5484, 119.6075},
    };
    public static final double[][] TABLE_Ea_Ed = {{-0.0000, 0, 0, 0, 0.0351, 0, 0, 0},
            {-0.0304, 0, 0, 0.0001, 2.6367, 0.0009, 0, 0.0036},
            {-2.4657, 0.0529, -0.0014, 0.0003, 22.7908, 0.5991, 0.0492, 0.0003},
            {-2.8608, 0.0713, -0.0022, 0.0004, 24.3880, 0.7332, 0.0706, 0.0011},
            {-0.0425, 0, 0, 0.0001, 3.1173, 0.0015, 0, 0.0034},
            {-3.2338, 0.0909, -0.0031, 0.0009, 25.7696, 0.8640, 0.0949, 0.0010},
            {-42.5874, 12.8117, -2.6077, 17.6902, 56.9083, -0.8433, 26.1648, 3.34},
            {-3.5214, 0.1078, -0.0039, 0.0163, 26.7643, 0.9669, 0.1166, 0.060},
            {-19.3248, 3.0286, -0.4092, 0.5052, 49.8309, 4.9707, 5.5910, 0.19},
    };
    public static final double[][] TABLE_REL_EARTH = {{0.38710, 0.20563, 7.005, 29.125, 48.331, 174.795},
            {0.72333, 0.00677, 3.395, 54.884, 76.680, 50.416},
            {1.00000, 0.01671, 0, 288.064, 174.873, 357.529},
            {1.52368, 0.09340, 1.850, 286.502, 49.558, 19.373},
            {5.20260, 0.04849, 1.303, 273.867, 100.464, 20.020},
            {9.55491, 0.05551, 2.489, 339.391, 113.666, 317.021},
            {19.21845, 0.04630, 0.773, 98.999, 74.006, 141.050},
            {30.11039, 0.00899, 1.770, 276.340, 131.784, 256.225},
            {39.543, 0.2490, 17.140, 113.768, 110.307, 14.882},
    };

    public static final double[][] TABLE_n_P = {{4.092317, 0.37073, 77.456},
            {1.602136, 0.72330, 131.564},
            {0.985608, 0.99972, 102.937},
            {0.524039, 1.51039, 336.060},
            {0.083056, 5.19037, 14.331},
            {0.033371, 9.52547, 93.057},
            {0.011698, 19.17725, 173.005},
            {0.005965, 30.10796, 48.124},
            {0.003964, 37.09129, 224.075},
    };

    private int numberOfPlanet;
    private double jdTime;

    public double getJdTime() {
        return jdTime;
    }

    public int getNumberOfPlanet() {
        return numberOfPlanet;
    }

    public PlanetPosition(String planetName, String time) {
        this.jdTime = getJulianDateNumber(time);
        this.numberOfPlanet = numberOfPlanet(planetName);
    }

    public int numberOfPlanet(String namePlanet) {
        int i;
        String planet = namePlanet.toLowerCase();
        switch (planet) {
            case "mercury":
                i = 0;
                break;
            case "venus":
                i = 1;
                break;
            case "earth":
                i = 2;
                break;
            case "mars":
                i = 3;
                break;
            case "jupiter":
                i = 4;
                break;
            case "saturn":
                i = 5;
                break;
            case "uranus":
                i = 6;
                break;
            case "neptune":
                i = 7;
                break;
            case "pluto":
                i = 8;
                break;
            default:
                return 0;
        }
        return i;
    }


    public double getJulianDateNumber(String time) {

        return JDday.getTime(time);
    }


    public double getMeanAnomaly() {
        double M_0 = TABLE_M_M0[numberOfPlanet][0];

        double M_1 = TABLE_M_M0[numberOfPlanet][1];

        double deltaTime = jdTime - J2000;

        return (M_0 + M_1 * (deltaTime)) % 360;
    }

    public double getEquationOfCenter() {

        double C = TABLE_Ec[numberOfPlanet][0] * Math.sin(getMeanAnomaly() * Math.PI / 180) +
                TABLE_Ec[numberOfPlanet][1] * Math.sin(2 * getMeanAnomaly() * Math.PI / 180)
                + TABLE_Ec[numberOfPlanet][2] * Math.sin(3 * getMeanAnomaly() * Math.PI / 180)
                + TABLE_Ec[numberOfPlanet][3] * Math.sin(4 * getMeanAnomaly() * Math.PI / 180)
                + TABLE_Ec[numberOfPlanet][4] * Math.sin(5 * getMeanAnomaly() * Math.PI / 180)
                + TABLE_Ec[numberOfPlanet][5] * Math.sin(6 * getMeanAnomaly() * Math.PI / 180);
        return C;
    }

    public double getTrueAnomaly() {
        return getMeanAnomaly() + getEquationOfCenter();


    }

    public double getEclipticalCoordinates() {

        double meanLongitude = getMeanAnomaly() + TABLE_P_e[numberOfPlanet][0];
        return (meanLongitude + getEquationOfCenter() + 180) % 360;
    }

    public double getRightAscension() {

        double longitude = getEclipticalCoordinates();
        return longitude + TABLE_Ea_Ed[numberOfPlanet][0] * Math.sin(2 * longitude * Math.PI / 180)
                + TABLE_Ea_Ed[numberOfPlanet][1] * Math.sin(4 * longitude * Math.PI / 180)
                + TABLE_Ea_Ed[numberOfPlanet][2] * Math.sin(6 * longitude * Math.PI / 180);
    }

    public double getDeclination() {

        double longitude = getEclipticalCoordinates();
        return TABLE_Ea_Ed[numberOfPlanet][4] * Math.sin(longitude * Math.PI / 180)
                + TABLE_Ea_Ed[numberOfPlanet][5] * Math.pow(Math.sin(longitude * Math.PI / 180), 3)
                + TABLE_Ea_Ed[numberOfPlanet][6] * Math.pow(Math.sin(longitude * Math.PI / 180), 5);
    }
//_________________________________________________________________________


    public double getDistanceFromSun() {

        return TABLE_n_P[numberOfPlanet][1] / (1 + TABLE_REL_EARTH[numberOfPlanet][1] * Math.cos(getTrueAnomaly() * Math.PI / 180));
    }

    public double getXhelio() {

        double r = getDistanceFromSun();
        double first = Math.cos(TABLE_REL_EARTH[numberOfPlanet][4]
                * Math.PI / 180) * Math.cos((TABLE_REL_EARTH[numberOfPlanet][3]
                + getTrueAnomaly()) * Math.PI / 180);
        double second = Math.sin(TABLE_REL_EARTH[numberOfPlanet][4]
                * Math.PI / 180) * Math.cos(TABLE_REL_EARTH[numberOfPlanet][2] * Math.PI / 180)
                * Math.sin((TABLE_REL_EARTH[numberOfPlanet][3] + getTrueAnomaly()) * Math.PI / 180);
        return r * (first - second);

    }

    public double getYhelio() {

        double r = getDistanceFromSun();
        double first = Math.sin(TABLE_REL_EARTH[numberOfPlanet][4] * Math.PI / 180)
                * Math.cos((TABLE_REL_EARTH[numberOfPlanet][3] + getTrueAnomaly()) * Math.PI / 180);
        double second = Math.cos(TABLE_REL_EARTH[numberOfPlanet][4] * Math.PI / 180)
                * Math.cos(TABLE_REL_EARTH[numberOfPlanet][2] * Math.PI / 180)
                * Math.sin((TABLE_REL_EARTH[numberOfPlanet][3] + getTrueAnomaly()) * Math.PI / 180);
        return r * (first + second);
    }

    public double getZhelio() {

        double r = getDistanceFromSun();
        return r * Math.sin(TABLE_REL_EARTH[numberOfPlanet][2]
                * Math.PI / 180) * Math.sin((TABLE_REL_EARTH[numberOfPlanet][3] + getTrueAnomaly()) * Math.PI / 180);

    }
}
