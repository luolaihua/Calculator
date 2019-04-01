package com.luo.myscientificcalculator;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionHelper;
import net.sourceforge.jeval.function.FunctionResult;
import net.sourceforge.jeval.function.math.Abs;

public class MyJeval extends Evaluator{


    public static void main(String[] args) throws EvaluationException {
        Evaluator evaluator = new Evaluator();

        StringBuilder sb = new StringBuilder("9+(3-9)");
        StringBuilder sb2 = new StringBuilder(sb);
        //pow(sb,3,2);
        System.out.println(sb);
        /*System.out.println(sb2);
        //拆括号
        for(int i = 0;i<=1; i++){
            //左边需要再补个括号
            sb.replace(0,sb.length(),sb.substring(sb.indexOf("(")+1));
            System.out.println(sb);

        }
        System.out.println(sb2.delete(sb2.length()-sb.length()-1,sb2.length()));
        System.out.println(sb2.append("pow(("+sb+")"));
*/








        /*
         * getNumberResult---得到数字结果
         * isExpressionString---判断表达式是否有效
         */
        /*try {
            System.out.println("1.-->" + evaluator.evaluate("1+2*3-2/1"));  //直接计算字符串型的数学表达式
            System.out.println("2.-->" + evaluator.evaluate("toUpperCase(trim( trim(' a b c ') ))"));  //执行java中的方法
            evaluator.putVariable("a", "'Hello'");//定义字符串变量
            evaluator.putVariable("b", "'World'");
            evaluator.putVariable("c", "1"); //定义数字变量
            evaluator.putVariable("d", "2");
            System.out.println("3.-->" + evaluator.evaluate("#{a}")); //输出字符串
            System.out.println("4.-->" + evaluator.evaluate("#{b}"));
            System.out.println("5.-->" + evaluator.evaluate("#{PI}"));
            System.out.println("6.-->" + evaluator.evaluate("#{a} + ' ' + #{b} + '!'")); //拼接后输出
            System.out.println("7.-->" + evaluator.evaluate("(#{c} + #{d}) - #{c}"));  //拼接后输出计算结果

        } catch (EvaluationException ee) {
            System.out.println(ee);
        }
        */


       /* try {

            //System.out.println(Math.PI);
            //System.out.println(Math.E);

            System.out.println(evaluator.evaluate("abs(-1)"));
            System.out.println(evaluator.evaluate("sqrt(abs(-9))"));
            System.out.println(evaluator.evaluate("log(2.71828)"));
            System.out.println(evaluator.evaluate("log(5)"));


        } catch (EvaluationException ee) {
            System.out.println(ee);
        }*/
       /* String aString = evaluator.evaluate("2*(9-8)");
        double b = evaluator.getNumberResult("3*(8-9)");
        System.out.println(aString);
        System.out.println(b);*/

        /**问题一
         * lastIndexOf(String str)
         * 返回指定子字符串最右边出现的字符串内的索引。
         *
         * 思路：
         * 点击了哪个运算符就把哪个运算符放入 operator*/
       /* String op = "+";
        StringBuilder sb = new StringBuilder("123+45-9999+cos(12");
        System.out.println(sb.length());
        System.out.println(sb.lastIndexOf("("));
        //获取op之后的字符串
        System.out.println(sb.substring(sb.lastIndexOf(op)+1));

        System.out.println(sb.replace(sb.lastIndexOf("+")+1,sb.length(),"pow("+sb.substring(sb.lastIndexOf("+")+1)+")"));
*/
       // StringBuilder sb = new StringBuilder("123+45-9999-");
        //sb.replace(sb.length()-1,sb.length(),"+");

       // getOperator(sb,"/");
        //System.out.println(sb);
        /**
         * 问题二
         * 括号问题
         */
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




       /* //拆括号
        for(int i = 0;i<=c; i++){
            //左边需要再补个括号
            sb.replace(0,sb.length(),sb.substring(sb.indexOf("(")+1));
            System.out.println(sb);

        }
        System.out.println(sb2.delete(sb2.length()-sb.length()-1,sb2.length()));
        System.out.println(sb2.append("pow(2,("+sb+")"));


*/

        //获取op之后的字符串
        //System.out.println(sb.substring(sb.lastIndexOf(op)+1));
       // sb.replace(sb.lastIndexOf("(")+1,sb.length(),"pow(2,"+sb.substring(sb.lastIndexOf("(")+1)+")");
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
