package md_arshad.qshop.Network;

import java.util.ArrayList;

import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;

public class FetchData {

    public static ArrayList<QShopItemStructure> mElectronicsData;
    public static ArrayList<QShopItemStructure> mFashionData;
    public static ArrayList<QShopItemStructure> mHomeLivingData;
    public static ArrayList<QShopItemStructure> mKidsData;
    public static QShopItemStructure newArrivalItem;
    //TODO: Offer data
    //TODO: Categorywise data
    //TODO: MainPage Banner Image Data
    
    static {
        initElectronicsData();
        initFashionData();
        initHomeFashionData();
        initKidsData();
        newArrivalItem = new QShopItemStructure();
        newArrivalItem.setImage(new int[]{R.drawable.macbook_1});
        newArrivalItem.setInWishList(false);
        newArrivalItem.setItemTitle("Mac Air");
        newArrivalItem.setItemDescription("Recently Launched By Apple");
        newArrivalItem.setCurrentPrice(59999.0);
        newArrivalItem.setPreviousPrice(90999.0);

    }

    private static void initElectronicsData() {
        mElectronicsData = new ArrayList<>();

        //electronics
        QShopItemStructure categoryItemStructure = new QShopItemStructure();
        categoryItemStructure.setImage(new int[]{R.drawable.macbook_1});
        categoryItemStructure.setInWishList(false);
        categoryItemStructure.setItemTitle("Mac Air");
        categoryItemStructure.setItemDescription("Recently Launched By Apple");
        categoryItemStructure.setCurrentPrice(59999.0);
        categoryItemStructure.setPreviousPrice(90999.0);
        mElectronicsData.add(categoryItemStructure);

        QShopItemStructure categoryItemStructure2 = new QShopItemStructure();
        categoryItemStructure2.setImage(new int[]{R.drawable.samsung_j7});
        categoryItemStructure2.setInWishList(false);
        categoryItemStructure2.setItemTitle("Samsung Galaxy J7");
        categoryItemStructure2.setItemDescription("Highly efficient memory management");
        categoryItemStructure2.setCurrentPrice(12999.0);
        categoryItemStructure2.setPreviousPrice(25999.0);
        mElectronicsData.add(categoryItemStructure2);

        QShopItemStructure categoryItemStructure4 = new QShopItemStructure();
        categoryItemStructure4.setImage(new int[]{R.drawable.sony_headphone});
        categoryItemStructure4.setInWishList(false);
        categoryItemStructure4.setItemTitle("Sony Boost");
        categoryItemStructure4.setItemDescription("Sony headphones");
        categoryItemStructure4.setCurrentPrice(8999.0);
        categoryItemStructure4.setPreviousPrice(10999.0);
        mElectronicsData.add(categoryItemStructure4);

        QShopItemStructure categoryItemStructure3 = new QShopItemStructure();
        categoryItemStructure3.setImage(new int[]{R.drawable.galaxy_note_8});
        categoryItemStructure3.setInWishList(false);
        categoryItemStructure3.setItemTitle("Galaxy Note 8");
        categoryItemStructure3.setItemDescription("Super efficient design");
        categoryItemStructure3.setCurrentPrice(19999.0);
        categoryItemStructure3.setPreviousPrice(30999.0);
        mElectronicsData.add(categoryItemStructure3);

        QShopItemStructure categoryItemStructure1 = new QShopItemStructure();
        categoryItemStructure1.setImage(new int[]{R.drawable.macbook_2});
        categoryItemStructure1.setInWishList(false);
        categoryItemStructure1.setItemTitle("Macbook Air Pro");
        categoryItemStructure1.setItemDescription("Best offer grab it");
        categoryItemStructure1.setCurrentPrice(89999.0);
        categoryItemStructure1.setPreviousPrice(100999.0);
        mElectronicsData.add(categoryItemStructure1);


    }

    private static void initHomeFashionData() {
        mHomeLivingData = new ArrayList<>();

        //electronics
        QShopItemStructure categoryItemStructure = new QShopItemStructure();
        categoryItemStructure.setImage(new int[]{R.drawable.sofa_1});
        categoryItemStructure.setInWishList(false);
        categoryItemStructure.setItemTitle("Mac Air");
        categoryItemStructure.setItemDescription("Recently Launched By Apple");
        categoryItemStructure.setCurrentPrice(59999.0);
        categoryItemStructure.setPreviousPrice(90999.0);
        mHomeLivingData.add(categoryItemStructure);

        QShopItemStructure categoryItemStructure2 = new QShopItemStructure();
        categoryItemStructure2.setImage(new int[]{R.drawable.sofa_2});
        categoryItemStructure2.setInWishList(false);
        categoryItemStructure2.setItemTitle("Samsung Galaxy J7");
        categoryItemStructure2.setItemDescription("Highly efficient memory management");
        categoryItemStructure2.setCurrentPrice(12999.0);
        categoryItemStructure2.setPreviousPrice(25999.0);
        mHomeLivingData.add(categoryItemStructure2);

        QShopItemStructure categoryItemStructure4 = new QShopItemStructure();
        categoryItemStructure4.setImage(new int[]{R.drawable.sofa_3});
        categoryItemStructure4.setInWishList(false);
        categoryItemStructure4.setItemTitle("Sony Boost");
        categoryItemStructure4.setItemDescription("Sony headphones");
        categoryItemStructure4.setCurrentPrice(8999.0);
        categoryItemStructure4.setPreviousPrice(10999.0);
        mHomeLivingData.add(categoryItemStructure4);

        QShopItemStructure categoryItemStructure3 = new QShopItemStructure();
        categoryItemStructure3.setImage(new int[]{R.drawable.sofa_4});
        categoryItemStructure3.setInWishList(false);
        categoryItemStructure3.setItemTitle("Galaxy Note 8");
        categoryItemStructure3.setItemDescription("Super efficient design");
        categoryItemStructure3.setCurrentPrice(19999.0);
        categoryItemStructure3.setPreviousPrice(30999.0);
        mHomeLivingData.add(categoryItemStructure3);

    }

    private static void initKidsData() {
        mKidsData = new ArrayList<>();

        //electronics
        QShopItemStructure categoryItemStructure = new QShopItemStructure();
        categoryItemStructure.setImage(new int[]{R.drawable.kids_1});
        categoryItemStructure.setInWishList(false);
        categoryItemStructure.setItemTitle("Mac Air");
        categoryItemStructure.setItemDescription("Recently Launched By Apple");
        categoryItemStructure.setCurrentPrice(59999.0);
        categoryItemStructure.setPreviousPrice(90999.0);
        mKidsData.add(categoryItemStructure);

        QShopItemStructure categoryItemStructure2 = new QShopItemStructure();
        categoryItemStructure2.setImage(new int[]{R.drawable.kids_2});
        categoryItemStructure2.setInWishList(false);
        categoryItemStructure2.setItemTitle("Samsung Galaxy J7");
        categoryItemStructure2.setItemDescription("Highly efficient memory management");
        categoryItemStructure2.setCurrentPrice(12999.0);
        categoryItemStructure2.setPreviousPrice(25999.0);
        mKidsData.add(categoryItemStructure2);

        QShopItemStructure categoryItemStructure4 = new QShopItemStructure();
        categoryItemStructure4.setImage(new int[]{R.drawable.kids_3});
        categoryItemStructure4.setInWishList(false);
        categoryItemStructure4.setItemTitle("Sony Boost");
        categoryItemStructure4.setItemDescription("Sony headphones");
        categoryItemStructure4.setCurrentPrice(8999.0);
        categoryItemStructure4.setPreviousPrice(10999.0);
        mKidsData.add(categoryItemStructure4);

        QShopItemStructure categoryItemStructure3 = new QShopItemStructure();
        categoryItemStructure3.setImage(new int[]{R.drawable.kids_4});
        categoryItemStructure3.setInWishList(false);
        categoryItemStructure3.setItemTitle("Galaxy Note 8");
        categoryItemStructure3.setItemDescription("Super efficient design");
        categoryItemStructure3.setCurrentPrice(19999.0);
        categoryItemStructure3.setPreviousPrice(30999.0);
        mKidsData.add(categoryItemStructure3);

    }

    private static void initFashionData() {
        mFashionData = new ArrayList<>();

        //electronics
        QShopItemStructure categoryItemStructure = new QShopItemStructure();
        categoryItemStructure.setImage(new int[]{R.drawable.jeans_1});
        categoryItemStructure.setInWishList(false);
        categoryItemStructure.setItemTitle("Mac Air");
        categoryItemStructure.setItemDescription("Recently Launched By Apple");
        categoryItemStructure.setCurrentPrice(59999.0);
        categoryItemStructure.setPreviousPrice(90999.0);
        mFashionData.add(categoryItemStructure);

        QShopItemStructure categoryItemStructure2 = new QShopItemStructure();
        categoryItemStructure2.setImage(new int[]{R.drawable.shirt_1});
        categoryItemStructure2.setInWishList(false);
        categoryItemStructure2.setItemTitle("Samsung Galaxy J7");
        categoryItemStructure2.setItemDescription("Highly efficient memory management");
        categoryItemStructure2.setCurrentPrice(12999.0);
        categoryItemStructure2.setPreviousPrice(25999.0);
        mFashionData.add(categoryItemStructure2);

        QShopItemStructure categoryItemStructure4 = new QShopItemStructure();
        categoryItemStructure4.setImage(new int[]{R.drawable.shirt_2});
        categoryItemStructure4.setInWishList(false);
        categoryItemStructure4.setItemTitle("Sony Boost");
        categoryItemStructure4.setItemDescription("Sony headphones");
        categoryItemStructure4.setCurrentPrice(8999.0);
        categoryItemStructure4.setPreviousPrice(10999.0);
        mFashionData.add(categoryItemStructure4);

        QShopItemStructure categoryItemStructure3 = new QShopItemStructure();
        categoryItemStructure3.setImage(new int[]{R.drawable.shirt_3});
        categoryItemStructure3.setInWishList(false);
        categoryItemStructure3.setItemTitle("Galaxy Note 8");
        categoryItemStructure3.setItemDescription("Super efficient design");
        categoryItemStructure3.setCurrentPrice(19999.0);
        categoryItemStructure3.setPreviousPrice(30999.0);
        mFashionData.add(categoryItemStructure3);

        QShopItemStructure categoryItemStructure1 = new QShopItemStructure();
        categoryItemStructure1.setImage(new int[]{R.drawable.jeans_2});
        categoryItemStructure1.setInWishList(false);
        categoryItemStructure1.setItemTitle("Macbook Air Pro");
        categoryItemStructure1.setItemDescription("Best offer grab it");
        categoryItemStructure1.setCurrentPrice(89999.0);
        categoryItemStructure1.setPreviousPrice(100999.0);
        mFashionData.add(categoryItemStructure1);

        QShopItemStructure categoryItemStructure5 = new QShopItemStructure();
        categoryItemStructure5.setImage(new int[]{R.drawable.jeans_3});
        categoryItemStructure5.setInWishList(false);
        categoryItemStructure5.setItemTitle("Macbook Air Pro");
        categoryItemStructure5.setItemDescription("Best offer grab it");
        categoryItemStructure5.setCurrentPrice(89999.0);
        categoryItemStructure5.setPreviousPrice(100999.0);
        mFashionData.add(categoryItemStructure5);

        QShopItemStructure categoryItemStructure6 = new QShopItemStructure();
        categoryItemStructure6.setImage(new int[]{R.drawable.jeans_5});
        categoryItemStructure6.setInWishList(false);
        categoryItemStructure6.setItemTitle("Macbook Air Pro");
        categoryItemStructure6.setItemDescription("Best offer grab it");
        categoryItemStructure6.setCurrentPrice(89999.0);
        categoryItemStructure6.setPreviousPrice(100999.0);
        mFashionData.add(categoryItemStructure6);

        QShopItemStructure categoryItemStructure7 = new QShopItemStructure();
        categoryItemStructure7.setImage(new int[]{R.drawable.shirt_4});
        categoryItemStructure7.setInWishList(false);
        categoryItemStructure7.setItemTitle("Macbook Air Pro");
        categoryItemStructure7.setItemDescription("Best offer grab it");
        categoryItemStructure7.setCurrentPrice(89999.0);
        categoryItemStructure7.setPreviousPrice(100999.0);
        mFashionData.add(categoryItemStructure7);

    }


}
