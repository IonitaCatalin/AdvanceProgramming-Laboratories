package org.laborator;

public class Token {
   private int value;
   private boolean blankNumber=false;

    public Token(int value, boolean blankNumber) {
        this.value = value;
        this.blankNumber = blankNumber;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public boolean isBlank()
    {
        return blankNumber?true:false;
    }
    @Override
    public String toString() {
        return value+"("+blankNumber+")";
    }
}
