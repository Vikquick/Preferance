package Models.Cards;

public enum Suit {
    PICK {
        @Override
        public String toString() {
            return "Пики";
        }
    },
    TREF {
        @Override
        public String toString() {
            return "Трефы";
        }
    },
    BUBI {
        @Override
        public String toString() {
            return "Буби";
        }
    },
    CHERVI {
        @Override
        public String toString() {
            return "Черви";
        }
    }

}
