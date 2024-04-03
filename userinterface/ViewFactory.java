package userinterface;

import impresario.IModel;

//==============================================================================
public class ViewFactory {

	public static View createView(String viewName, IModel model) {
		switch(viewName) {
			case "ClerkView":
				return new ClerkView(model);
			case "ColorCollectionView":
				return new ColorCollectionView(model);
			case "ModifyColorView":
				return new ModifyColorView(model);
			case "DeleteColorView":
				return new DeleteColorView(model);
			case "AddColorView":
				return new AddColorView(model);
			default:
				return null;
		}
	}


	/*
	public static Vector createVectorView(String viewName, IModel model)
	{
		if(viewName.equals("SOME VIEW NAME") == true)
		{
			//return [A NEW VECTOR VIEW OF THAT NAME TYPE]
		}
		else
			return null;
	}
	*/

}
