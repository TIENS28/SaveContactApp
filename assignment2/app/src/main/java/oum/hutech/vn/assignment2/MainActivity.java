package oum.hutech.vn.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView friend_img;
    Button add_bt, detail_bt, update_bt, delete_bt;
    EditText et_name, et_address, et_phoneNo;
    TextView tv_id;
    GridView gv_display;
    DatabaseHelper databaseHelper;
    ArrayList<Friends> arrayList;
    ArrayAdapter<Friends> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = (TextView) findViewById(R.id.tv_id);
        et_name = (EditText) findViewById(R.id.et_name);
        et_address = (EditText) findViewById(R.id.et_address);
        et_phoneNo = (EditText) findViewById(R.id.et_phone);

        add_bt = (Button) findViewById(R.id.bt_add);
        detail_bt = (Button) findViewById(R.id.bt_detail);
        update_bt = (Button) findViewById(R.id.bt_update);
        delete_bt = (Button) findViewById(R.id.bt_delete);
        databaseHelper = new DatabaseHelper(this);

        gv_display = (GridView) findViewById(R.id.gv_display);

        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friends friends = new Friends();
                friends.setName(et_name.getText().toString());
                friends.setPhoneNo(Integer.parseInt(et_phoneNo.getText().toString()));
                friends.setAddress(et_address.getText().toString());
                clearText();
                if (databaseHelper.insertFriends(friends) > 0)
                    Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Save error!", Toast.LENGTH_SHORT).show();
            }
        });

        detail_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail();
            }
        });

        gv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                Friends friends = arrayList.get(i / 4);
                tv_id.setText(friends.getId() + "");
                et_name.setText(friends.getName());
                et_phoneNo.setText(friends.getPhoneNo() + "");
                et_address.setText(friends.getAddress());
            }
        });

        update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friends friends = new Friends();
                friends.setId(Integer.parseInt(tv_id.getText().toString()));
                friends.setName(et_name.getText().toString());
                friends.setPhoneNo(Integer.parseInt(et_phoneNo.getText().toString()));
                friends.setAddress(et_address.getText().toString());
                if (databaseHelper.updateFriends(friends) > 0)
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_SHORT).show();
                clearText();
                detail();
            }
        });

        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.deleteFriends(Integer.parseInt(et_phoneNo.getText().toString())) > 0)
                    Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Delete error!", Toast.LENGTH_SHORT).show();
                clearText();
                detail();
            }
        });
    }

    public void clearText() {
        tv_id.setText("ïd");
        et_name.getText().clear();
        et_address.getText().clear();
        et_phoneNo.getText().clear();
    }

    public void detail() {
        Friends f = new Friends();
        if (et_name.getText().toString().equals("")) {
            arrayList = databaseHelper.getAllFriends();
        }
        ArrayList<String> list_string = new ArrayList<>();
        for (Friends friends : arrayList) {
            list_string.add(friends.getId() + "");
            list_string.add(friends.getName());
            list_string.add(friends.getPhoneNo() + "");
            list_string.add(friends.getAddress());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list_string);
        gv_display.setAdapter(adapter);
    }
}
