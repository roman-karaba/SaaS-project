package core;

/**
 * Created by
 * Jakub Gawrylkowicz 1326002
 * Konrad Laaber 1301118
 *
 * Implementation of the decorater pattern.
 * The subclass of Product to extend its functionality ny adding the plans
 */

public abstract class PlanDecorator extends Product {

  protected Product product;
  protected String planName;
  protected String planDescription;
  protected double planRate;

  public PlanDecorator(Product product, String planName, String planDescription, double planRate) {
    this.product = product;
    this.planName = planName;
    this.planDescription = planDescription;
    this.planRate = planRate;
  }

  public abstract String getPlanName();
  public abstract int getPlanType();
  public abstract String getPlanDescription();
  public abstract double getPlanRate();





}
