package core;

/**
 * Created by Jakub Gawrylkowicz 1326002
 *
 * Interface for all the subscriptions states.
 * The variables for the implementation classes.
 */
public interface SubscriptionState {

  String STATE_ACTIVE = "active";
  String STATE_INACTIVE = "inactive";
  String STATE_SUSPENDED = "suspended";

  String getDescription();

}
