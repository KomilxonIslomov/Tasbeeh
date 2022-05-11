package uz.isystem.tasbeh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;

import uz.isystem.tasbeh.cashe.MemoryHelper;
import uz.isystem.tasbeh.cashe.UserData;

public class MainActivity extends AppCompatActivity {
    int step = 0;
    boolean isSound;
    boolean isVibration;
    boolean isTheme;
    private TextView backButton, restartButton, textView, stepButton, textBack;
    private ImageView soundMusic, vibration, theme, menuButton, saveResult;
    private Vibrator vibrator;
    private ConstraintLayout themes;
    private AppCompatEditText name;
    private int a = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadView();
        buttonClick();


    }

    private void loadView() {
//        textBack = findViewById(R.id.themes);
        textView = findViewById(R.id.textView);
//        theme = findViewById(R.id.color_btn);
        stepButton = findViewById(R.id.step);
        soundMusic = findViewById(R.id.sound_btn);
        vibration = findViewById(R.id.vibration_btn);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        theme = findViewById(R.id.color_btn);
        restartButton = findViewById(R.id.restart);
        backButton = findViewById(R.id.orqaga);
        menuButton = findViewById(R.id.menu);
        name = findViewById(R.id.editName);
        saveResult = findViewById(R.id.save_btn);
        isSound = true;
        isVibration = true;
        isTheme = true;


    }

    private void buttonClick() {
        saveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().equals("")) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("Name", name.getText());
                    intent.getIntExtra("step", a);
                    MemoryHelper.getHelper().saveData(new UserData(
                            name, step
                    ));
                    startActivity(intent);
                }
            }
        });
        mediaPlayer = MediaPlayer.create(this, R.raw.clicl);
//        theme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isTheme) {
//                    themes.setBackgroundResource(R.drawable.red);
//                    textBack.setBackgroundResource(R.drawable.tasbeh2);
//                    isTheme = false;
//                } else {
//                    textBack.setBackgroundResource(R.drawable.tasbeh);
//                    themes.setBackgroundResource(R.drawable.backckkck);
//                    isTheme = true;
//                }
//
//            }
//        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
        vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isVibration) {
                    startVibration();
                    vibration.setImageResource(R.drawable.novibration);
                    isVibration = false;
                } else {
                    vibration.setImageResource(R.drawable.vibration);
                    isVibration = true;
                    vibrator.cancel();
                }

            }
        });
        soundMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSound) {
                    soundMusic.setImageResource(R.drawable.nosound);
                    isSound = false;
                    startMusic();
                } else {
                    soundMusic.setImageResource(R.drawable.sound);
                    isSound = true;
                    stopMusic();
                }
            }
        });


        stepButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                a++;
                stepView();
                setSound();
                if (isVibration) {

                    startVibration();
                } else {
                    vibrator.cancel();
                }
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = 0;
                stepView();
                setSound();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSound();
                if (a > 0) {
                    a--;
                    stepView();
                } else {
                    if (a <= 0) {
                        Toast.makeText(MainActivity.this, "!!! Minimal hisob 0.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.menu_dialog, null, false);
        Button noBtn = view.findViewById(R.id.noBtn);
        TextView exitBtn = view.findViewById(R.id.exitBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                onBackPressed();
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        builder.setView(view);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @SuppressLint("MissingPermission")
    private void startVibration() {
        if (isVibration) {
            vibrator.vibrate(100);
        }
    }

    private void startMusic() {
        if (mediaPlayer != null) {
            stopMusic();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.clicl);
        mediaPlayer.start();
    }

    private void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.clicl);
    }

    private void setSound() {
        if (isSound) {
            startMusic();
        } else {
            stopMusic();
        }
    }

    private void stepView() {
        textView.setText(String.format("%d", a));
    }
}