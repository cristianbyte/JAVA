package entity;

public class Booking {

    private int id;
    private String booking_date;
    private String seat;
    private int id_passenger;
    private int id_flight;
    private String name_passenger;
    private String model_flight;

    public Booking() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getId_passenger() {
        return id_passenger;
    }

    public void setId_passenger(int id_passenger) {
        this.id_passenger = id_passenger;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    public String getName_passenger() {
        return name_passenger;
    }

    public void setName_passenger(String name_passenger) {
        this.name_passenger = name_passenger;
    }

    public String getModel_flight() {
        return model_flight;
    }

    public void setModel_flight(String model_flight) {
        this.model_flight = model_flight;
    }

    public Booking(int id, String booking_date, String seat, int id_passenger, int id_flight, String name_passenger, String model_flight) {
        this.id = id;
        this.booking_date = booking_date;
        this.seat = seat;
        this.id_passenger = id_passenger;
        this.id_flight = id_flight;
        this.name_passenger = name_passenger;
        this.model_flight = model_flight;
    }

    @Override
    public String toString() {
        return STR."Booking{id=\{id}, booking_date='\{booking_date}\{'\''}, seat='\{seat}\{'\''}, id_passenger=\{id_passenger}, id_flight=\{id_flight}, name_passenger='\{name_passenger}\{'\''}, model_flight='\{model_flight}\{'\''}\{'}'}";
    }

    public String toString(boolean names) {
        return STR."Booking{id=\{id}, booking_date='\{booking_date}\{'\''}, seat='\{seat}\{'\''}\{names ? STR.", name_passenger='\{name_passenger}\{'\''}, model_flight='\{model_flight}\{'\''}" : STR.", id_passenger=\{id_passenger}, id_flight=\{id_flight}"}\{'}'}";
    }
}
