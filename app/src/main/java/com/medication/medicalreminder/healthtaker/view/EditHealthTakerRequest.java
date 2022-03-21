package com.medication.medicalreminder.healthtaker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.healthtaker.persenter.EditHealthTakerPresenter;
import com.medication.medicalreminder.healthtaker.persenter.EditHealthTakerPresenterInterface;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;

public class EditHealthTakerRequest extends AppCompatActivity implements EditHealthTakerRequestInterface {

    public static String senderName = "";
    private TextView messageTextView;
    private Button acceptButton, denyButton;
    private EditHealthTakerPresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_health_taker_request);

        presenterInterface = new EditHealthTakerPresenter(Repository.getInstance(this, ConcreteLocalSource.getInstance(this), FirebaseOperation.getInstance()));

        messageTextView = findViewById(R.id.message_textView);
        acceptButton = findViewById(R.id.accept_button);
        denyButton = findViewById(R.id.deny_button);


        messageTextView.setText(senderName + " " + getResources().getString(R.string.request_health_taker_message));
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditHealthTakerRequest.this, "acceptButton", Toast.LENGTH_SHORT).show();
                presenterInterface.sendReplyOnInvitation(true);
                finish();
            }
        });

        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditHealthTakerRequest.this, "denyButton", Toast.LENGTH_SHORT).show();
                presenterInterface.sendReplyOnInvitation(false);
                finish();
            }
        });

    }
}