package core;

/**
 * Created by
 * Jakub Gawrylkowicz 1326002
 * Konrad Laaber 1301118
 *
 * Implementation of the decorater pattern.
 *
 * The superclass of the decorator pattern. It contains all the static variables which
 * which help defining the subclasses.
 */


public abstract class Product {

  public final static int PRODUCT_TYPE_CLOUD = 0;
  public final static int PRODUCT_TYPE_VPN = 1;
  public final static int PRODUCT_TYPE_EMAIL = 2;

  public final static int PLAN_TYPE_STANDARD = 3;
  public final static int PLAN_TYPE_PREMIUM = 4;
  public final static int PLAN_TYPE_PRO = 5;

  public static int ID_MIN = 300000;
  public static int ID_MAX = 399999;
  public static int ID_COUNTER = ID_MIN;

  protected int productID;
  protected String productName;
  protected String productDescription;

  public abstract int getID();

  public abstract String getProductName();

  public abstract int getProductType();

  public abstract double getPlanRate();

  public abstract String getProductDescription();

  public abstract void setProductDescription(String productDescription);

  public abstract String getPlanName();

  public abstract int getPlanType();

  public abstract String getPlanDescription();

  @Override
  public String toString() {
    String str = "";

    str += "ID: " + Integer.valueOf(this.getID()).toString() + "\n";
    str += "Product name: " + this.getProductName() + "\n";
    str += "Product Description: " + this.getPlanDescription() + "\n";

    return str;
  }
}


