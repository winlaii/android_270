package com.example.user.simpleui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_DRINK_MENU_ACTIVITY = 0;

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String selectedTea = "black tea";

    String menuResults = "";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText= (EditText)findViewById(R.id.editText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        listView = (ListView)findViewById(R.id.listView);
        spinner = (Spinner)findViewById(R.id.spinner);

        sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editText.setText(sharedPreferences.getString("editText", ""));
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                String text = editText.getText().toString();
                editor.putString("editText", text);
                editor.commit();

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    submit(v);
                    return true;
                }
                return false;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                selectedTea = radioButton.getText().toString();
            }
        });
        String history = Utils.readFile(this, "history");
        String[] datas = history.split("\n");
        for(String data: datas)
        {
            Order order = Order.newInstanceWithData(data);
            if (order != null)
            orders.add(order);
        }

        setupListView();
        setupSpinner();

        Log.d("Debug", "MainActivity OnCreate");
    }

    public void setupListView()
    {
        OrderAdapter adapter = new OrderAdapter(this, orders);
        listView.setAdapter(adapter);
    }

    public void setupSpinner()
    {
        String[] data= getResources().getStringArray(R.array.storeInfos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);
        spinner.setAdapter(adapter);
    }

    public void submit(View view)
    {
        String text = editText.getText().toString();
        textView.setText(text);

        Order order = new Order();
        order.note = text;
        order.menuResults = menuResults;
        order.storeInfo = (String)spinner.getSelectedItem();

        orders.add(order);

        Utils.writeFile(this, "history", order.toData() + "\n");

        setupListView();

        editText.setText("");
        menuResults = "";
    }

    public void goToMenu (View view)
    {
        Intent intent = new Intent();
        intent.setClass(this, DrinkMenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DRINK_MENU_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_DRINK_MENU_ACTIVITY)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "完成菜單", Toast.LENGTH_SHORT).show();
                menuResults = data.getStringExtra("results");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "MainActivity OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "MainActivity OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "MainActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "MainActivity OnStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("Debug", "MainActivity onDestroy");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d("Debug", "MainActivity onRestart");
    }
}

