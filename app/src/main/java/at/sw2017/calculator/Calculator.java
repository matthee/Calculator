package at.sw2017.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Calculator extends AppCompatActivity implements View.OnClickListener {
    private List<Button> numberButtons = new ArrayList<>();

    private Button buttonAdd;
    private Button buttonSubtract;
    private Button buttonMultiply;
    private Button buttonDivide;

    private Button buttonClear;
    private Button buttonEquals;

    private TextView numberView;

    private State state = State.INIT;
    private int firstNumber;

    public enum State {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, INIT, NUM
    }

    static {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        findViews();
        addButtonOnClickListeners();
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        switch (clickedButton.getId()) {
            case R.id.buttonAdd:
                clearAndSaveNumberView();
                state = State.ADD;
                break;

            case R.id.buttonSubtract:
                clearAndSaveNumberView();
                state = State.SUBTRACT;
                break;

            case R.id.buttonMultiply:
                clearAndSaveNumberView();
                state = State.MULTIPLY;
                break;

            case R.id.buttonDivide:
                clearAndSaveNumberView();
                state = State.DIVIDE;
                break;

            case R.id.buttonClear:
                clearNumberView();
                break;

            case R.id.buttonEquals:
                calculateResult();
                break;

            default:
                String recentNumber = numberView.getText().toString();
                if (recentNumber.equals("0")) {
                    recentNumber = "";
                }

                recentNumber += clickedButton.getText().toString();
                numberView.setText(recentNumber);

        }
    }


    private void findViews() {
        for (int i = 0; i <= 9; i++) {
            String buttonName = "button" + i;

            int buttonId = getResources().getIdentifier(buttonName, "id", R.class.getPackage().getName());

            Button button = (Button) findViewById(buttonId);
            numberButtons.add(button);
        }

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide  = (Button) findViewById(R.id.buttonDivide);

        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonEquals = (Button) findViewById(R.id.buttonEquals);

        numberView = (TextView) findViewById(R.id.numberView);
    }


    private void addButtonOnClickListeners() {
        for (Button button : numberButtons) {
            button.setOnClickListener(this);
        }

        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);

        buttonClear.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
    }

    private void clearAndSaveNumberView() {
        firstNumber = getNumberFieldValue();
        numberView.setText("0");
    }

    private void clearNumberView() {
        numberView.setText("0");
        firstNumber = 0;
        state = State.INIT;
    }

    private void displayResult(int result) {
        numberView.setText(Integer.toString(result));
    }

    private void displayNoValuesError() {
        numberView.setText("Error");
    }

    private int getNumberFieldValue() {
        String firstNumberString = numberView.getText().toString();
        return Integer.valueOf(firstNumberString);
    }

    private void calculateResult() {
        int secondNumber = getNumberFieldValue();
        int result = 0;

        switch (state) {
            case ADD:
                result = Calculations.doAddition(firstNumber, secondNumber);
                break;

            case SUBTRACT:
                result = Calculations.doSubtraction(firstNumber, secondNumber);
                break;


            case MULTIPLY:
                result = Calculations.doMultiplication(firstNumber, secondNumber);
                break;

            case DIVIDE:
                result = Calculations.doDivision(firstNumber, secondNumber);
                break;

            case INIT:
                displayNoValuesError();

            default:
                return;
        }
        state = State.NUM;
        displayResult(result);

    }
}
