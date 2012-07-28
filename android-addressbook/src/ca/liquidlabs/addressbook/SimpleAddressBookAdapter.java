package ca.liquidlabs.addressbook;

import info.itsalif.addressbook.R;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class SimpleAddressBookAdapter extends SimpleAdapter {

	private static List<HashMap<String, String>> listMap;	
	private static Context context; 
	private static int resource;
	
	protected static int[] resourceList;
	protected static String[] fromList;
	
	protected static int callResource = R.id.address_book_item_call;
	protected static int emailResource = R.id.address_book_item_email;

	
	private static class ViewHolder
	{
		TextView[] textView;		
		ImageView phoneIcon;
		ImageView emailIcon;
		
		int position;
	}
	
	

	public SimpleAddressBookAdapter(Context context,
			List<HashMap<String, String>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		
		// save the ArrayList and context for later usage
		SimpleAddressBookAdapter.listMap = data;	
		SimpleAddressBookAdapter.context = context;
		SimpleAddressBookAdapter.resource = resource;
		
		resourceList = to;
		fromList = from;
	}
	

	/**
	 * Generating View of each row (optimized as per Google UI Guideline)
	 * @param position The Position/index of the list Item
	 * @param convertView the View
	 * @param ViewGroup parent of the current View
	 * @return View The View to be displayed to the user
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// declare it final so that it could be accessed from the inner class
		final ViewHolder holder;
		
		if (convertView == null) 
		{
			LayoutInflater mInflater = (LayoutInflater) SimpleAddressBookAdapter.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(SimpleAddressBookAdapter.resource, parent, false);
						
			holder = new ViewHolder();	
			holder.textView = new TextView[fromList.length];
			
			// get the textview's from the convertView
			for (int i = 0; i < fromList.length; i++) {
				holder.textView[i] = (TextView) convertView.findViewById(resourceList[i]);
			}
			
			// get the phoneIcon and emailIcon as well from convertView
			holder.phoneIcon = (ImageView) convertView.findViewById(R.id.address_book_item_call);
			holder.emailIcon = (ImageView) convertView.findViewById(R.id.address_book_item_email);
			
			// add a listener for phone call
			holder.phoneIcon.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String phone = SimpleAddressBookAdapter.listMap.get(holder.position).get("phone");
					ActivityHelper.startActivity(ActivityHelper.PHONE_CALL, phone);
				}
				
			});
			
			// add listener for email 
			holder.emailIcon.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String email = SimpleAddressBookAdapter.listMap.get(holder.position).get("email");
					ActivityHelper.startActivity(ActivityHelper.EMAIL, email);
				}
				
			});
			
			convertView.setTag(holder);
			
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		for (int i = 0; i < fromList.length; i++) {
			holder.textView[i].setText(SimpleAddressBookAdapter.listMap.get(position).get(fromList[i]));
		}
		
		holder.position = position;
		
		
		return convertView;
	}	
	
	
}
