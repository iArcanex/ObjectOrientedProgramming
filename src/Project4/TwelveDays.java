package Project4;

/**Week 4
 * CSci 2001-91
 */

/* 5.29 - 12 Days
* Utilizing repetition and switch statements
* the song Twelve Days of Christmas, can be printed. */

public class TwelveDays {

    public static void main(String args[]) {

        int day;
        String verse = "";

        for(day = 1; day <=12; day++) {

            switch (day) {

                case 1:
                    verse = "\nOn the first ";
                    break;
                case 2:
                    verse = "\nOn the second ";
                    break;
                case 3:
                    verse = "\nOn the third ";
                    break;
                case 4:
                    verse = "\nOn the fourth ";
                    break;
                case 5:
                    verse = "\nOn the fifth ";
                    break;
                case 6:
                    verse = "\nOn the sixth ";
                    break;
                case 7:
                    verse = "\nOn the seventh ";
                    break;
                case 8:
                    verse = "\nOn the eigth ";
                    break;
                case 9:
                    verse = "\nOn the ninth ";
                    break;
                case 10:
                    verse = "\nOn the tenth ";
                    break;
                case 11:
                    verse = "\nOn the eleventh ";
                    break;
                case 12:
                    verse = "\nOn the twelfth ";
                    break;
            }

            verse += "day of Christmas, my true love sent to me...";


            switch (day) {

                case 12:
                    verse += "\nTwelve Lords a leaping,";
                case 11:
                    verse += "\nEleven Ladies dancing,";
                case 10:
                    verse += "\nTen Pipers piping,";
                case 9:
                    verse += "\nNine Drummers drumming,";
                case 8:
                    verse += "\nEight Maids a milking,";
                case 7:
                    verse += "\nSeven Swans a swimming,";
                case 6:
                    verse += "\nSix Geese a laying,";
                case 5:
                    verse += "\nFive Gold rings,";
                case 4:
                    verse += "\nFour Colly birds,";
                case 3:
                    verse += "\nThree French hens,";
                case 2:
                    verse += "\nTwo Turtle doves,";
                case 1:
                    verse += "\nOne Partridge in a pear-tree.";
            }

        }

        System.out.print(verse);

    }


}
