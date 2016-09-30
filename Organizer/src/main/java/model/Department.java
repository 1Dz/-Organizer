package model;

/**
 * Created by user on 29.09.16.
 */
public enum Department {
    FPKI {
        public String toString() {
            return "ФПКИ";
        }

        public String toEngString() {
            return "FPKI";
        }
    },
    PTKI {
        @Override
        public String toString() {
            return "ПТКИ";
        }

        public String toEngString() {
            return "PTKI";
        }
    },
    OGM {
        @Override
        public String toString() {
            return "ОГМ";
        }

        public String toEngString() {
            return "OGM";
        }
    },
    PSH {
        @Override
        public String toString() {
            return "ПСХ";
        }

        public String toEngString() {
            return "PSH";
        }
    },
    HOZ {
        @Override
        public String toString() {
            return "Хоз. отдел";
        }

        public String toEngString() {
            return "HOZ";
        }
    },
    TRANSPORT {
        @Override
        public String toString() {
            return "ТЦ";
        }

        public String toEngString() {
            return "TRANSPORT";
        }
    },
    ADMINISTRATION {
        @Override
        public String toString() {
            return "Управление";
        }

        public String toEngString() {
            return "ADMINISTRATION";
        }
    };

    public String toEngString() {
        return this.toEngString();
    }
}
