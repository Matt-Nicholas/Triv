package com.example.guest.triv.services;

import android.text.Html;
import android.util.Log;

import com.example.guest.triv.Constants;
import com.example.guest.triv.models.Question;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;
import okhttp3.Call;

public class TriviaService {
    public static void findQuestions(Callback callback){
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("","");
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TRIVIA_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<Question> processResults(Response response){

        ArrayList<Question> questions = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject triviaJSON = new JSONObject(jsonData);
                JSONArray questionsJSON = triviaJSON.getJSONArray("results");
                for (int i = 0; i < questionsJSON.length(); i++) {
                    JSONObject questionJSON = questionsJSON.getJSONObject(i);
                    String category = questionJSON.getString("category");
                    String type = questionJSON.getString("type");
                    String difficulty = questionJSON.getString("difficulty");
                    String question = questionJSON.getString("question");
                    question = decodeString(question);// Decode special chars
                    String correct_answer = questionJSON.getString("correct_answer");
                    correct_answer = decodeString(correct_answer); // Decode special chars
                    ArrayList<String> incorrect_answers = new ArrayList<>();
                    JSONArray incorrect_answersJSON = questionJSON.getJSONArray("incorrect_answers");
                    for (int y = 0; y < incorrect_answersJSON.length(); y++) {
                        incorrect_answers.add(incorrect_answersJSON.get(y).toString());
                    }
                    for(int j=0; j<incorrect_answers.size(); j++){ // Loop through incorrect answers
                        incorrect_answers.set(j, decodeString(incorrect_answers.get(j))); // Decode and replace incorrect answers that contain special chars
                    }
                    // Create new question with JSON results
                    Question q = new Question(category, type, difficulty, question,
                            correct_answer, incorrect_answers);
                    questions.add(q);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // Decodes special characters in given string
    public String decodeString(String s){
        String convertedString = s;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            convertedString = Html.fromHtml(s,Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            convertedString = Html.fromHtml(s).toString();
        }
        return convertedString;
    }
}
