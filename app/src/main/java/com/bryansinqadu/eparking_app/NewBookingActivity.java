package com.bryansinqadu.eparking_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class NewBookingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener {

    final Context context = this;
    private Button submit;
    private EditText name, email, phone, usercategory, date, time, department, slot;
    private Button availableslots, slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        AllComponents();
        DatePicker();
        TimePicker();
        Departments();
        UserCategory();
        Slots();

    }

    public void AllComponents() {
        submit = (Button) findViewById(R.id.btnSubmit);
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        phone = (EditText) findViewById(R.id.etPhone);
        usercategory = (EditText) findViewById(R.id.etUserCategory);
        date = (EditText) findViewById(R.id.etDate);
        time = (EditText) findViewById(R.id.etTime);
        department = (EditText) findViewById(R.id.etDepartment);
        slot = (EditText) findViewById(R.id.textviewslot);


    }

    public void DatePicker() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

    }

    public void TimePicker() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        date.setText(currentDateString);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time.setText(hourOfDay + ":" + minute);
    }

    public void Departments() {
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.department, null);


                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setView(promptsView);
                builder.setMessage("Select Your Department...");
                builder
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        department.setText(department.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    public void UserCategory() {
        usercategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.usercategory, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setView(promptsView);
                builder.setMessage("Select Your User Category...");
                builder
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        usercategory.setText(department.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    public void Slots() {
        slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View mView = layoutInflater.inflate(R.layout.slots, null);

                availableslots = (Button) mView.findViewById(R.id.btnAvailableSlots);
                slot1 = (Button) mView.findViewById(R.id.btnSlot1);
                slot2 = (Button) mView.findViewById(R.id.btnSlot2);
                slot3 = (Button) mView.findViewById(R.id.btnSlot3);
                slot4 = (Button) mView.findViewById(R.id.btnSlot4);
                slot5 = (Button) mView.findViewById(R.id.btnSlot5);
                slot6 = (Button) mView.findViewById(R.id.btnSlot6);
                slot7 = (Button) mView.findViewById(R.id.btnSlot7);
                slot8 = (Button) mView.findViewById(R.id.btnSlot8);
                slot9 = (Button) mView.findViewById(R.id.btnSlot9);
                slot10 = (Button) mView.findViewById(R.id.btnSlot10);

                availableslots.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slot1.setBackgroundColor(Color.GREEN);
                        slot2.setBackgroundColor(Color.GREEN);
                        slot3.setBackgroundColor(Color.GREEN);
                        slot4.setBackgroundColor(Color.GREEN);
                        slot5.setBackgroundColor(Color.GREEN);
                        slot6.setBackgroundColor(Color.GREEN);
                        slot7.setBackgroundColor(Color.GREEN);
                        slot8.setBackgroundColor(Color.GREEN);
                        slot9.setBackgroundColor(Color.GREEN);
                        slot10.setBackgroundColor(Color.GREEN);

                    }
                });


                AlertDialog.Builder slotbuilder = new AlertDialog.Builder(context);
                slotbuilder.setView(mView);
                slotbuilder.setMessage("Select available slot..");
                slotbuilder
                        .setPositiveButton("Select", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = slotbuilder.create();
                alertDialog.show();
            }
        });
    }
}
