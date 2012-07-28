package ca.liquidlabs.addressbook;

import info.itsalif.addressbook.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AddressBookActivity extends Activity {
	
	private List<HashMap<String, String>> addressList;
	
	private SimpleAdapter adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main);
        
        // update the current Activity
        ActivityHelper.setActivity(this);
        this.initAddressBookData();
        this.initListView();
    }
    
    /**
     * Initialize the Address Book Data with some values
     */
    public void initAddressBookData() {
    	this.addressList = new ArrayList<HashMap<String, String>>();
    	
    	HashMap<String, String> map;
    	
    	map = new HashMap<String, String>();    	
    	map.put("name", "Aaron");
    	map.put("phone", "416-000-0000");
    	map.put("email", "test@gmail.com");
    	
    	this.addressList.add(map);
    	
    	
    	map = new HashMap<String, String>();    	
    	map.put("name", "Abdullah Rubiyath");
    	map.put("phone", "647-111-1111");
    	map.put("email", "test@gmail.com");
    	
    	this.addressList.add(map);
    	
    	
    	map = new HashMap<String, String>();    	
    	map.put("name", "Asim D");
    	map.put("phone", "647-111-1111");
    	map.put("email", "test@gmail.com");
    	
    	this.addressList.add(map);
    	
    	
    	map = new HashMap<String, String>();    	
    	map.put("name", "Bryan Jones");
    	map.put("phone", "905-111-1111");
    	map.put("email", "test@gmail.com");
    	
    	this.addressList.add(map);
    	
    	
    	map = new HashMap<String, String>();    	
    	map.put("name", "Hossain Khan");
    	map.put("phone", "647-000-0000");
    	map.put("email", "test@gmail.com");
    	
    	this.addressList.add(map);
    	

    	map = new HashMap<String, String>();    	
    	map.put("name", "Vikram");
    	map.put("phone", "647-111-1111");
    	map.put("email", "test@gmail.com");
    	
    	this.addressList.add(map);      	
    }
    
        
    /**
     * Initializes the List View
     */
    public void initListView() 
    {   
    	/* get the list view from the layout */
    	ListView listView = (ListView) super.findViewById(R.id.address_book_listview);
    	
    	/* create an adapter */
    	this.adapter = new SimpleAddressBookAdapter(
    			listView.getContext(), 
    			this.addressList, 
    			R.layout.address_book_list_item,
    			new String[] { "name" },
    			new int[] { R.id.address_book_item_name }
    		);
    	
    	// set the list's adapter
    	listView.setAdapter(this.adapter);
    }
}