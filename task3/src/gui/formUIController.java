package gui;


import static gui.MainUIController.isFastBill;

import client.FastBillClientFactory;
import client.FastBillController;
import client.MockupClientFactory;
import client.MockupController;
import core.Controller;
import core.Customer;
import core.Invoice;
import core.PaymentCreditCard;
import core.PaymentMethod;
import core.PaymentPayPal;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by David on 17-Jun-17.
 */
public class formUIController {


  @FXML
  private TextField titleTxt;
  @FXML
  private TextField firstNameTxt;
  @FXML
  private TextField lastNameTxt;
  @FXML
  private TextField firstAddressRowTxt;
  @FXML
  private TextField secondAddressRowTxt;
  @FXML
  private TextField postcodeTxt;
  @FXML
  private TextField cityTxt;
  @FXML
  private TextField countryTxt;
  @FXML
  private TextField landlineTxt;
  @FXML
  private TextField cellTxt;
  @FXML
  private TextField faxTxt;
  @FXML
  private TextField emailTxt;
  @FXML
  private TextField websiteTxt;
  @FXML
  private TextField paymentTxt;
  @FXML
  private Button addCustomerSubmitBtn;
  @FXML
      private Label exceptionTxt;

  @FXML
  private TextField customerIDTxt;
  @FXML
  private TextField subscriptionIDTxt;
  @FXML
  private TextField chargedTxt;
  @FXML
  private TextField yearTxt;
  @FXML
  private TextField monthTxt, cardNumberTxt, cardExpDateTxt, cardExpDateTxt1, cardSecureTxt, payPalAccountTxt;
  @FXML
  private Label invoiceLabel;
  @FXML
  private Label subExpDateLabel;
  @FXML
  private Label minusLabel;
  @FXML
  private Label subPaidLabel, subPaidLabel1;
  @FXML
  private ChoiceBox isPaidBox;
  @FXML
  private Button addInvoiceSubmitBtn;
  @FXML
  private Label customerLabel;

  private static final int INVOICE_BTN = 0;
  private static final int CUSTOMER_BTN = 1;


  int addType = MainUIController.getAddType();


  public void initialize() {
    System.out.println("initialize() in form controller call");


    if(isFastBill){
      addInvoiceSubmitBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {


          FastBillClientFactory factory = new FastBillClientFactory();
          FastBillController controller = factory.createFastBillController();

          int customerId = Integer.valueOf(customerIDTxt.getText());
          double price = Double.valueOf(subscriptionIDTxt.getText());
          controller.createInvoice(controller.getCustomerByID(customerId), price);


        }
      });

      addCustomerSubmitBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

          /*Controller controller = new Controller();

          Customer customer = new Customer();
          controller.createFastbillCustomer(getCustomerData(customer));*/

        }
      });

    }else{

      addInvoiceSubmitBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            if(customerIDTxt.getText().isEmpty() || subscriptionIDTxt.getText().isEmpty()
                || chargedTxt.getText().isEmpty() || yearTxt.getText().isEmpty() || monthTxt.getText().isEmpty()){

        /*Text t = new Text();
        t.setFill(Color.RED);*/
              //t.setText("First Name and Last Name are required!");
              exceptionTxt.setText("All fields are required!");
            }
            else {
              MockupClientFactory factory = new MockupClientFactory();
              MockupController controller = factory.createMockupController();

              ObservableList<String> isPaidList = FXCollections.observableArrayList("Yes", "No");
              isPaidBox.setValue("No");
              isPaidBox.setItems(isPaidList);

              int customerID = Integer.valueOf(customerIDTxt.getText());
              int subID = Integer.valueOf(subscriptionIDTxt.getText());
              double chargedAmount = Double.valueOf(chargedTxt.getText());
              int year = Integer.valueOf(yearTxt.getText());
              int month = Integer.valueOf(monthTxt.getText());
              YearMonth yearMonth = YearMonth.of(year,month);


              String cardNumber = null;
              int cardExpiryYear = 0;
              int cardExpiryMonth = 0;
              int secureCode = 0;

              if(!cardNumberTxt.getText().isEmpty() || !cardExpDateTxt1.getText().isEmpty() ||
                  !cardExpDateTxt.getText().isEmpty() || !cardSecureTxt.getText().isEmpty()){

                cardNumber = cardNumberTxt.getText();

                cardExpiryYear = Integer.valueOf(cardExpDateTxt.getText());
                cardExpiryMonth = Integer.valueOf(cardExpDateTxt1.getText());
                secureCode = Integer.valueOf(cardSecureTxt.getText());

              }


              String payPalAccount = payPalAccountTxt.getText();
              Customer customer = controller.initController().getCustomerByLocalID(customerID);


              if(cardExpDateTxt.getText().isEmpty() || cardExpDateTxt1.getText().isEmpty()
                  || cardSecureTxt.getText().isEmpty() || cardNumberTxt.getText().isEmpty()
                  && !payPalAccountTxt.getText().isEmpty()){

                PaymentPayPal paymentMethod = new PaymentPayPal(payPalAccount);
                customer.addInvoice(subID, chargedAmount,yearMonth, paymentMethod);

              }
              if(!cardExpDateTxt.getText().isEmpty() && !cardExpDateTxt1.getText().isEmpty()
                  && !cardSecureTxt.getText().isEmpty() && !cardNumberTxt.getText().isEmpty()
                  && payPalAccountTxt.getText().isEmpty()){

                PaymentCreditCard paymentMethod = new PaymentCreditCard(cardNumber,
                    YearMonth.of(cardExpiryYear,cardExpiryMonth), secureCode);

                customer.addInvoice(subID, chargedAmount, yearMonth, paymentMethod);

              }else{
                System.out.println("No fields matched their purpose!");
              }

              try {
                showForm(INVOICE_BTN);

              } catch (Exception e) {
                e.printStackTrace();
              }
            }



          }

      });

      addCustomerSubmitBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

          MockupClientFactory factory = new MockupClientFactory();
          MockupController controller = factory.createMockupController();
          Customer customer = new Customer();


          if(firstNameTxt.getText().isEmpty() || lastNameTxt.getText().isEmpty()){

            exceptionTxt.setText("First Name and Last Name are required!");

          }
          else{


            getCustomerData(customer);

            //TODO FIX THE DOUBLE

            controller.initController().createCustomer(customer);


            try {
              showForm(CUSTOMER_BTN);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      });

    }




    switch(addType){
      case 1:
        System.out.println("Init add customer call");
        initAddCust();
        break;
      case 2:
        System.out.println("Init add invoice call");
        initAddInvoice();
        break;
      default:
        break;
    }

  }

  private void initAddCust(){

    customerIDTxt.setManaged(false);
    subscriptionIDTxt.setManaged(false);
    chargedTxt.setManaged(false);
    yearTxt.setManaged(false);
    monthTxt.setManaged(false);
    invoiceLabel.setManaged(false);
    subExpDateLabel.setManaged(false);
    minusLabel.setManaged(false);
    subPaidLabel1.setManaged(false);
    subPaidLabel.setManaged(false);

    isPaidBox.setManaged(false);
    addInvoiceSubmitBtn.setManaged(false);
    cardNumberTxt.setManaged(false);
    cardExpDateTxt.setManaged(false);
    cardExpDateTxt1.setManaged(false);
    cardSecureTxt.setManaged(false);
    payPalAccountTxt.setManaged(false);

  }

  private void initAddInvoice(){

    customerLabel.setManaged(false);

    titleTxt.setManaged(false);
    firstNameTxt.setManaged(false);
    lastNameTxt.setManaged(false);
    firstAddressRowTxt.setManaged(false);
    secondAddressRowTxt.setManaged(false);
    postcodeTxt.setManaged(false);
    cityTxt.setManaged(false);
    countryTxt.setManaged(false);
    landlineTxt.setManaged(false);
    cellTxt.setManaged(false);
    faxTxt.setManaged(false);
    emailTxt.setManaged(false);
    websiteTxt.setManaged(false);
    paymentTxt.setManaged(false);
    addCustomerSubmitBtn.setManaged(false);

  }

  private Customer getCustomerData(Customer customer){



    String title = titleTxt.getText();
    String firstName = firstNameTxt.getText();
    String lastName = lastNameTxt.getText();
    String firstAddressRow = firstAddressRowTxt.getText();
    String secondAddressRow = secondAddressRowTxt.getText();
    String postcode = postcodeTxt.getText();
    String city = cityTxt.getText();
    String country = countryTxt.getText();
    String landline = landlineTxt.getText();
    String cell = cellTxt.getText();
    String fax = faxTxt.getText();
    String email = emailTxt.getText();
    String website = websiteTxt.getText();
    String payment = paymentTxt.getText();


    customer.setTitle(title);
    customer.setForename(firstName);
    customer.setSurname(lastName);
    customer.setFirstAddressRow(firstAddressRow);
    customer.setSecondAddressRow(secondAddressRow);
    customer.setPostcode(postcode);
    customer.setLocation(city);
    customer.setCountry(country);
    customer.setTelephoneNumber(landline);
    customer.setCellNumber(cell);
    customer.setFaxNumber(fax);
    customer.setEmail(email);
    customer.setWebsiteAddress(website);
    customer.setPaymentCurrency(payment);

    return customer;

  }


  private void showForm(int type) throws Exception{
    Stage stageForm = null;
    switch(type){
      case formUIController.INVOICE_BTN:
        stageForm = (Stage) addInvoiceSubmitBtn.getScene().getWindow();
        break;

      case formUIController.CUSTOMER_BTN:
        stageForm = (Stage) addCustomerSubmitBtn.getScene().getWindow();
        break;

      default:
        break;
    }

    FXMLLoader loader2 = new FXMLLoader();
    Parent client = loader2.load(getClass().getResource("fxml/Client.fxml"));

    Scene scene2 = new Scene(client);
    stageForm.setScene(scene2);
    stageForm.centerOnScreen();
    stageForm.show();

  }
}
