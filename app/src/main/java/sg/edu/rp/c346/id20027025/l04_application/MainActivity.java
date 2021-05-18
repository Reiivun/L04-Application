package sg.edu.rp.c346.id20027025.l04_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etNumber;
    EditText etSize;
    RadioGroup rgTable;
    RadioButton rbTableSmoke;
    RadioButton rbTableNonSmoke;
    DatePicker datePicker;
    TimePicker timePicker;
    Button btnConfirm;
    Button btnClearInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etSize = findViewById(R.id.etSize);
        rgTable = findViewById(R.id.rgTable);
        rbTableSmoke = findViewById(R.id.rbTableSmoke);
        rbTableNonSmoke = findViewById(R.id.rbTableNonSmoke);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnClearInput = findViewById(R.id.btnClearInput);

        datePicker.updateDate(2021, 5, 1);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phoneNumber = etNumber.getText().toString();
                String pax = etSize.getText().toString();
                String isSmoke = "";
                int checkedRadioId = rgTable.getCheckedRadioButtonId();

                if (checkedRadioId == R.id.rbTableNonSmoke) {
                    isSmoke = "non-smoking";
                } else {
                    isSmoke = "smoking";
                }

                if (name.matches("") || phoneNumber.matches("") || pax.matches("") || isSmoke.matches("")) {
                    Toast.makeText(MainActivity.this, "Please enter in empty fields", Toast.LENGTH_SHORT).show();

                    if (pax.matches("0")) {
                        Toast.makeText(MainActivity.this, "Cannot have 0 pax!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    String date = datePicker.getYear() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth();
                    String time = timePicker.getCurrentHour() + ":" + String.format("%02d", timePicker.getCurrentMinute());

                    String msg = "Hi, " + name + ", you have booked a " + pax + " person(s) " + isSmoke + " table on " + date + " at " + time + ". Your phone number is " + phoneNumber + ".";

                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }

            }
        });

        btnClearInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etNumber.setText("");
                etSize.setText("");
                rbTableNonSmoke.setChecked(false);
                rbTableSmoke.setChecked(false);
                datePicker.updateDate(2021, 5, 1);
                timePicker.setCurrentHour(20);
                timePicker.setCurrentMinute(30);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                hourOfDay = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();

                if (hourOfDay >= 21) {
                    timePicker.setCurrentHour(20);
                    timePicker.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this, "We close at 9pm, open at 8am.", Toast.LENGTH_SHORT).show();
                }
                if (hourOfDay < 8) {
                    timePicker.setCurrentHour(20);
                    timePicker.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this, "We close at 9pm, open at 8am.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}