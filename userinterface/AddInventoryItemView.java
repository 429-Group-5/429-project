//Specify package
package userinterface;

import java.util.Observable;
// system imports
import java.util.Properties;

import javafx.event.Event;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;
import model.InventoryItem;
import javafx.scene.Scene;
import javafx.scene.input.*;

// project imports
import impresario.IModel;

/*ADDINVENTORYITEMVIEW CLASS
 * Class contains view for adding InventoryItem Objects
 */
//======================================================================
public class AddInventoryItemView extends View {

    //error message
    private MessageView statusLog;

    //GUI Textfield components
    protected TextField barcodeField;
    protected TextField genderField;
    protected TextField articleField;
    protected TextField colorField;
    protected TextField color2Field;
    protected TextField sizeField;
    protected TextField brandField;
    protected TextField notesField;
    protected TextField fnameField;
    protected TextField lnameField;
    protected TextField phoneField;
    protected TextField emailField;

    /*CONSTRUCTOR
     * Takes model Object
     */
    public AddInventoryItemView(IModel invenItem){

        super(invenItem, "AddInventoryItemView");

        //Create container for view
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);

		container.setPadding(new Insets(15, 5, 5, 5));

		// create a Node (Text) for showing the title
		container.getChildren().add(createTitle());

		// create a Node (GridPane) for showing data entry fields
		container.getChildren().add(createFormContents());

		// Error message area
		container.getChildren().add(createStatusLog("                          "));

		getChildren().add(container);

		// STEP 0: Be sure you tell your model what keys you are interested in
		// myModel.subscribe("LoginError", this);
	}//END CONSTRUCTOR================================================

    /*createTitle-------------------------------------------------------
     * create Title text
     */
    private Node createTitle() {
		Text titleText = new Text("Choose Transaction:");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		
		return titleText;
	}//End createTitle--------------------------------------------------

    /*createFormContents-------------------------------------------------
     * creates actual form for data entry
     * 
     */
    private VBox createFormContents() {
		
        VBox form = new VBox();

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);

        //-----Barcode Label and Field-----
        Text barcodeLabel = new Text("Barcode: ");
        barcodeLabel.setWrappingWidth(150);
        barcodeLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(barcodeLabel, 0, 0);

        barcodeField = new TextField();
        barcodeField.setEditable(true);
        grid.add(barcodeField, 0, 1);

        //-----Gender Label and Field-----
        Text genderLabel = new Text("Gender: ");
        genderLabel.setWrappingWidth(150);
        genderLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(genderLabel, 1, 0);
        
        genderField = new TextField();
        genderField.setEditable(true);
        grid.add(genderField, 1, 1);

        //-----Article Label and Field-----
        Text articleLabel = new Text("Article Type: ");
        articleLabel.setWrappingWidth(150);
        articleLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(articleLabel, 2, 0);

        articleField = new TextField();
        articleField.setEditable(true);
        grid.add(articleField, 2, 1);

        //-----Primary Color Label and Field-----
        Text colorLabel = new Text("Primary Color: ");
        colorLabel.setWrappingWidth(150);
        colorLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(colorLabel, 3, 0);

        colorField = new TextField();
        colorField.setEditable(true);
        grid.add(colorField, 3, 1);

        //------Color2 Label and Field-----
        Text color2Label = new Text("Secondary Color: ");
        color2Label.setWrappingWidth(150);
        color2Label.setTextAlignment(TextAlignment.RIGHT);
        grid.add(color2Label, 4, 0);

        color2Field = new TextField();
        color2Field.setEditable(true);
        grid.add(color2Field, 4, 1);

        //-----Size Label and Field----
        Text sizeLabel = new Text("Size: ");
        sizeLabel.setWrappingWidth(150);
        sizeLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(sizeLabel, 5, 0);

        sizeField = new TextField();
        sizeField.setEditable(true);
        grid.add(sizeField, 5, 1);

        //-----Brand Name Label and Field-----
        Text brandLabel = new Text("Brand: ");
        brandLabel.setWrappingWidth(150);
        brandLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(brandLabel, 6, 0);

        brandField = new TextField();
        brandField.setEditable(true);
        grid.add(brandField, 6, 1);

        //-----Notes Label and Field-----
        Text notesLabel = new Text("Notes: ");
        notesLabel.setWrappingWidth(150);
        notesLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(notesLabel, 7, 1);

        notesField = new TextField();
        notesField.setEditable(true);
        grid.add(notesField, 7, 1);

        //-----Donor First Name Label and Field-----
        Text fnameLabel = new Text("Donor First name: ");
        fnameLabel.setWrappingWidth(150);
        fnameLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(fnameLabel, 8, 0);
        
        fnameField = new TextField();
        fnameField.setEditable(true);
        grid.add(fnameField, 8, 1);

        //-----Donor Last Name Label and Field------
        Text lnameLabel = new Text("Donor Last Name: ");
        lnameLabel.setWrappingWidth(150);
        lnameLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(lnameLabel, 9, 0);
        
        lnameField = new TextField();
        lnameField.setEditable(true);
        grid.add(lnameField, 9, 1);

        //-----Donor Phone Number Label and Field------
        Text phoneLabel = new Text("Phone Number: ");
        phoneLabel.setWrappingWidth(150);
        phoneLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(phoneLabel, 10, 0);

        phoneField = new TextField();
        phoneField.setEditable(true);  
        grid.add(phoneField, 10, 1);

        //-----Donor Email Label and Field-----
        Text emailLabel = new Text("Email: ");
        emailLabel.setWrappingWidth(150);
        emailLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(emailLabel, 11, 0);

        emailField = new TextField();
        emailField.setEditable(true);
        grid.add(emailField, 11, 1);

        /*barcodeField upon losing focus:
         * -verify user input
         * -parse gender, article id, and primary color
         * -all other textfields will become editable
         */
        barcodeField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {               
                //String from barcode field
                String barcodeTempString = barcodeField.getText();
                
                /*Try block will check if barcode was numeric. If so:
                 * -Verify input is 8 digits
                 * -Parse input for gender, article, color
                 */
                try {
                    int barcodeInt = Integer.parseInt(barcodeTempString);

                    //Check that input is 8 digits
                    if (barcodeTempString.length() != 8){
                        barcodeField.setText("Barcode was incorrect length. Please try again");                    
                    } else {
                        //parse barcode for info
                        char[] barcodeArray = barcodeTempString.toCharArray();

                        //Determine gender from first digit
                        char genderChar = barcodeArray[0];
                        if (genderChar == '0'){
                            genderField.setText("M");
                            genderField.setEditable(true);
                        } else
                        if (genderChar == '1'){
                            genderField.setText("W");
                            genderField.setEditable(true);
                        } else {
                            genderField.setText("Barcode was incorrect");
                            genderField.setEditable(false);
                        };

                        

                    }
                } catch (NumberFormatException exc){
                    barcodeField.setText("Barcode should be numerical");
                }//End trycatch block                
            }//End change            
        });//End focusProperty

        //====BUTTONS=====
        //Setup separate Hbox for submit and cancel button
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        //-----Submit Button------
        Button subButton = new Button("Submit");
        subButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e){
                processSubAction(e);
            }
        });
        buttons.getChildren().add(subButton);

        //-----Cancel Button-----
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e){

                //CLERK REQUIRES MATCHING STATE CHANGE
                myModel.stateChangeRequest(getAccessibleHelp(), e);
            }
        });
        buttons.getChildren().add(cancelButton);

        //Add form and buttons
        vbox.getChildren().add(grid);
        vbox.getChildren().add(buttons);        

        return form;
    
    }//End createFormContents------------------------------------------

    /*processSubAction---------------------
     * Handles what happens upon pressing submit
     *  WIP
     */
    public void processSubAction(Event e){
        //Validate user input

        //Parse barcode for article, gender, primary color

        //Convert input to strings
        String barcodeString = barcodeField.getText();
        String genderString = genderField.getText();
        String articleString = articleField.getText();
        String colorString = colorField.getText();
        String color2String = colorField.getText();
        String sizeString = sizeField.getText();
        String brandString = brandField.getText();
        String notesString = notesField.getText();
        String fnameString = fnameField.getText();
        String lnameString = lnameField.getText();
        String phoneString = phoneField.getText();
        String emailString = emailField.getText();

        //Create properties and keys
        Properties insertProperties = new Properties();
        insertProperties.setProperty("barcode", barcodeString);
        insertProperties.setProperty("gender", genderString);
        insertProperties.setProperty("size", sizeString);
        insertProperties.setProperty("articleTypeId", articleString);
        insertProperties.setProperty("color1Id", colorString);
        insertProperties.setProperty("color2Id", color2String);
        insertProperties.setProperty("brand", brandString);
        insertProperties.setProperty("notes", notesString);
        insertProperties.setProperty("donorLastName", lnameString);
        insertProperties.setProperty("donorFirstName", fnameString);
        insertProperties.setProperty("donorPhone", phoneString);
        insertProperties.setProperty("donorEmail", emailString);

        //Call Clerk stateChangeRequest Method to create InventoryItem
        myModel.stateChangeRequest("AddInventoryItem", insertProperties);

    }

    // Create the status log field
	//-------------------------------------------------------------
	private MessageView createStatusLog(String initialMessage) {
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

    @Override
    public void updateState(String key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }
}//END CLASS=============================================================
