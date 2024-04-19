// specify the package
package model;

// system imports
import javafx.stage.Stage;
import model.ArticleType;
import model.Transaction;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.Properties;
import java.util.Vector;

// project imports
import event.Event;
import exception.InvalidPrimaryKeyException;

import userinterface.View;
import userinterface.ViewFactory;

public class ModifyInventoryTransaction extends Transaction{
    private String transactionStatusMessage = "";

    private String barcode;

    public ModifyInventoryTransaction() throws Exception {
        super();
    }

    protected void setDependencies() {
        dependencies = new Properties();
		dependencies.setProperty("DoModifyInventory", "TransactionError");
        dependencies.setProperty("CancelScanBarcode", "CancelTransaction");

		myRegistry.setDependencies(dependencies);
    }

    protected Scene createView() {
        Scene currentScene = myViews.get("ScanBarcodeView");

		if (currentScene == null) {
			// create our new view
			View newView = ViewFactory.createView("ScanBarcodeView", this);
			currentScene = new Scene(newView);
			myViews.put("ScanBarcodeView", currentScene);

			return currentScene;
		}
		else {
			return currentScene;
		}
    }

    public Object getState(String key) {
        switch (key) {
            case "TransactionStatus":
                return transactionStatusMessage;
            default:
                System.err.println("ModifyInventoryTransaction: invalid key for getState: "+key);
                break;
		}
		return null;
	}

    public void stateChangeRequest(String key, Object value) {
        switch(key) {
            case "DoYourJob":
                doYourJob();
                break;
            case "CancelModifyInventory":
                swapToView(createView());
                break;
            case "DoScanBarcode":
                processTransaction((Properties)value);
                break;
            default:
                System.err.println("ModifyInventoryTransaction: invalid key for stateChangeRequest " + key);
        }
		myRegistry.updateSubscribers(key, this);
    }
    
    public void processTransaction(Properties props) {
        barcode = props.getProperty("barcode");

		try {
            Inventory item = new Inventory(barcode);
            item.update();
            transactionStatusMessage = (String)item.getState("UpdateStatusMessage");
        }
        catch (Exception exc) {
            System.err.println(exc);
        }
	}
}
