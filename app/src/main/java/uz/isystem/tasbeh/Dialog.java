package uz.isystem.tasbeh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class Dialog extends AlertDialog {
    private final View view;
    private final Button noBtn;
    private final Button exitBtn;

    protected Dialog(@NonNull Context context) {
        super(context);
        noBtn = findViewById(R.id.noBtn);
        exitBtn = findViewById(R.id.exitBtn);
        view = LayoutInflater.from(context).inflate(R.layout.menu_dialog, null, false);
        setView(view);

    }


}
