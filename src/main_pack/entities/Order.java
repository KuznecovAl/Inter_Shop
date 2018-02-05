package main_pack.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class Order {

    private long id;
    private long id_user;
    private LocalDateTime date_time;
    private String status;


    public Order(long id_user, LocalDateTime date_time, String status) {
        this.id = -1;
        this.id_user = id_user;
        this.date_time = date_time;
        this.status = status;
    }
}

