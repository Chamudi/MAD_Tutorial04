package com.example.dbhandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbhandling.Database.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText Uname,Pwrd;
Button Sall,add,signin,delete,update;

DBHandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHandler(this);
        Uname = findViewById(R.id.editText2);
        Pwrd = findViewById(R.id.editText);
        Sall =findViewById(R.id.button) ;
        add = findViewById(R.id.button2) ;
        signin = findViewById(R.id.button3) ;
        delete =findViewById(R.id.button5) ;
        update =findViewById(R.id.button4) ;




    }

    @Override
    protected void onResume() {
        super.onResume();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.addInfo(Uname.getText().toString(),Pwrd.getText().toString());
                Toast.makeText(MainActivity.this,"Insertion Successfull",Toast.LENGTH_LONG).show();
            }
        });

        Sall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> usernames =  myDb.readALLInfo();


                for (String usr: usernames){
                    Log.i("db",usr);
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> matchList = myDb.readInfo(Uname.getText().toString(), Pwrd.getText().toString());

                if(matchList.size() > 0){
                    Toast.makeText(getApplicationContext(), "Username and password is correct",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Username and password is wrong",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDb.deleteInfo(Uname.getText().toString());
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateInfo(Uname.getText().toString(),Pwrd.getText().toString());
                Toast.makeText(MainActivity.this,"Successfully update",Toast.LENGTH_LONG).show();
            }
        });
    }
}
