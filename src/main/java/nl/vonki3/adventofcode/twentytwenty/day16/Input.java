package nl.vonki3.adventofcode.twentytwenty.day16;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Input {
    final List<Klasse> klasses = new ArrayList<>();
    final Ticket myTicket = new Ticket();
    final List<Ticket> nearbyTickets = new ArrayList<>();
}
