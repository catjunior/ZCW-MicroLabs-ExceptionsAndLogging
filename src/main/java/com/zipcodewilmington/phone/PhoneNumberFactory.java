package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberFactory.class);

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {

        PhoneNumber[] phoneNumArr = new PhoneNumber[phoneNumberCount];

        for(int i = 0; i < phoneNumberCount; i++){
            phoneNumArr[i] = createRandomPhoneNumber();
        }

        return phoneNumArr;
    }



    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    private static PhoneNumber createRandomPhoneNumber() {

        RandomNumberFactory rn = new RandomNumberFactory() {};

        int areaCode = rn.createInteger(100, 999);
        int centralOfficeCode = rn.createInteger(100, 999);
        int phoneLineCode = rn.createInteger(1000, 9999);


        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {

        try { // converting int input to string, so we can use createPhoneNumber(Str);
            String intNumToStr = "(" + areaCode + ")-" + centralOfficeCode + "-" + phoneLineCode;

            return createPhoneNumber(intNumToStr);

        } catch (InvalidPhoneNumberFormatException e) {

            logger.info(areaCode + centralOfficeCode + phoneLineCode + " is not a valid phone number");
            return null;
        }

    }


    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException{

        PhoneNumber phoneNumber = new PhoneNumber(phoneNumberString);
        logger.info("Attempting to create a new PhoneNumber object with a value of "+ phoneNumber.getAreaCode() + phoneNumber.getCentralOfficeCode() + phoneNumber.getPhoneLineCode());
        return phoneNumber;

    }
}