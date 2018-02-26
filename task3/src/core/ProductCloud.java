package core;

/**
 * Created by
 * Jakub Gawrylkowicz 1326002
 * Konrad Laaber 1301118
 *
 * Implementation of the decorater pattern.
 */

public class ProductCloud extends Product {

  public ProductCloud(String productName) {
    this.productName = productName;
    this.productID = ID_COUNTER++;
  }

  @Override
  public int getID() {
    return this.productID;
  }

  @Override
  public String getProductName() {
    return this.productName;
  }

  @Override
  public int getProductType() {
    return PRODUCT_TYPE_CLOUD;
  }

  @Override
  public double getPlanRate() {
    return 0;
  }

  @Override
  public String getProductDescription() {
    return this.productDescription;
  }

  @Override
  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  @Override
  public int getPlanType() {
    return 0;
  }

  @Override
  public String getPlanName() {
    return null;
  }

  @Override
  public String getPlanDescription() {
    return null;
  }
}
