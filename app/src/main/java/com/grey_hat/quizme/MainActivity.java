package com.grey_hat.quizme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//Create an array string for the evaluation
    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

//Create an array string to contain the answers given by the user
    public String[] evaluateGui() {
        String[] ret = new String[7];

        EditText Question1 = findViewById(R.id.question_1);
        EditText Question2 = findViewById(R.id.question_2);
        EditText Question7 = findViewById(R.id.question_7);
        CheckBox question4Egypt = findViewById(R.id.question4_egypt);
        CheckBox question4India = findViewById(R.id.question4_india);
        CheckBox question4Brazil = findViewById(R.id.question4_brazil);
        CheckBox question4Mexico = findViewById(R.id.question4_mexico);

        Boolean answerQuestion4 = false;
            if (question4Egypt.isChecked() == true && question4India.isChecked() == false && question4Mexico.isChecked() == true && question4Brazil.isChecked() == false ) {
            answerQuestion4 = true;
            }

        ret[0] = Question1.getText().toString().toLowerCase();
        ret[1] = Question2.getText().toString().toLowerCase();
        ret[2] = evaluateRadioGroup(R.id.radio_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_question_5).toLowerCase();
        ret[5] = evaluateRadioGroup(R.id.radio_question_6).toLowerCase();
        ret[6] = Question7.getText().toString().toLowerCase();

        return ret;
    }

//Give the correct answers

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"pyramid", "peru", "india", "true", "Q5", "jordan","the great wall of china"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    //create a toast to show how many answers are correct

    public void toastResult(int result) {
        String message = result + " out of 7. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Try harder..You can do it";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Great You are getting there!";
        }else if (result == 6) {
            message += "Close to Perfect!";
        }else if (result == 7) {
            message += "Perfectly awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }
//Code to reset the fields
    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        EditText editText2 = findViewById(R.id.question_2);
        editText2.setText("");

        EditText editText7 = findViewById(R.id.question_7);
        editText7.setText("");

        CheckBox checkBox = findViewById(R.id.question4_egypt);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question4_india);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question4_brazil);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question4_mexico);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_question_3);
        radioGroup.clearCheck();

        radioGroup = findViewById(R.id.radio_question_5);
        radioGroup.clearCheck();

        radioGroup = findViewById(R.id.radio_question_6);
        radioGroup.clearCheck();
    }
}
