package core;

/**
 * Created by Jakub Gawrylkowicz 1326002
 */
public class ActiveSubscriptionState implements SubscriptionState {

  @Override
  public String getDescription() {
    return STATE_ACTIVE;
  }
}
