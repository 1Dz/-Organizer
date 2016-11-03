package model;

/**
 * Created by user on 29.09.16.
 */
public enum Department {
    FPKI {
        public String toRusString() {
            return "ФПКИ";
        }

        public String toEngString() {
            return "FPKI";
        }
    },
    PTKI {
        @Override
        public String toRusString() {
            return "ПТКИ";
        }

        public String toEngString() {
            return "PTKI";
        }
    },
    OGM {
        @Override
        public String toRusString() {
            return "ОГМ";
        }

        public String toEngString() {
            return "OGM";
        }
    },
    PSH {
        @Override
        public String toRusString() {
            return "ПСХ";
        }

        public String toEngString() {
            return "PSH";
        }
    },
    HOZ {
        @Override
        public String toRusString() {
            return "Хоз. отдел";
        }

        public String toEngString() {
            return "HOZ";
        }
    },
    TRANSPORT {
        @Override
        public String toRusString() {
            return "ТЦ";
        }

        public String toEngString() {
            return "TRANSPORT";
        }
    },
    ADMINISTRATION {
        @Override
        public String toRusString() {
            return "Управление";
        }

        public String toEngString() {
            return "ADMINISTRATION";
        }
    };

    public String toEngString() {
        return this.toEngString();
    }

    public String toRusString() { return this.toRusString();}

    public static Department convert(String name)
    {
        switch (name){
            case "ФПКИ":
                return FPKI;
            case "ПТКИ":
                return PTKI;
            case "ОГМ":
                return OGM;
            case "ПСХ":
                return PSH;
            case "Хоз. отдел":
                return HOZ;
            case "ТЦ":
                return TRANSPORT;
            case "Управление":
                return ADMINISTRATION;
            default: return FPKI;

        }
    }
}
