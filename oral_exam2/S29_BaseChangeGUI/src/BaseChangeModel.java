public class BaseChangeModel {
    private String input;
    private int base, toBase;

    /**
     * Model controller that takes care of the conversion. in is the input base, b is the base in which the string value
     * is in, and tb is "to base" or the desired base the user would like.
     * @param in
     * @param b
     * @param tb
     */
    public BaseChangeModel(String in, int b, int tb){
        this.input = in;
        this.base = b;
        this.toBase = tb;
    }

    public String getInput(){
        return this.input;
    }
    public int getBase(){
        return this.base;
    }
    public int getToBase(){
        return this.toBase;
    }
    public void setInput(String input){
        this.input = input;
    }
    public void setBase(int base){
        this.base = base;
    }
    public void setToBase(int based){
        this.toBase =based;
    }

    /**
     * convertBase is where the value is calculated. It loops through the digit by starting at the the
     * @return
     */
    public String convertBase(){
        double num = 0;
        double digit = 0;
        char charDigit;
        //Loop through string and acquire digit. This gives us the decimal value of our original base.
        for(int i = 0; i < getInput().length(); i ++ ){
            charDigit = Character.toUpperCase(input.charAt(input.length()-1-i));


            //gets the decimal of the digit using letter math, where for example Z - A is 25
            if(Character.isLetter(charDigit)){
                digit = charDigit -'A' +10;
            }
            else if(Character.isDigit(charDigit)){
                digit = charDigit - '0';
            }
            else{
                return "Invalid Entry";
            }

            num += digit * Math.pow(base,i);

        }
        //Determines length of the new number
        int d = 1;
        while(Math.pow(toBase, d) <= num){
            d++;
        }

        char[] newNumber = new char[d];
        double power;
        //This loop calculates the digit for this power given the toBase and casts it to a char and adds it to the array
        //in order of least significant to most significant. After adding to the array it is then converted into a string
        //and the returned in the new base.

        for(int i = d-1; i >= 0; i--){
            power = Math.pow(toBase, i);
            digit = Math.floor(num / power);
            num -= digit*power;

            if(digit <= 9 ){
                newNumber[d-1-i] = (char)('0' + (int)digit);
            }
            else{
                newNumber[d-1-i] = (char)('A' + (int)digit-10);
            }
        }

        return new String(newNumber);


    }
}
