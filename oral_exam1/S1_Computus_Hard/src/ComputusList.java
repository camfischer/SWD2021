
public class ComputusList{

    private final int[] marchDates = new int[11];
    private final int[] aprilDates = new int[26];
    private final int repeat = 5700000;

    /**
     * Because it is not asking for input it is void and just needs to be called.
     * Create new instances and increase the matching index in the appropriate list.
     */
    public void Eastercount(){
        for(int i =0; i < repeat; i++){
            ComputusAlgorithim easter = new ComputusAlgorithim();
            easter.getEasterDate(i);
            if(easter.getMonth() == 3) // Because earlies is 22, subtract 22 otherwise out of bounds
                marchDates[(easter.getDay())-22]++;
            else //Same as March except earliest is April Fools!
                aprilDates[(easter.getDay()-1)]++;
        }



    }


    //Figured I'd make a print statement to make main method look nicer

    /**
     * Simple print method to keep main less bnusy.
     */
    public void print(){
        for(int i =0; i < marchDates.length;i++ ){
            System.out.println("March" + (i+22)+ " - " + marchDates[i]);
        }
        for(int i =0; i < aprilDates.length;i++ ){
            System.out.println("April " + (i+1)+ " - " + aprilDates[i]);
        }
    }
}
