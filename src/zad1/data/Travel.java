package zad1.data;


public class Travel {
    private final String locale;
    private String country;
    private String startDate;
    private String endDate;
    private final String place;
    private final String price;
    private final String currency;

    public Travel(String locale, String country, String startDate,
                  String endDate, String place, String price, String currency) {
        this.locale = locale;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.price = price;
        this.currency = currency;
    }

    public String getLocale() {
        return locale;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getPlace() {
        return place;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Travel{" +
            "locale = '" + locale + '\'' +
            ", country = '" + country + '\'' +
            ", start date = '" + startDate + '\'' +
            ", end date = '" + endDate + '\'' +
            ", place = '" + place + '\'' +
            ", price = '" + price + '\'' +
            ", currency = '" + currency + '\'' +
            '}';
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
