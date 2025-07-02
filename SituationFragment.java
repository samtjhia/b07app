package com.example.b07demosummer2024;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SituationFragment extends Fragment {
    private FirebaseDatabase db;
    private String choice;
    private String question;

    private DatabaseReference itemsRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_questionnaire_status, container, false);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        question = String.valueOf(view.findViewById(R.id.situation));
        RadioButton radioButton1 = view.findViewById(R.id.radioButton);
        RadioButton radioButton2 = view.findViewById(R.id.radioButton2);
        RadioButton radioButton3 = view.findViewById(R.id.radioButton3);
        Button nextButton = view.findViewById(R.id.nextButton);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find which radio button was selected
                RadioButton selectedRadioButton = view.findViewById(checkedId);
                choice = selectedRadioButton.getText().toString();



            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            //"Still in a relationship","Planning to leave","Post-separation"

            @Override
            public void onClick(View v) {addAnswer(); loadFragment(new AddItemFragment());}
            //public void onClick(View v) {loadFragment(new CityFragment());

        });



        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void addAnswer() {
        db = FirebaseDatabase.getInstance("link");
        itemsRef = db.getReference("questionaire/warm-up");
        String []choices = {"Still in a relationship","Planning to leave","Post-separation"};
        String id = itemsRef.push().getKey();
        Question question1 = new Question(question,choices);

        itemsRef.child(id).setValue(question1).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to add item", Toast.LENGTH_SHORT).show();
            }




        /*
        String author = editTextAuthor.getText().toString().trim();
        String genre = editTextGenre.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString().toLowerCase();



        itemsRef = db.getReference("categories/" + category);
        String id = itemsRef.push().getKey();
        Item item = new Item(id, title, author, genre, description);

        itemsRef.child(id).setValue(item).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to add item", Toast.LENGTH_SHORT).show();
            }*/
        });
    }


}
