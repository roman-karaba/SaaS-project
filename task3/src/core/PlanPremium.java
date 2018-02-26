package core;

/**
 * Created by
 * Jakub Gawrylkowicz 1326002
 * Konrad Laaber 1301118
 *
 * Implementation of the decorater pattern.
 */

public class PlanPremium extends PlanDecorator {

  public PlanPremium(Product product, String planName, String planDescription, double planRate) {
    super(product, planName, planDescription, planRate);
  }


  @Override
  public int getID() {
    return this.product.getID();
  }

  @Override
  public String getProductName() {
    return this.product.getProductName();
  }

  @Override
  public String getPlanName() {
    return this.planName;
  }

  @Override
  public int getProductType() {
    return this.product.getProductType();
  }

  @Override
  public int getPlanType() {
    return PLAN_TYPE_PREMIUM;
  }

  @Override
  public String getProductDescription() {
    return this.product.getProductDescription();
  }

  @Override
  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  @Override
  public String getPlanDescription() {
    return this.planDescription;
  }

  @Override
  public double getPlanRate() {
    return this.planRate;
  }
}
