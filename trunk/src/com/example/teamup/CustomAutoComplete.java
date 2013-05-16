package com.example.teamup;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

 public class CustomAutoComplete extends AutoCompleteTextView {
     private String previous = "";
     private String seperator = ",";
     private String ini_Address = "(";
     private String fin_Address = ")";

     public CustomAutoComplete(final Context context, final AttributeSet attrs, final int defStyle) {
         super(context, attrs, defStyle);
         this.setThreshold(0);
     }
     public CustomAutoComplete(final Context context, final AttributeSet attrs) {
         super(context, attrs);
         this.setThreshold(0);
     }
     public CustomAutoComplete(final Context context) {
         super(context);
         this.setThreshold(0);
     }

     @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
         String filterText = text.toString().trim();
          previous = filterText.substring(0,filterText.lastIndexOf(getSeperator())+1);
         filterText = filterText.substring(filterText.lastIndexOf(getSeperator()) + 1);
         if(!TextUtils.isEmpty(filterText)){
             super.performFiltering(filterText, keyCode);
         }
     }

     @Override
     protected void replaceText(final CharSequence text) {
    	String address_tmp = "";
    	String address = "";
    	String filterText = text.toString().trim();
    	address = filterText.substring(filterText.lastIndexOf(getIniAddress())+1,filterText.lastIndexOf(getFinAddress()));
    	//address = filterText.substring(0,address.lastIndexOf(getFinAddress()) - 1);
        super.replaceText(previous+address+getSeperator());
     }
     public String getSeperator() {
         return seperator;
     }
     public void setSeperator(final String seperator) {
         this.seperator = seperator;
     }
     public String getIniAddress() {
         return ini_Address;
     }
     public String getFinAddress() {
         return fin_Address;
     }
    	
}

