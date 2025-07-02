package com.example.b07demosummer2024;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Iterator;
import android.content.Context;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("hi");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSON_Reader reader = new JSON_Reader();
            String json = reader.readJsonFile(this, "questionaire.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONObject questionnaireObject = jsonObject.getJSONObject("questionnaire");
            Iterator<String> typeKeys = questionnaireObject.keys();
            Map<String, Map<String, Object>> types = new HashMap<>();
            while (typeKeys.hasNext()) {
                String typekey = typeKeys.next();
                JSONObject typeData = questionnaireObject.getJSONObject(typekey);
                Iterator<String> questionKeys = typeData.keys();
                Map<String, Object> questions = new HashMap<>();
                while (questionKeys.hasNext()) {
                    String questionKey = questionKeys.next();
                    JSONObject questionData = typeData.getJSONObject(questionKey);
                    String question_id = questionData.getString("question_id");
                    String question = questionData.getString("question");

                    Map<String, Object> questionInfo = new HashMap<>();
                    questionInfo.put("question_id", question_id);
                    questionInfo.put("question", question);


                    questions.put(questionKey, questionInfo);


                }

                types.put(typekey, questions);

            }
            for (Map.Entry<String, Map<String, Object>> entry : types.entrySet()) {
                String typeId = entry.getKey();
                Map<String, Object> questionInfo = entry.getValue();

                System.out.println("question type: " + typeId);
                for (Map.Entry<String, Object> info : questionInfo.entrySet()) {
                    System.out.println("  " + info.getKey() + ": " + info.getValue());
                }
            }
        }
     catch (Exception e) {
        e.printStackTrace();
    }

        db = FirebaseDatabase.getInstance("https://b07-demo-summer-2024-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = db.getReference("testDemo");

//        myRef.setValue("B07 Demo!");
        myRef.child("movies").setValue("B07 Demo!");

        if (savedInstanceState == null) {
            loadFragment(new SituationFragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}