package com.luo.myscientificcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9,tv_0,tv_show,
            tv_point,tv_add,tv_sub,tv_div,tv_mult,tv_percent,tv_clear,tv_backspace,tv_equal,tv_kuo;
    private TextView tv_1_pro,tv_2_pro,tv_3_pro,tv_4_pro,tv_5_pro,tv_6_pro,tv_7_pro,tv_8_pro,tv_9_pro,tv_0_pro,
            tv_show_pro, tv_point_pro,tv_add_pro,tv_sub_pro,tv_div_pro, tv_mult_pro,tv_percent_pro,
            tv_clear_pro,tv_backspace_pro,tv_equal_pro,tv_kuo_pro,tv_pi,tv_e,tv_sin,tv_cos,tv_tan,tv_ln,tv_lg,tv_sqrt,
            tv_pow,tv_ping;
    private static StringBuilder sb_show ,sb_result;
    private static int flag_kuo = 0,flag_result=0,flag_operator = 0;
    private Evaluator evaluator = new Evaluator();
    private String  result = "";
    private static StringBuilder sb_show_pro ,sb_result_pro;


    private static int flag_kuo_pro = 0,flag_result_pro=0;
    private Evaluator evaluator_pro = new Evaluator();
    private String  result_pro = "";
    public static final double PPP = Math.PI;
    public static final double EEE = Math.E;
    private static String operator = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.scientific);
        sb_show = new StringBuilder();
        sb_result = new StringBuilder();
        tv_0 = (TextView) findViewById(R.id.tv_zero);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
        tv_5 = (TextView) findViewById(R.id.tv_5);
        tv_6 = (TextView) findViewById(R.id.tv_6);
        tv_7 = (TextView) findViewById(R.id.tv_7);
        tv_8 = (TextView) findViewById(R.id.tv_8);
        tv_9 = (TextView) findViewById(R.id.tv_9);
        tv_point = (TextView) findViewById(R.id.tv_point);
       // tv_percent = (TextView) findViewById(R.id.tv_percent);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_sub = (TextView) findViewById(R.id.tv_sub);
        tv_mult = (TextView) findViewById(R.id.tv_mult);
        tv_div = (TextView) findViewById(R.id.tv_div);
        tv_equal = (TextView) findViewById(R.id.tv_equal);
        tv_backspace = (TextView) findViewById(R.id.tv_backspace);
        //tv_kuo = (TextView) findViewById(R.id.tv_quo);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_show = (TextView) findViewById(R.id.tv_show);



        sb_show_pro = new StringBuilder();
        sb_result_pro = new StringBuilder();
        tv_0_pro = (TextView) findViewById(R.id.tv_zero_pro);
        tv_1_pro = (TextView) findViewById(R.id.tv_1_pro);
        tv_2_pro = (TextView) findViewById(R.id.tv_2_pro);
        tv_3_pro = (TextView) findViewById(R.id.tv_3_pro);
        tv_4_pro = (TextView) findViewById(R.id.tv_4_pro);
        tv_5_pro = (TextView) findViewById(R.id.tv_5_pro);
        tv_6_pro = (TextView) findViewById(R.id.tv_6_pro);
        tv_7_pro = (TextView) findViewById(R.id.tv_7_pro);
        tv_8_pro = (TextView) findViewById(R.id.tv_8_pro);
        tv_9_pro = (TextView) findViewById(R.id.tv_9_pro);
        tv_point_pro = (TextView) findViewById(R.id.tv_point_pro);
       // tv_percent_pro = (TextView) findViewById(R.id.tv_percent_pro);
        tv_add_pro = (TextView) findViewById(R.id.tv_add_pro);
        tv_sub_pro = (TextView) findViewById(R.id.tv_sub_pro);
        tv_mult_pro = (TextView) findViewById(R.id.tv_mult_pro);
        tv_div_pro = (TextView) findViewById(R.id.tv_div_pro);
        tv_equal_pro = (TextView) findViewById(R.id.tv_equal_pro);
        tv_backspace_pro = (TextView) findViewById(R.id.tv_backspace_pro);
       // tv_kuo_pro = (TextView) findViewById(R.id.tv_quo_pro);
        tv_clear_pro = (TextView) findViewById(R.id.tv_clear_pro);
        tv_show_pro = (TextView) findViewById(R.id.tv_show_pro);
        tv_pi = (TextView) findViewById(R.id.tv_pi);
        tv_e= (TextView) findViewById(R.id.tv_e);
        tv_sin= (TextView) findViewById(R.id.tv_sin);
        tv_cos = (TextView) findViewById(R.id.tv_cos);
        tv_tan = (TextView) findViewById(R.id.tv_tan);
        tv_ln = (TextView) findViewById(R.id.tv_ln);
        tv_lg = (TextView) findViewById(R.id.tv_lg);
        tv_sqrt = (TextView) findViewById(R.id.tv_sqrt);
        tv_pow = (TextView) findViewById(R.id.tv_pow);
        tv_ping = (TextView) findViewById(R.id.tv_ping);


        tv_show.setOnClickListener(this);
        tv_0.setOnClickListener(this);
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
        tv_6.setOnClickListener(this);
        tv_7.setOnClickListener(this);
        tv_8.setOnClickListener(this);
        tv_9.setOnClickListener(this);
        tv_percent.setOnClickListener(this);
        tv_point.setOnClickListener(this);
        tv_add.setOnClickListener(this);
        tv_sub.setOnClickListener(this);
        tv_mult.setOnClickListener(this);
        tv_div.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
        tv_kuo.setOnClickListener(this);
        tv_backspace.setOnClickListener(this);
        tv_equal.setOnClickListener(this);



       /* tv_1_pro.setOnClickListener(this);tv_2_pro.setOnClickListener(this);tv_3_pro.setOnClickListener(this);tv_4_pro.setOnClickListener(this);
        tv_5_pro.setOnClickListener(this);tv_6_pro.setOnClickListener(this);tv_7_pro.setOnClickListener(this);tv_8_pro.setOnClickListener(this);
        tv_9_pro.setOnClickListener(this);tv_0_pro.setOnClickListener(this);tv_add_pro.setOnClickListener(this);
        tv_show_pro.setOnClickListener(this); tv_point_pro.setOnClickListener(this);tv_sub_pro.setOnClickListener(this);
        tv_div_pro.setOnClickListener(this); tv_mult_pro.setOnClickListener(this);tv_percent_pro.setOnClickListener(this);
        tv_clear_pro.setOnClickListener(this);tv_backspace_pro.setOnClickListener(this);tv_equal_pro.setOnClickListener(this);
        tv_kuo_pro.setOnClickListener(this);tv_pi.setOnClickListener(this);tv_e.setOnClickListener(this);
        tv_sin.setOnClickListener(this);tv_cos.setOnClickListener(this);tv_tan.setOnClickListener(this);
        tv_ln.setOnClickListener(this);tv_lg.setOnClickListener(this);tv_sqrt.setOnClickListener(this);
        tv_pow.setOnClickListener(this);tv_ping.setOnClickListener(this);
        */

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zero:
                //是否进行过计算，然后初始化
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;

                }
                tv_show.setText(sb_show.append("0"));
                sb_result.append("0");
                operator = "";
                break;
            case R.id.tv_1:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("1");
                tv_show.setText(sb_show.append("1"));operator = "";
                break;
            case R.id.tv_2:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("2");
                tv_show.setText(sb_show.append("2"));operator = "";
                break;
            case R.id.tv_3:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("3");
                tv_show.setText(sb_show.append("3"));operator = "";
                break;
            case R.id.tv_4:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("4");
                tv_show.setText(sb_show.append("4"));operator = "";
                break;
            case R.id.tv_5:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("5");
                tv_show.setText(sb_show.append("5"));operator = "";
                break;
            case R.id.tv_6:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("6");
                tv_show.setText(sb_show.append("6"));operator = "";
                break;
            case R.id.tv_7:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("7");
                tv_show.setText(sb_show.append("7"));operator = "";
                break;
            case R.id.tv_8:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("8");
                tv_show.setText(sb_show.append("8"));operator = "";
                break;
            case R.id.tv_9:
                if (flag_result == 1) {
                    sb_show = new StringBuilder();
                    sb_result = new StringBuilder();
                    flag_kuo = 0;
                    flag_result = 0;
                }
                sb_result.append("9");
                tv_show.setText(sb_show.append("9"));operator = "";
                break;
  //------------------------------------------------------------


            case R.id.tv_add:
                if(! operator.equals("+")){
                    flag_result = 0;
                    sb_result.append("+");
                    tv_show.setText(sb_show.append("+"));
                }
                operator = "+";
                break;
            case R.id.tv_sub:
                if(! operator.equals("-")){
                    flag_result = 0;
                    sb_result.append("-");
                    tv_show.setText(sb_show.append("-"));
                }
                operator = "-";
                break;
            case R.id.tv_mult:
                if(! operator.equals("*")){
                    flag_result = 0;
                    sb_result.append("*");
                    tv_show.setText(sb_show.append("×"));
                } operator = "*";

                break;
            case R.id.tv_div:
                if(! operator.equals("/")){
                    flag_result = 0;
                    sb_result.append("/");
                    tv_show.setText(sb_show.append("÷"));
                } operator = "/";

                break;
            case R.id.tv_point:
                flag_result = 0;
                sb_result.append(".");
                tv_show.setText(sb_show.append("."));
                break;
           /* case R.id.tv_percent:
                flag_result = 0;
                sb_result.append("%");
                tv_show.setText(sb_show.append("%"));
                break;*/
            case R.id.tv_clear:
                tv_show.setText("");
                sb_show = new StringBuilder();
                sb_result = new StringBuilder();
                flag_kuo = 0;
                flag_result = 0;
                break;
            case R.id.tv_backspace:
                if (sb_show.length() != 0 && sb_result.length() != 0) {
                    if(sb_result.charAt(sb_result.length()-1) =='(' || sb_result.charAt(sb_result.length()-1) ==')'){
                        flag_kuo--;
                }
                    tv_show.setText(sb_show.deleteCharAt(sb_show.length()-1));
                    sb_result.deleteCharAt(sb_result.length()-1);
                    flag_result = 0;
                }

                break;
            case R.id.tv_equal:
                try {
                    if (flag_kuo % 2 != 0) {
                        sb_result.append(")");
                    }
                     result = evaluator.evaluate(sb_result.toString());
                    tv_show.setText(result);
                    sb_result = new StringBuilder();
                    sb_show = new StringBuilder();
                    sb_result.append(result);
                    sb_show.append(result);
                    flag_result = 1;
                } catch (EvaluationException ee) {
                    Toast.makeText(MainActivity.this,"输入有误...", Toast.LENGTH_SHORT).show();
                }

                break;
           /* case R.id.tv_quo:
                if (flag_kuo % 2 == 0) {
                    tv_show.setText(sb_show.append("("));
                    sb_result.append("(");
                }else {
                    tv_show.setText(sb_show.append(")"));
                    sb_result.append(")");
                }
                flag_result = 0;
                flag_kuo++;
                break;*/

/**
 * *********************************************************************
 */

            case R.id.tv_zero_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                tv_show_pro.setText(sb_show_pro.append("0"));
                sb_result_pro.append("0");
                break;
            case R.id.tv_1_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("1");
                tv_show_pro.setText(sb_show_pro.append("1"));
                break;
            case R.id.tv_2_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("2");
                tv_show_pro.setText(sb_show_pro.append("2"));
                break;
            case R.id.tv_3_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("3");
                tv_show_pro.setText(sb_show_pro.append("3"));
                break;
            case R.id.tv_4_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("4");
                tv_show_pro.setText(sb_show_pro.append("4"));
                break;
            case R.id.tv_5_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("5");
                tv_show_pro.setText(sb_show_pro.append("5"));
                break;
            case R.id.tv_6_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("6");
                tv_show_pro.setText(sb_show_pro.append("6"));
                break;
            case R.id.tv_7_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("7");
                tv_show_pro.setText(sb_show_pro.append("7"));
                break;
            case R.id.tv_8_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("8");
                tv_show_pro.setText(sb_show_pro.append("8"));
                break;
            case R.id.tv_9_pro:
                if (flag_result_pro == 1) {
                    sb_show_pro = new StringBuilder();
                    sb_result_pro = new StringBuilder();
                    flag_kuo_pro = 0;
                    flag_result_pro = 0;
                }
                sb_result_pro.append("9");
                tv_show_pro.setText(sb_show_pro.append("9"));
                break;
            case R.id.tv_add_pro:
                flag_result_pro = 0;
                sb_result_pro.append("+");
                tv_show_pro.setText(sb_show_pro.append("+"));
                break;
            case R.id.tv_sub_pro:
                flag_result_pro = 0;
                sb_result_pro.append("-");
                tv_show_pro.setText(sb_show_pro.append("-"));
                break;
            case R.id.tv_mult_pro:
                flag_result_pro = 0;
                sb_result_pro.append("*");
                tv_show_pro.setText(sb_show_pro.append("×"));
                break;
            case R.id.tv_div_pro:
                flag_result_pro = 0;
                sb_result_pro.append("/");
                tv_show_pro.setText(sb_show_pro.append("÷"));
                break;
            case R.id.tv_point_pro:
                flag_result_pro = 0;
                sb_result_pro.append(".");
                tv_show_pro.setText(sb_show_pro.append("."));
                break;

            case R.id.tv_clear_pro:
                tv_show_pro.setText("");
                sb_show_pro = new StringBuilder();
                sb_result_pro = new StringBuilder();
                flag_kuo_pro = 0;
                flag_result_pro = 0;
                break;
            case R.id.tv_backspace_pro:
                if (sb_show_pro.length() != 0 && sb_result_pro.length() != 0) {
                    if(sb_result_pro.charAt(sb_result_pro.length()-1) =='(' || sb_result_pro.charAt(sb_result_pro.length()-1) ==')'){
                        flag_kuo_pro--;
                    }
                    tv_show_pro.setText(sb_show_pro.deleteCharAt(sb_show_pro.length()-1));
                    sb_result_pro.deleteCharAt(sb_result_pro.length()-1);
                    flag_result_pro = 0;
                }

                break;
            case R.id.tv_equal_pro:
                try {
                    if (flag_kuo_pro % 2 != 0) {
                        sb_result_pro.append(")");
                    }
                    result_pro = evaluator_pro.evaluate(sb_result_pro.toString());
                    tv_show_pro.setText(result_pro);
                    sb_result_pro = new StringBuilder();
                    sb_show_pro = new StringBuilder();
                    sb_result_pro.append(result_pro);
                    sb_show_pro.append(result_pro);
                    flag_result_pro = 1;
                } catch (EvaluationException ee) {
                    Toast.makeText(MainActivity.this,"输入有误...", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.tv_pi:
                tv_show_pro.setText(sb_show_pro.append("π"));
                sb_result_pro.append(PPP);
                break;
            case R.id.tv_ping:
                tv_show_pro.setText(sb_show_pro.append("²"));
                sb_result_pro.append("pow(");
                break;
            case R.id.tv_e:

                break;
            case R.id.tv_lg:

                break;
            case R.id.tv_tan:

                break;
            case R.id.tv_sin:

                break;

            case R.id.tv_ln:

                break;
            case R.id.tv_sqrt:

                break;
            case R.id.tv_pow:

                break;

        }
    }
}
