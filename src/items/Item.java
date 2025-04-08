package items;

public class Item {
    protected int id;
    protected String itemName;
    protected String itemDescription;
    protected int itemPrice;
    protected int itemDamage;
    protected int weight;

    public Item(int id, String itemName, int weight, int itemDamage) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = "Wow, this awesome!";
        this.itemDamage = itemDamage;
        this.itemPrice = 0;
        this.weight = weight;
    }

    public int getId() {return id;}

    public String getItemName() {return itemName;}

    public String getItemDescription() {return itemDescription;}

    public void setItemPrice(int price) {itemPrice = price;}

    public void setItemDamage(int itemDamage) {this.itemDamage = itemDamage;}

    public int getItemDamage() {return itemDamage;}
}
