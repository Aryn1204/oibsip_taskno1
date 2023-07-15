package ac.projects.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result_tv,solution_tv;
    MaterialButton buttonAC,buttonOpenBracket,buttonCloseBracket,buttonC,buttonDot;
    MaterialButton buttonDivide,buttonMinus,buttonPlus,buttonMultiply,buttonEquals;
    MaterialButton buttonZero,buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv = findViewById(R.id.tv_result);
        solution_tv = findViewById(R.id.tv_solution);

        idAssign(buttonAC,R.id.button_ac);
        idAssign(buttonOpenBracket,R.id.button_starting_bracket);
        idAssign(buttonCloseBracket,R.id.button_ending_bracket);
        idAssign(buttonC,R.id.button_c);
        idAssign(buttonDot,R.id.button_dot);
        idAssign(buttonDivide,R.id.button_divide);
        idAssign(buttonMinus,R.id.button_minus);
        idAssign(buttonPlus,R.id.button_plus);
        idAssign(buttonMultiply,R.id.button_x);
        idAssign(buttonEquals,R.id.button_equals);
        idAssign(buttonZero,R.id.button_0);
        idAssign(buttonOne,R.id.button_1);
        idAssign(buttonTwo,R.id.button_2);
        idAssign(buttonThree,R.id.button_3);
        idAssign(buttonFour,R.id.button_4);
        idAssign(buttonFive,R.id.button_5);
        idAssign(buttonSix,R.id.button_6);
        idAssign(buttonSeven,R.id.button_7);
        idAssign(buttonEight,R.id.button_8);
        idAssign(buttonNine,R.id.button_9);

    }

    void idAssign(MaterialButton button,int id){
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String btnTxt = button.getText().toString();
        String dataCalculate = solution_tv.getText().toString();

        if(btnTxt.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(btnTxt.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(btnTxt.equals("C")){
            dataCalculate = dataCalculate.substring(0,dataCalculate.length()-1);
        }
        else{
            dataCalculate = dataCalculate + btnTxt;
        }


        solution_tv.setText(dataCalculate);
        String result = getResult(dataCalculate);
        if(!result.equals("Error")){
            result_tv.setText(result);
        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(result.endsWith(".0")){
                result = result.replace(".0","");
            }
            return result;
        }
        catch (Exception err){
            return "Error";
        }
    }
}

