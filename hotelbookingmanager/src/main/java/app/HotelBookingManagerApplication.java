package app;

import com.hotel.HotelBookingManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.hotel")
public class HotelBookingManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelBookingManagerApplication.class, args);
    }

    @Bean
    public HotelBookingManager bookingManager() {
        return new HotelBookingManager(10); // set the number of rooms here
    }
}