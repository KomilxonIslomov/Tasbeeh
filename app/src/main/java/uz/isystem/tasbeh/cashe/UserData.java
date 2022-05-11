package uz.isystem.tasbeh.cashe;

import androidx.appcompat.widget.AppCompatEditText;

public class UserData {
    private int step;
    private String name;

    public UserData(AppCompatEditText name, int a) {
    }

    public UserData(int step, String name) {
        this.step = step;
        this.name = name;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "step=" + step +
                ", name='" + name + '\'' +
                '}';
    }
}
