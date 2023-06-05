package sg.edu.rp.c346.id22034700.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerElement;
    EditText editTextElement;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView lvTask;
    ArrayList<String> alTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerElement = findViewById(R.id.spinner);
        editTextElement = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnRemove = findViewById(R.id.buttonRemove);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);

        alTask = new ArrayList<String>();

        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        spinnerElement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        editTextElement.getText().clear();
                        btnRemove.setEnabled(false);
                        btnAdd.setEnabled(true);
                        editTextElement.setHint(getResources().getString(R.string.addTask));
                        break;
                    case 1:
                        editTextElement.getText().clear();
                        btnRemove.setEnabled(true);
                        btnAdd.setEnabled(false);
                        editTextElement.setHint(getResources().getString(R.string.removeTask));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextElement.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "No empty fields allowed", Toast.LENGTH_SHORT).show();
                } else {
                    String userColour = editTextElement.getText().toString();
                    alTask.add(userColour);
                    aaTask.notifyDataSetChanged();
                    editTextElement.setText("");
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(editTextElement.getText().toString());
                if (editTextElement.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "No empty fields allowed", Toast.LENGTH_SHORT).show();
                } else {
                    if (alTask.size() == 0) {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    } else {
                        if (alTask.size() > pos) {
                            alTask.remove(pos);
                            aaTask.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });
    }
}