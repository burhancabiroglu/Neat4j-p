package com.cabir.neat4j.math;

public class Shape {
    private int input;
    private int output;

    public Shape(int input, int output) {
        this.input = input;
        this.output = output;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Shape{" + "input=" + input + ", output=" + output + '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape shape = (Shape) o;

        if (input != shape.input) return false;
        return output == shape.output;
    }

    @Override
    public int hashCode() {
        int result = input;
        result = 31 * result + output;
        return result;
    }
}
