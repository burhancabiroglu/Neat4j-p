package com.cabir.neat4j.math;


import com.cabir.neat4j.Main;

import java.util.Random;

public class Matrix {
    private int row,column;
    public double[][] data;

    public Matrix(){
        this(0,0);
    }

    public Matrix(int row,int column){
        this.row = row;
        this.column = column;
        this.data = new double[row][column];
    }

    public Matrix(double[][] m){
        this.data = m;
        this.row = m.length;
        this.column = m[0].length;

    }

    public double get(int row,int column){
        return data[row][column];
    }

    public Matrix(Matrix m){
        this.data = m.data;
        this.row = m.row;
        this.column = m.column;
    }

    public static Matrix clone(Matrix m){
        Matrix matrix = new Matrix(m.row, m.column);
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.column; j++) {
                matrix.data[i][j] = m.get(i,j);
            }
        }
        return matrix;
    }

    public Matrix clone(){
        Matrix matrix = new Matrix(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix.data[i][j] = get(i,j);
            }
        }
        return matrix;
    }

    public static Matrix zeroMatrix(int row,int column){
        Matrix matrix = new Matrix(row,column);
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.column; j++) {
                matrix.data[i][j] = 0;
            }
        }
        return matrix;
    }

    public static Matrix zeroMatrix(Matrix mnw){
        Matrix matrix = new Matrix(mnw.row, mnw.column);
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.column; j++) {
                matrix.data[i][j] = 0;
            }
        }
        return matrix;
    }
    public static Matrix zeroMatrix(double[][] m){
        Matrix matrix = new Matrix(m.length,m[0].length);
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.column; j++) {
                matrix.data[i][j] = 0;
            }
        }
        return matrix;
    }

    public static Matrix randomMatrix(int row,int column){
        Matrix matrix = new Matrix(row,column);
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.column; j++) {
                matrix.data[i][j] = (2*Math.random()-1);
            }
        }
        return matrix;
    }

    public static Matrix randomMatrix(Matrix m){
        Matrix matrix = new Matrix(m.row, m.column);
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.column; j++) {
                matrix.data[i][j] = (2*Math.random()-1);
            }
        }
        return matrix;
    }

    public static Matrix randomMatrix(double[][] m){
        Matrix matrix = new Matrix(m.length, m[0].length);
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.column; j++) {
                matrix.data[i][j] = (2*Math.random()-1);
            }
        }
        return matrix;
    }

    public static Matrix reshape(double[] m,int row,int column){
        Matrix matrix = new Matrix(row,column);
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < column ; j++) {
                if(row>column){
                    matrix.data[i][j] = m[i];
                }
                else{
                    matrix.data[i][j] = m[j];
                }
            }
        }
        return matrix;
    }

    public void add(double scaler) {
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                this.data[i][j]+=scaler;
            }
        }
    }

    public Matrix add(Matrix m) {
        if(column!=m.column || row!=m.row) {
            System.out.println("Shape Mismatch");
            return null;
        }

        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                this.data[i][j]+=m.data[i][j];
            }
        }
        return this.clone();
    }

    public static Matrix add(Matrix m1,Matrix m2) {
        Matrix mnt = new Matrix(m1.row,m1.column);
        if(m1.column!=m2.column || m1.row!=m2.row) {
            System.out.println("Shape Mismatch "+m1.shape()+" and "+m2.shape());
            return null;
        }

        for(int i=0;i<m1.row;i++) {
            for(int j=0;j<m1.column;j++) {
                mnt.data[i][j] = m1.data[i][j]+m2.data[i][j];
            }
        }
        return mnt;
    }

    public static Matrix sub(Matrix m1,Matrix m2) {
        Matrix mnt = new Matrix(m1.row,m1.column);
        if(m1.column!=m2.column || m1.row!=m2.row) {
            System.out.println("Shape Mismatch "+m1.shape()+" and "+m2.shape());
            return null;
        }

        for(int i=0;i<m1.row;i++) {
            for(int j=0;j<m1.column;j++) {
                mnt.data[i][j] = m1.data[i][j]-m2.data[i][j];
            }
        }
        return mnt;
    }

    public static Matrix transpose(Matrix a) {
        Matrix temp=new Matrix(a.column,a.row);
        for(int i=0;i<a.row;i++) {
            for(int j=0;j<a.column;j++) {
                temp.data[j][i]=a.data[i][j];
            }
        }
        return temp;
    }

    public Matrix transpose(){
        return Matrix.transpose(this);
    }

    public Matrix T(){
        return transpose();
    }



    public void subtract(double scaler) {
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                this.data[i][j]-=scaler;
            }
        }
    }

    public void subtract(Matrix m) {
        if(column!=m.column || row!=m.row) {
            System.out.println("Shape Mismatch");
            return;
        }

        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                this.data[i][j]-=m.data[i][j];
            }
        }
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp=new Matrix(a.row,b.column);
        for(int i=0;i<temp.row;i++) {
            for(int j=0;j<temp.column;j++) {
                double sum=0;
                for(int k=0;k<a.column;k++) {
                    sum+=a.data[i][k]*b.data[k][j];
                }
                temp.data[i][j]=sum;
            }
        }
        return temp;
    }

    public void multiply(Matrix a) {
        for(int i=0;i<a.row;i++) {
            for(int j=0;j<a.column;j++) {
                this.data[i][j]*=a.data[i][j];
            }
        }
    }

    public void multiply(double a) {
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                this.data[i][j]*=a;
            }
        }
    }

    public Matrix sigmoid() {
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++)
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
        }
        return this.clone();
    }

    public Matrix relu(){
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++)
                this.data[i][j] = Math.max(0,this.data[i][j]);
        }
        return this.clone();
    }

    public static Matrix dot(Matrix m1,Matrix m2){

        if(m1.column != m2.row){
            System.out.println("Invalid Shape. ("+ m1.row+","+m1.column+")  and  ("+ m2.row+","+m2.column+")");
            return null;
        }
        Matrix m = new Matrix(m1.row,m2.column);

        for (int i = 0; i < m1.row; i++) {
            for (int j = 0; j < m2.column; j++) {
                double num = 0;
                for (int k = 0; k < m2.row; k++) {
                    num += m1.get(i,k)*m2.get(k,j);
                }
                m.data[i][j] +=num;
            }
        }
        return m;
    }

    public Matrix dot(Matrix m1){
        return Matrix.dot(this,m1);
    }


    public static Matrix unitMatrix(int row){
        Matrix unit = Matrix.zeroMatrix(row,row);
        for (int i = 0; i < row; i++) {
            unit.data[i][i] = 1;
        }
        return unit;
    }

    public void log(){
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(get(i,j)+" ");
                out.append(get(i, j)).append(" ");
            }
            System.out.println();
            out.append("\n");
        }

        Main.LOGGER.warning(out.toString());


    }

    public static void show(Matrix m){
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.column; j++) {
                System.out.print(m.get(i,j)+" ");
            }
            System.out.println();
        }
    }

    public String shape(){
        return "("+row+","+column+")";
    }

    public static String shape(Matrix m){
        return "("+m.row+","+m.column+")";
    }

    public int shape(int i){
        if(i==0)
            return row;
        else
            return column;
    }

    public static double uniformRandom(int low, int height){
        Random random = new Random();

        return  random.nextInt(height+1);
    }

    public void getMeYourselfBaby(double[][] m){
        this.data = m;
        this.row = m.length;
        this.column = m[0].length;
    }
    public void getMeYourselfBaby(Matrix m){
        this.data = m.clone().data;
        this.row = m.row;
        this.column = m.column;
    }

    public void appendRow(double[][] m){
        Matrix output = Matrix.zeroMatrix(row+1, column);
        if(row==0 && column==0){
            this.data = m;
            this.row = m.length;
            this.column= m[0].length;
        }
        else{
            for (int i = 0; i < output.row; i++) {
                for (int j = 0; j < output.column; j++) {
                    if(i<row){
                        output.data[i][j] = get(i,j);
                    }
                    else{
                        output.data[i][j] = m[0][j];
                    }
                }
            }
            getMeYourselfBaby(output);
        }
    }

    public Matrix power(){
        Matrix output = clone();
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < column; j++) {
                output.data[i][j] = output.data[i][j]*output.data[i][j];
            }
        }
        return output;
    }

    public double mean(){
        return sum()/(row*column);
    }

    public double sum(){
        double sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                sum+=data[i][j];
            }
        }
        return sum;
    }

    public static Matrix addBias(Matrix input,Matrix bias){
        if(input.shape(1) != bias.shape(1)) return null;
        if(bias.shape(0) != 1) return null;
        int addRow = input.shape(0) - bias.shape(0);
        Matrix clone = bias.clone();
        Matrix added = bias.clone();
        for (int i = 0; i < addRow; i++) {
            clone.appendRow(added.data);
        }
        return Matrix.add(input,clone);
    }
}