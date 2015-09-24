package edu.ecjtu.java4phone;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {
    EditText input;
    TextView inputHint;
    Button btnCalc;
    ImageView imgView;
    Button btnNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.calc_input);
        inputHint = (TextView) findViewById(R.id.input_hint);
        btnCalc = (Button) findViewById(R.id.btn_calc);

        final ArrayList<Integer> num = new ArrayList<>();
        final ArrayList<String> opts = new ArrayList<>();
        final int[] result = new int[1];
        final boolean[] opt_flag = {false};

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStatement = input.getText().toString();
                //Todo: 这里执行计算功能
                Pattern pattern = Pattern.compile("\\-|\\+|\\*|\\/|\\d");
                Matcher matcher = pattern.matcher(inputStatement);
                while (matcher.find()) {
                    String opt = inputStatement.substring(matcher.start(), matcher.end());
                    opts.add(opt);
                }
                //btnNull.setText("Go");
                int result = doit(opts.get(0),opts.get(2),opts.get(1));
                opts.clear();
                //结果在这里输出
                inputHint.setText("= " + new Double(result).toString());
            }
        });
    }

    private int doit(String pres, String nex, String opt) {
        int pre = Integer.parseInt(pres);
        int next = Integer.parseInt(nex);
        switch (opt) {
            case "+":
                return pre + next;
            case "-":
                return pre - next;
            case "*":
                return pre * next;
            case "/":
                return pre / next;
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
