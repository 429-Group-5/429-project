// specify the package
package model;

import java.sql.SQLException;
// system imports
import java.util.Hashtable;
import java.util.Properties;
import java.util.Stack;

import javafx.stage.Stage;
import javafx.scene.Scene;

// project imports
import impresario.IModel;
import impresario.ISlideShow;
import impresario.IView;
import impresario.ModelRegistry;

import exception.InvalidPrimaryKeyException;
import exception.PasswordMismatchException;
import event.Event;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;

/** The class containing the Teller  for the ATM application */
//==============================================================
public class Clerk implements IView, IModel
// This class implements all these interfaces (and does NOT extend 'EntityBase')
// because it does NOT play the role of accessing the back-end database tables.
// It only plays a front-end role. 'EntityBase' objects play both roles.
{
	// For Impresario
	private Properties dependencies;
	private ModelRegistry myRegistry;

	// GUI Components
	private Hashtable<String, Scene> myViews;
	private Stage myStage;

	// constructor for this class
	//----------------------------------------------------------
	public Clerk()
	{
		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();

		// STEP 3.1: Create the Registry object - if you inherit from
		// EntityBase, this is done for you. Otherwise, you do it yourself
		myRegistry = new ModelRegistry("Clerk");
		if(myRegistry == null)
		{
			new Event(Event.getLeafLevelClassName(this), "Clerk",
				"Could not instantiate Registry", Event.ERROR);
		}

		// STEP 3.2: Be sure to set the dependencies correctly
		setDependencies();

		// Set up the initial view
		createAndShowClerkView();
	}

	//-----------------------------------------------------------------------------------
	private void setDependencies()
	{
		dependencies = new Properties();
		// dependencies.setProperty("Login", "LoginError");
		// dependencies.setProperty("Deposit", "TransactionError");
		// dependencies.setProperty("Withdraw", "TransactionError");
		// dependencies.setProperty("Transfer", "TransactionError");
		// dependencies.setProperty("BalanceInquiry", "TransactionError");
		// dependencies.setProperty("ImposeServiceCharge", "TransactionError");

		myRegistry.setDependencies(dependencies);
	}

	/**
	 * Method called from client to get the value of a particular field
	 * held by the objects encapsulated by this object.
	 *
	 * @param	key	Name of database column (field) for which the client wants the value
	 *
	 * @return	Value associated with the field
	 */
	//----------------------------------------------------------
	public Object getState(String key) {
		return "";
	}

	//----------------------------------------------------------------
	public void stateChangeRequest(String key, Object value) {
		switch (key) {
            case "AddArticleType":
            case "ModifyArticleType":
            case "DeleteArticleType":
            case "AddColor":
            case "ModifyColor":
            case "DeleteColor":
			case "AddInventory":
				createAndShowAddInventoryView();
				break;
			case "ModifyInventory":
			case "DeleteInventory":
			case "CheckoutInventory":
			case "ListAvailableInventory":
			case "ListCheckedOutInventory":
				doTransaction(key);
				break;
			case "CancelTransaction":
				createAndShowClerkView();
				break;
			// case "ColorSelected":
			// 	String colorId = ((Properties)value).getProperty("id");
			// 	handleColorSelected(colorId);
			// 	break;
            default:
                System.out.println("Invalid key.");
        }

		myRegistry.updateSubscribers(key, this);
	}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	public void updateState(String key, Object value) {
		stateChangeRequest(key, value);
	}

	public void doTransaction(String transactionType) {
		try {
			Transaction trans = TransactionFactory.createTransaction(transactionType);

			trans.subscribe("CancelTransaction", this);
			trans.stateChangeRequest("DoYourJob", "");
		}
		catch (Exception ex) {
			System.err.println(ex);
		}
	}

	private void handleColorCollection() {
		
	}

	private void createAndShowClerkView() {
		Scene currentScene = (Scene)myViews.get("ClerkView");

		if (currentScene == null) {
			View newView = ViewFactory.createView("ClerkView", this);
			currentScene = new Scene(newView);
			myViews.put("ClerkView", currentScene);
		}
				
		swapToView(currentScene);
	}

	private void createAndShowAddArticleTypeView() {
        System.out.println("add article type not implemented");
    }

    private void createAndShowSearchArticleTypeView() {
        System.out.println("search article type not implemented");
    }

    private void createAndShowAddColorView() {
        System.out.println("add color not implemented");
    }

    private void createAndShowAddInventoryView() {

    Scene currentScene = (Scene)myViews.get("AddInventoryItemView");

		if (currentScene == null){
			View newView = ViewFactory.createView("AddInventoryItemView", this);
			currentScene = new Scene(newView);
			myViews.put("AddInventoryItemView", currentScene);
		}

		swapToView(currentScene);
    }//End createAndShowAddInventoryView---------------------------------

    private void createAndShowScanBarcodeView() {
        System.out.println("scan barcode not implemented");
    }

    private void createAndShowCheckoutInventoryView() {
        System.out.println("checkout inventory item not implemented");
    }

    private void createAndShowListAvailableInventoryView() {
        System.out.println("list available inventory not implemented");
    }

    private void createAndShowListCheckedOutInventoryView() {
        System.out.println("list checked out inventory not implemented");
    }


	/** Register objects to receive state updates. */
	//----------------------------------------------------------
	public void subscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager[" + myTableName + "].subscribe");
		// forward to our registry
		myRegistry.subscribe(key, subscriber);
	}

	/** Unregister previously registered objects. */
	//----------------------------------------------------------
	public void unSubscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager.unSubscribe");
		// forward to our registry
		myRegistry.unSubscribe(key, subscriber);
	}



	//-----------------------------------------------------------------------------
	public void swapToView(Scene newScene) {
		if (newScene == null)
		{
			System.out.println("Clerk.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView",
				"Missing view for display ", Event.ERROR);
			return;
		}

		myStage.setScene(newScene);
		myStage.sizeToScene();
		
			
		//Place in center
		WindowPosition.placeCenter(myStage);

	}

}