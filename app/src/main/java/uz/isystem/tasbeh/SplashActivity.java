package uz.isystem.tasbeh;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private CountDownTimer timer;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        logo = findViewById(R.id.imageView2);
        setLogoAnimation();
        timer = new CountDownTimer(2_500, 1_000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }

    private void setLogoAnimation() {
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        logo.startAnimation(logoAnimation);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}