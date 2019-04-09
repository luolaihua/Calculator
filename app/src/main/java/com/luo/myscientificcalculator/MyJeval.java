package com.luo.myscientificcalculator;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionHelper;
import net.sourceforge.jeval.function.FunctionResult;
import net.sourceforge.jeval.function.math.Abs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyJeval extends Evaluator{


    public static void main(String[] args) throws EvaluationException {

       String a = "idsjfio#soijdfioe#dijfeio#djfeio#dfre592+92682#dofreo84684#dfrfrf98458";
       String aa = "idsjfio";
        String[] b = aa.split("#");
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }

    }
















    public static void pow2(StringBuilder sb2,String op,String flag,int count_left,int count_right){
        //最后一个操作数-----op
        //最后按下的字符-----flag
        //StringBuilder sb = new StringBuilder("9+(3-(4+8*(3-2))");

        int c = count_left-count_right; //a为两数量之差
        StringBuilder sb = new StringBuilder(sb2);
        System.out.println(sb2);

        if(flag == ")"){
            //拆括号
            for(int i = 0;i<=c; i++){
                sb.replace(0,sb.length(),sb.substring(sb.indexOf("(")+1));
            }
            //删除原sb2中需要包围的字符串
            sb2.delete(sb2.length()-sb.length()-1,sb2.length());
            //包围sb，并拼接到sb2
            sb2.append("pow(("+sb+",");
        }else {
            //如果遇到的不是 “）”
            //获取op之后的字符串
            //如果op为空
            if(op==""){
                sb2.replace(0,sb2.length(),"pow(" + sb + ",");
                //sb2.append("pow(2," + sb + ")");
            }else {
                //System.out.println(sb2.substring(sb2.lastIndexOf(op)+1));
                sb2.replace(sb2.lastIndexOf(op)+1,sb2.length(),
                        "pow("+sb2.substring(sb2.lastIndexOf(op)+1)+",");
            }
        }
    }







//在点击了加减乘除之后使用，防止多次点击加减乘除
    public static void getOperator(StringBuilder sb,String op,String flag) {
        //当前操作数-----op
        //之前操作数-----flag
        if (flag =="num") {
             sb.append(op);
            }else {
            sb.replace(sb.length()-1,sb.length(),op);
        }
    }

    public static void bracket(StringBuilder sb,int count_kuo,String flag){
        String a = sb.substring(sb.length()-1);

    }

    public static void clear(int a) {

        a=0;
    }

}
