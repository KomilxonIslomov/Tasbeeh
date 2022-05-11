package uz.isystem.tasbeh;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import uz.isystem.tasbeh.cashe.MemoryHelper;
import uz.isystem.tasbeh.cashe.UserData;

public class ResultActivity extends AppCompatActivity {
    private LinearLayout rootGroup;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        rootGroup = findViewById(R.id.root_layout);
        layout = findViewById(R.id.root_layout);
        showResult();
    }

    private void showResult() {
        ArrayList<UserData> list = MemoryHelper.getHelper().getLastResult();
        for (UserData data : list) {
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            StringBuilder builder = new StringBuilder();
            builder.append("  Name: ");
            builder.append(data.getName());
            builder.append("  Step:");
            builder.append(data.getStep());

            textView.setTextSize(15);
            textView.setGravity(500);
            textView.setText(builder.toString());
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(params);
            rootGroup.addView(textView);
        }
    }
}
