package zad1.data;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TravelData {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final List<Travel> travelData = new ArrayList<>();

    public TravelData(File dataFile) {
        fillData(new File(dataFile, "Offers.tsv"));
    }

    public TravelData(String path) {
        this(new File(path));
    }

    public List<Travel> getTravelData() {
        return travelData;
    }

    public void fillData(File path) {
        try (BufferedReader br = openFile(path)) {
            br.lines()
                .map(TravelData::parseLine)
                .forEach(travelData::add);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Travel parseLine(String line) {
        String[] res = line.split("\t");

        return new Travel(
            res[0], res[1], res[2],
            res[3], res[4], res[5], res[6]
        );
    }


    public static BufferedReader openFile(File path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path));
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        return travelData.stream()
            .map(travel ->
                transformTravel(travel, locale, dateFormat))
            .map(Travel::toString)
            .collect(Collectors.toList());
    }

    public static Travel transformTravel(Travel travel, String locale, String dateFormat) {
        String oldCountry = travel.getCountry();
        String country = translateCountry(
            travel.getLocale(),
            locale,
            oldCountry
        ).orElse(
            oldCountry.startsWith("Translation for")
                ? oldCountry
                : "Translation for " + oldCountry + " not found"
        );

        Function<String, String> transformDate =
            date -> formatDate(
                dateFormat,
                date
            ).orElse("Invalid date format");

        String startDate = transformDate.apply(travel.getStartDate());

        String endDate = transformDate.apply(travel.getEndDate());

        travel.setCountry(country);
        travel.setStartDate(startDate);
        travel.setEndDate(endDate);

        return travel;
    }

    public static Optional<String> translateCountry(String in, String out, String country) {
        Locale outLocale = Locale.forLanguageTag(out);
        Locale inLocale = Locale.forLanguageTag(in);

        for (Locale l : Locale.getAvailableLocales()) {
            if (l.getDisplayCountry(inLocale).equals(country)) {
                return Optional.of(l.getDisplayCountry(outLocale));
            }
        }

        return Optional.empty();
    }

    public static Optional<String> formatDate(String dateFormat, String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date d = sdf.parse(date);
            sdf.applyPattern(dateFormat);
            return Optional.of(sdf.format(d));
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        TravelData data = new TravelData("data");

        data.getTravelData()
            .forEach(System.out::println);

        System.out.println();

        data.getOffersDescriptionsList("PL", "yyyy-dd-MM")
            .forEach(System.out::println);
    }
}


//pobierz driver dla sqlite
