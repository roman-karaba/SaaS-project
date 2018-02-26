package gui;

import client.FastBillClientFactory;
import client.FastBillController;
import client.MockupClientFactory;
import client.MockupController;
import core.Controller;
import core.Customer;
import core.Invoice;
import java.io.IOException;
import java.util.ArrayList;

import core.Product;
import core.Subscription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static gui.RootUIController.type;

/**
 * Created by David on 15/06/2017.
 */
public class MainUIController {


    @FXML
    private Button testBtn;
    @FXML
    private Button addNewCustomerBtn;
    @FXML
    private Button addNewInvoiceBtn;
    @FXML
    private Button getAllUnpaidInvoicesBtn;

    @FXML
    private ListView<String> customerArea;
    @FXML
    private ListView<String> subscriptionArea;
    @FXML
    private ListView<String> notSoldProductArea;
    @FXML
    private ListView<String> soldProductArea;
    @FXML
    private ListView<String> customerDash;
    @FXML
    private ListView<String> subDash;
    @FXML
    private ListView<String> notSoldDash;
    @FXML
    private ListView<String> soldDash;
    @FXML
    private TextField unpaidCustomerTxt;

    public static int addType = 0;
    public static boolean isFastBill = false;



    public void initialize() {



        System.out.println("initialize() call");

        switch(type){
            case RootUIController.FASTBILL:
                System.out.println("Init fastbill call");
                initFastBill();
                break;
            case RootUIController.MOCKUP:
                System.out.println("Init mockup call");
                initMockUp();
                break;
            default:
                break;
        }

    }

    private void initFastBill(){




      isFastBill = true;
      FastBillClientFactory factory = new FastBillClientFactory();
      FastBillController controller = factory.createFastBillController();
      controller.getFastBillData();



      ArrayList<Customer> customerList = controller.getCustomerList();
      System.out.println(controller.toString());
      ObservableList<String> customers = FXCollections.observableArrayList();

      for (Customer customer : customerList) {
        customers.add(customer.toString());
        /*if (customer.getAllInvoices() != null) {
          for (Invoice invoice : customer.getAllInvoices()) {
            invoices.add(invoice.toString());
          }
        } else {
          System.out.println("Customer has no invoices");
        }*/
      }
      customerArea.setItems(customers);
      customerDash.setItems(customers);




      addNewCustomerBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          addType = 1;
          setAddType(addType);
          try{
            showForm();
          } catch (Exception e){
            e.printStackTrace();
          }
        }
      });

      addNewInvoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          addType = 2;
          setAddType(addType);
          try{
            showForm();
          } catch (Exception e){
            e.printStackTrace();
          }
        }
      });



    }



    private void initMockUp(){

        //isFastBill = false;





        MockupClientFactory factory = new MockupClientFactory();
        MockupController controller = factory.createMockupController();
        Customer customerObj = new Customer();

        controller.getCustomerList();
        TestData.generateTestData(controller.initController());

        try{

            ArrayList<Customer> customerList = controller.getCustomerList();
            ArrayList<Subscription> subscriptionList = controller.getSubscriptionList();
            ArrayList<Product> notSoldProductList = controller.getNotSoldProductList();
            ArrayList<Product> soldProductList = controller.getSoldProductList();
            //ArrayList<Invoice> unpaidInvoicesList = customerObj.getUnpaidInvoices();

          //ArrayList<Invoice> invoiceList = customerObj.get



            ObservableList<String> customers = FXCollections.observableArrayList();
            ObservableList<String> subscriptions = FXCollections.observableArrayList();
            ObservableList<String> notSoldProducts = FXCollections.observableArrayList();
            ObservableList<String> soldProducts = FXCollections.observableArrayList();
            ObservableList<String> invoices = FXCollections.observableArrayList();
            ObservableList<String> unpaidInvoices = FXCollections.observableArrayList();



          for (Customer customer : customerList) {
            customers.add(customer.toString());
            if (customer.getAllInvoices() != null) {
              for (Invoice invoice : customer.getAllInvoices()) {

                invoices.add(invoice.toString());
              }
            }else {
              System.out.println("Customer has no invoices");
            }
            if (customer.getUnpaidInvoices() != null) {
              System.out.println(customer.getUnpaidInvoices());
              for (Invoice invoice : customer.getUnpaidInvoices()) {

                unpaidInvoices.add(invoice.toString());
              }

            } else {
              System.out.println("Customer has no unpaid invoices");
            }
          }
            for (Subscription subscription : subscriptionList) { subscriptions.add(subscription.toString()); }

            for (Product notSoldProduct : notSoldProductList) {

              notSoldProducts.add(notSoldProduct.toString() + "Status: Currently Not Sold");
            }
            for (Product soldProduct : soldProductList) {
              notSoldProducts.add(soldProduct.toString() + "Status: Currently Sold");
            }



            customerArea.setItems(customers);
            subscriptionArea.setItems(subscriptions);
            notSoldProductArea.setItems(notSoldProducts);

            customerDash.setItems(customers);
            subDash.setItems(subscriptions);
            notSoldDash.setItems(notSoldProducts);
            soldDash.setItems(invoices);
            soldProductArea.setItems(unpaidInvoices);



          testBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    testBtn.setText("MOCKUP BTN EVENT");
                    System.out.println(controller.toString());
                }

            });
        }catch(Exception e){
            System.out.println("Exception in initMockUp \n" + e.getMessage());
        }

      addNewCustomerBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          addType = 1;
          setAddType(addType);
          try{
            showForm();
          } catch (Exception e){
            e.printStackTrace();
          }
        }
      });

      addNewInvoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          addType = 2;
          setAddType(addType);
          try{
            showForm();
          } catch (Exception e){
            e.printStackTrace();
          }
        }
      });
      getAllUnpaidInvoicesBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          System.out.println("entered");
          Customer customer = new Customer();

          int customerID = Integer.valueOf(unpaidCustomerTxt.getText());
          customer.setLocalID(customerID);

          ObservableList<String> unpaidInvoicesCheck = FXCollections.observableArrayList();

          if (customer.getUnpaidInvoices() != null) {
            for (Invoice invoice : customer.getUnpaidInvoices()) {

              unpaidInvoicesCheck.add(invoice.toString());
            }

          } else {
            System.out.println("Customer has no unpaid invoices");
          }
          //customer.getUnpaidInvoices();
          System.out.println("went through");

        }
      });

    }

    /*@FXML
    private void onAddButtonClick(ActionEvent e) throws IOException {
      Stage stageForm;

      if(e.getSource()==addNewCustomerBtn) {
        addType = 1;
        setAddType(addType);
      }

      if(e.getSource()==addNewInvoiceBtn){
        addType = 2;
        setAddType(addType);
      }

      stageForm = (Stage) addNewCustomerBtn.getScene().getWindow();
      FXMLLoader loader2 = new FXMLLoader();
      Parent client = loader2.load(getClass().getResource("fxml/form.fxml"));

      Scene scene2 = new Scene(client);
      stageForm.setScene(scene2);
      stageForm.centerOnScreen();
      stageForm.show();

    }*/

    private void showForm() throws Exception{
      Stage stageForm;
      stageForm = (Stage) addNewCustomerBtn.getScene().getWindow();
      FXMLLoader loader2 = new FXMLLoader();
      Parent client = loader2.load(getClass().getResource("fxml/form.fxml"));

      Scene scene2 = new Scene(client);
      stageForm.setScene(scene2);
      stageForm.centerOnScreen();
      stageForm.show();

    }

    private void setAddType(int addType){
      this.addType = addType;
    }
    public static int getAddType(){return addType;}


}
