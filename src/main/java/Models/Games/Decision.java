package Models.Games;

public enum Decision {
    WIST {
        @Override
        public String toString() {
            return "ВИСТ";
        }
    }, PASS{
        @Override
        public String toString() {
            return "ПАСС";
        }
    }, MIZER{
        @Override
        public String toString() {
            return "МИЗЕР";
        }
    }, BRIBES{
        @Override
        public String toString() {
            return "ВЗЯТКИ";
        }
    }
}
