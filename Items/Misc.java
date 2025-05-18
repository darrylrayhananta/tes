package Items;
public class Misc extends Items implements Sellable {
    private int buyPrice;
    private int sellPrice;

    public Misc(String name, int buyPrice, int sellPrice) {
        super(name, "Misc");
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    // Getter and Setter for the new attributes
    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
}