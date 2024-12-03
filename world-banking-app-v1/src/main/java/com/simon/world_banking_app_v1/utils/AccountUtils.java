package com.simon.world_banking_app_v1.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";

    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account created";

    public static final String ACCOUNT_CREATION_CODE = "002";

    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account has been created successfully!";

    public static final String ACCOUNT_NUMBER_NON_EXISTS_CODE = "003";

    public static final String ACCOUNT_NUMBER_NON_EXISTS_MESSAGE = "Provided account number does not exist";

    public static final String ACCOUNT_NON_FOUND_CODE = "004";

    public static final String ACCOUNT_NUMBER_FOUND_MESSAGE = "Account account number found";

    public static final String ACCOUNT_CREDITED_SUCCESS_CODE = "005";

    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "Account credited successfully";

    public static final String INSUFFICIENT_BALANCE_CODE = "006";

    public static final String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Balance";

    public static final String ACCOUNT_DEBITED_CODE = "007";

    public static final String ACCOUNT_DEBITED_MESSAGE = "Account has been debited successfully!";

    public static final String TRANSFER_SUCCESSFUL_CODE = "008";

    public static final String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer Successful";




    public static String generateAccountNumber(){

        /*
        * this algorithm ensure that your account number will be a total of 10 digits
        * concatenate the current + any six random digits
         */

        //to get current yea -- which will give us the first 4 digits
        Year currentYear = Year.now();

        //this will give us the next random 6 digits
        int min = 100000;
        int max = 999999;

        //generate a random number between min and max
        int randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);

        //convert current year and random number to string and then concatenate them
        String year = String.valueOf(currentYear);
        String randomNum = String.valueOf(randomNumber);

        //append both the current YEar ad the random number to generate the 10 digits
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append(year).append(randomNum).toString();
    }
}

