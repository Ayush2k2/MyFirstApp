package com.cwadroit.isupportlockdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<Message1> extends AppCompatActivity {
    //for adding row
    TableLayout t1;
    ScrollView scrollView;
    TextView sno;
    EditText quantity,item_name;
    TableRow tr;
    Button add_row,send;
    int sn=6;


    //for getting text from editTexts to send message
    EditText[] item_GetMsg=new EditText[5];
    EditText[] quantity_GetMsg=new EditText[5];
    List<EditText> allItems = new ArrayList<EditText>();
    List<EditText> allQuantities = new ArrayList<EditText>();
    String[] itnm=new String[5];
    String[] Qnty=new String[5];
    EditText contact;
    EditText name,address;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TableLayout)findViewById(R.id.tableLayout);
        add_row=(Button)findViewById(R.id.addRow);
        add_row.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                add(view);
            }
        });
        send=(Button)findViewById(R.id.send);
        scrollView=(ScrollView)findViewById(R.id.scBar);
        item_GetMsg[0]=(EditText)findViewById(R.id.itemName_1);
        item_GetMsg[1]=(EditText)findViewById(R.id.itemName_2);
        item_GetMsg[2]=(EditText)findViewById(R.id.itemName_3);
        item_GetMsg[3]=(EditText)findViewById(R.id.itemName_4);
        item_GetMsg[4]=(EditText)findViewById(R.id.itemName_5);
        quantity_GetMsg[0]=(EditText)findViewById(R.id.quantity_1);
        quantity_GetMsg[1]=(EditText)findViewById(R.id.quantity_2);
        quantity_GetMsg[2]=(EditText)findViewById(R.id.quantity_3);
        quantity_GetMsg[3]=(EditText)findViewById(R.id.quantity_4);
        quantity_GetMsg[4]=(EditText)findViewById(R.id.quantity_5);
        contact=(EditText)findViewById(R.id.contact);
        name=(EditText)findViewById(R.id.editText_name);
        address=(EditText)findViewById(R.id.editText_address);

    }


    // check if the WhatsApp is installed or not and if WhatsApp is not installed, this will redirect to Google play store for installing the WhatsApp

    public void openWhatsApp(View view) {

        // name and address

        String nm=name.getText().toString();
        String adrs=address.getText().toString();
        String start="Name - "+nm+"\n"+"Address - "+adrs+"\n"+"Has ordered for - \n";



        //items name and quantities
        String[] items=new String[allItems.size()];
        String[] qty=new String[allQuantities.size()];
        String Message = "";
        for(int i=0; i < allItems.size(); i++){
            items[i] = allItems.get(i).getText().toString();
        }
        for(int i=0; i < allItems.size(); i++){
            qty[i] = allItems.get(i).getText().toString();
        }
         for(int i=0;i<5;i++){
             itnm[i]=item_GetMsg[i].getText().toString();
         }
        for(int i=0;i<5;i++){
            Qnty[i]=quantity_GetMsg[i].getText().toString();
        }

        for(int i=0;i<5;i++){
            if(itnm[i]!="") {
                Message = Message + itnm[i] + " - " + Qnty[i] + "\n";
            }
        }
        for(int i=0;i< allItems.size();i++){
            if(items[i]!="") {
                Message = Message + items[i] + " - " + Qnty[i] + "\n";
            }
        }


            //send message
            try {
                String phoneNo = contact.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + phoneNo + "&text=" + start+Message));
                startActivity(intent);
                Toast toast=Toast.makeText(this,"Tip  :  Shopkeeper Number should have country code as well without + like 91**********",Toast.LENGTH_LONG);
                toast.show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast toast=Toast.makeText(this,"Some Error Occured",Toast.LENGTH_SHORT);
                toast.show();
            }
        }




    //ADD ROWS
    public void add(View view) {
                tr=new TableRow(this);

                //for serial number
                sno=new TextView(this);
                sno.setText(String.format("%d", sn));
                int snoId=sn;
                sno.setId(snoId);
                sno.setTextSize(18);
                sno.setWidth(36);
                sno.setGravity(Gravity.CENTER);
                tr.addView(sno);


                //for item name
                int inId=100+sn;
                item_name=new EditText(this);
                item_name.setWidth(300);
                item_name.setGravity(Gravity.CENTER);

                item_name.setTextSize(16);
                item_name.setTag(inId);
                tr.addView(item_name);
                allItems.add(item_name);



                //for quantity
                int qnId=200+sn;
                quantity=new EditText(this);
                quantity.setWidth(72);
                quantity.setGravity(Gravity.CENTER);
                quantity.setTag(qnId);

                quantity.setTextSize(16);
                tr.addView(quantity);
                allQuantities.add(quantity);

                //increment for tag and adding row to table
                sn++;
                t1.addView(tr);
                //SCROLLBAR ALWAYS MOVES DOWN
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    }
}
