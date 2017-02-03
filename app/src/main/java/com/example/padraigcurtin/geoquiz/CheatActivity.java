package com.example.padraigcurtin.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by padraig.curtin on 03/02/2017.
 */

public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.padraigcurtin.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "com.example.padraigcurtin.geoquiz.answer_shown";

    private static final String TAG = "CheatActivity";

    private static final String KEY_CHEATER = "cheater";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private TextView mVersionTextView;
    private Button mShowAnswer;
    private boolean cheated = false;

    private void setAnswerShownResult(boolean isAnswerShown) {
        cheated = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_CHEATER, cheated);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        // Answer will not be shown until the user
        // presses the button
        setAnswerShownResult(false);
        if (savedInstanceState != null) {
            if(savedInstanceState.getBoolean(KEY_CHEATER, false)==true){
                setAnswerShownResult(true);
            }
        }

        mVersionTextView = (TextView)findViewById(R.id.versionText);
        mVersionTextView.setText("Android Version: "+ android.os.Build.VERSION.SDK);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
}
