package main.java.model.items;

public class XPOrb extends Drop {

    private int xpAmount;

    public XPOrb(int posX, int posY, int xpAmount) {
        super(posX, posY);
        this.xpAmount = xpAmount;
    }

    public void pickUpOrb() {

    }
}