package com.beertag.views.beerdetails;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beertag.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTagDialog extends AlertDialog {
    @BindView(R.id.et_new_beer_tag_for_add)
    EditText mEnteredTag;
    @BindView(R.id.btn_answer_add)
    Button mPositiveDialogButton;
    @BindView(R.id.btn_answer_cancel)
    Button mNegativeDialogButton;


    public AddTagDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View dialogView = inflater.inflate(R.layout.beer_add_tag_dialog, null);
        setContentView(dialogView);

        ButterKnife.bind(this);
    }
}
