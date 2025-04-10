package items;

public class Item {
    private int id;
    private String itemName;
    private String itemDescription;
    private int itemPrice;
    private int itemDamage;
    private int weight;

    public Item(int id, String itemName, int weight, int itemDamage) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = "Wow, this awesome!";
        this.itemDamage = itemDamage;
        this.itemPrice = 0;
        this.weight = weight;
    }

    //Setters
    public void setItemPrice(int price) {itemPrice = price;}

    public void setItemDamage(int itemDamage) {this.itemDamage = itemDamage;}

    //Getters
    public int getId() {return id;}

    public String getItemName() {return itemName;}

    public String getItemDescription() {return itemDescription;}

    public int getItemPrice(){return this.itemPrice;}

    public int getItemDamage() {return itemDamage;}

    public int getWeight(){return weight;}
}
