package io.codelex.myflightplanner.Requests;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


public class SearchFlightsRequest {
    @Valid
    @NotBlank
    String from;
    @Valid
    @NotBlank
    String to;
    @Valid
    @NotBlank
    String departureDate;

    public static boolean compareFlights(SearchFlightsRequest flight, SearchFlightsRequest flight2) {
        return flight.from.equals(flight2.from) &&
                flight.to.equals(flight2.to) &&
                flight.departureDate.equals(flight2.departureDate);
    }


    public SearchFlightsRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public static boolean isProperlyFormatted(String search) {

        return StringUtils.countMatches(search, "{\"from\":\"") == 1 &&
                StringUtils.countMatches(search, "\",\"to\":\"") == 1 &&
                StringUtils.countMatches(search, "\",\"departureDate\":\"") == 1;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    @Override
    public String toString() {
        return "SearchFlightsRequest{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }

}
