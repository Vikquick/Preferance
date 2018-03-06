package Models.Gamers;

public class Bullet {
    public int bullet;
    public int mountain;
    public int whists;

    public Bullet(int bullet, int mountain, int whists) {
        this.bullet = bullet;
        this.mountain = mountain;
        this.whists = whists;
    }

    public int getBullet() {
        return bullet;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    public int getMountain() {
        return mountain;
    }

    public void setMountain(int mountain) {
        this.mountain = mountain;
    }

    public int getWhists() {
        return whists;
    }

    public void setWhists(int whists) {
        this.whists = whists;
    }
}
