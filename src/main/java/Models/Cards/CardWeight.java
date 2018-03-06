package Models.Cards;

public enum CardWeight {
    SIX {
        @Override
        public String toString() {
            return "6";
        }
    },
    SEVEN {
        @Override
        public String toString() {
            return "7";
        }
    },
    EIGHT {
        @Override
        public String toString() {
            return "8";
        }
    },
    NINE {
        @Override
        public String toString() {
            return "9";
        }
    },
    TEN {
        @Override
        public String toString() {
            return "10";
        }
    },
    KNAVE {
        @Override
        public String toString() {
            return "Валет";
        }
    },
    LADY {
        @Override
        public String toString() {
            return "Дама";
        }
    },
    KING {
        @Override
        public String toString() {
            return "Король";
        }
    },
    ACE {
        @Override
        public String toString() {
            return "Туз";
        }
    }
}
