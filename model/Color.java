package model;

// system imports
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import exception.InvalidPrimaryKeyException;
import database.*;

public class Color extends EntityBase
{
	private static final String myTableName = "Color";

	protected Properties dependencies;

	private String updateStatusMessage = "";

	public Color(String id) throws InvalidPrimaryKeyException
	{
		super(myTableName);

		setDependencies();
		String query = "SELECT * FROM " + myTableName + " WHERE (id = " + id + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null)
		{
			int size = allDataRetrieved.size();

			if (size != 1)
			{
				throw new InvalidPrimaryKeyException("Multiple colors matching id : "
					+ id + " found.");
			}
			else
			{
				Properties retrievedAccountData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedAccountData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedAccountData.getProperty(nextKey);

					if (nextValue != null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}

			}
		}
		else
		{
			throw new InvalidPrimaryKeyException("No color matching id : "
				+ id + " found.");
		}
	}

	public Color(Properties props)
	{
		super(myTableName);

		setDependencies();
		persistentState = new Properties();
		Enumeration allKeys = props.propertyNames();
		while (allKeys.hasMoreElements() == true)
		{
			String nextKey = (String)allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);

			if (nextValue != null)
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}

	private void setDependencies()
	{
		dependencies = new Properties();
		myRegistry.setDependencies(dependencies);
	}

	public Object getState(String key)
	{
		if (key.equals("UpdateStatusMessage") == true)
			return updateStatusMessage;

		return persistentState.getProperty(key);
	}

	public void stateChangeRequest(String key, Object value)
	{
		myRegistry.updateSubscribers(key, this);
	}

	public void update()
	{
		updateStateInDatabase();
	}

	private void updateStateInDatabase() 
	{
		try
		{
			if (persistentState.getProperty("id") != null)
			{
				// update
				Properties whereClause = new Properties();
				whereClause.setProperty("id", persistentState.getProperty("id"));
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Color updated successfully in database!";
			}
			else
			{
				// insert
				Integer accountNumber = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("id", "" + accountNumber.intValue());
				updateStatusMessage = "New color inserted successfully in database!";
			}
		}
		catch (SQLException ex)
		{
			updateStatusMessage = "Error while inserting color in database!";
		}
	}

	public void display()
	{
		System.out.println(this.toString());
	}

	public String toString()
	{
		return persistentState.toString();
	}

	public Vector<String> getEntryListView()
	{
		Vector<String> v = new Vector<String>();

        v.addElement(persistentState.getProperty("id"));
		v.addElement(persistentState.getProperty("description"));
        v.addElement(persistentState.getProperty("barcodePrefix"));
		v.addElement(persistentState.getProperty("alphaCode"));

		return v;
	}

	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}
}