package sg.edu.rp.c346.id20026955.oursglands;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etDescription, etSquare;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etSquare = (EditText) findViewById(R.id.etSquare);
        etID = (EditText) findViewById(R.id.etID);
        ratingBar = (RatingBar) findViewById(R.id.ratingStars1);

        Intent i = getIntent();
        final Song currentSong = (Song) i.getSerializableExtra("song");

        etID.setText(currentSong.getId()+"");
        etName.setText(currentSong.getId()+"");
        etDescription.setText(currentSong.getDescription());
        etSquare.setText(currentSong.getSquare()+"");
        //int stars = (int) ratingBar.getRating();
        currentSong.setStars((int) ratingBar.getRating());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentSong.setName(etName.getText().toString().trim());
                currentSong.setDescription(etDescription.getText().toString().trim());
                int square = 0;
                try {
                    square = Integer.valueOf(etSquare.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid square km", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentSong.setSquare(square);

                ratingBar.setRating(currentSong.getStars());
                int result = dbh.updateSong(currentSong);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Island failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("You are delete the data");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Cancel", null);
                myBuilder.setNegativeButton("Delete", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);

                myBuilder.setTitle("Danger Cancel Changes Made?");
                myBuilder.setMessage("Do you want to discard the changes?");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Cancel", null);
                myBuilder.setNegativeButton("Confirm", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

    }


}