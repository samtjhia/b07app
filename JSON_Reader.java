package com.example.b07demosummer2024;
import java.util.Iterator;
import android.content.Context;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;

public class JSON_Reader {
    public String readJsonFile(Context context, String fileName) {
        try {
            // 1. Open the JSON file from assets or raw
            InputStream is = context.getAssets().open(fileName);  // or from raw resources
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // 2. Convert bytes to String
            String json = new String(buffer, StandardCharsets.UTF_8);
            return json;
            // 3. Parse using JSONObject
            /*
            JSONObject jsonObject = new JSONObject(json);
            JSONObject questionnaireObject = jsonObject.getJSONObject("questionnaire");
            Iterator<String> typeKeys = questionnaireObject.keys();
            Map<String, Map<String, Object>> types = new HashMap<>();

            // 4. Extract values

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
            */

        } catch (Exception e) {
            e.printStackTrace();
        }
    return "";
    }

}
