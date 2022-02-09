public class ComputusAlgorithim {
    private int day;
    private int month;

    /**
     * Mees/Jones/Butcher algorithim. Chose to do the version with less variables which would be the New Scientist
     * Publication in 1961. Omits the O and f variables to compute exact date otherwise you would have to +1 for valid day.
     * @param year Takes a positive integer and calculates the month and day easter occurred on.
     */
    public void getEasterDate(int year){

        /** Meeus/Jones/Butcher algorithm. Chose to the version with less variables so the New Scientist publication
        of it in 1961. Omits the O and f variables to compute exact date otherwise you would have to +1 to o for day
         */
        int a, b, c, d, e, g, h, i, j, k, l, m, n, o, p;
        a = year % 19;
        b = year /100;
        c = year % 100;
        d = b/4;
        e= b % 4;
        g = ((8*b)+13)/25;
        h = ((19*a)+b-d-g+15)%30;
        i = c/4;
        k = c % 4;
        l =(32+ (2*e)+(2*i)-h-k)%7;
        m = (a + (11 * h)+ (19 * l))/433;
        n = (h + l - (7 * m)+90)/25;
        p = (h + l - (7*m)+(33 * n) + 19)%32;

        setDay(p);
        setMonth(n);
    }

    /**
     * Simple getters and setters for the dates.
     */
    public int getDay(){
        return day;
    }
    public void setDay(int theday){
        day = theday;
    }

    public int getMonth(){
        return month;
    }
    public void setMonth(int themonth){
        month = themonth;
    }
    //If month is 3 return string March else it could only be 4 Therefore string April

    /**
     * returns String month based off of the integer passed in.
     * Because there are only 2 possible months for easter only need to worry about
     * cases 3 and 4.
     * @return
     */
    public String GetEasterMonth(){

        if(getMonth() == 3)
            return "March";
        else
            return "April";
    }
}
