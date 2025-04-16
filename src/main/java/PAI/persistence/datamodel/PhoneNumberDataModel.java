package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneNumberDataModel {

    private String countryCode;
    private String phoneNumber;

    public PhoneNumberDataModel() {}

    public PhoneNumberDataModel(String countryCode, String number) {
        this.countryCode = countryCode;
        this.phoneNumber = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return phoneNumber;
    }
}
