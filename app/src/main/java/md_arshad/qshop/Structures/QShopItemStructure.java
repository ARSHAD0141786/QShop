package md_arshad.qshop.Structures;

public class QShopItemStructure {

    String itemTitle,companyName, itemName, itemDescription;
    int[] image;
    boolean isInWishList;

    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }

    boolean isInCart;


    public String offerPercent(){
        String result = "";
        double ans = ((previousPrice - currentPrice)/previousPrice)*100;
        result += ""+(int)Math.abs(ans);
        result += "% OFF";
        return result;
    }

    public static String getPriceString(double price){
        String priceString = "\u20B9 ";
        int temp = (int) Math.floor(price);

        float x = (float)(price-temp);
        if(price>9999999){
            priceString += temp/10000000 + ",";
            temp=(int)temp%10000000;
            if(temp<=999999) priceString+="0";
        }
        if(price>99999){
            priceString += temp/100000 + ",";
            temp=(int)temp%100000;
            if(temp<=9999) priceString+="0";
        }
        if(price>999){
            priceString += temp/1000 + ",";
            temp=(int)temp%1000;
            if(temp<=9) priceString+="00";
            else if(temp<=99) priceString+="0";
        }
        priceString += String.format("%.2f",temp+x);
        return priceString;
    }

    public int[] getImage() {
        return image;
    }

    public void setImage(int[] image) {
        this.image = image;
    }

    public String[] getImage_url() {
        return image_url;
    }

    public void setImage_url(String[] image_url) {
        this.image_url = image_url;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean isInWishList() {
        return isInWishList;
    }

    public void setInWishList(boolean inWishList) {
        isInWishList = inWishList;
    }

    private String[] image_url;
    double previousPrice, currentPrice;
    int itemCount;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public double getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(double previousPrice) {
        this.previousPrice = previousPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
