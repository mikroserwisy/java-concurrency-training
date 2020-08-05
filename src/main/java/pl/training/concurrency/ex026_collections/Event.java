package pl.training.concurrency.ex026_collections;

import lombok.*;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Data
public class Event implements Delayed {

    private final Date startDate;

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startDate.getTime() - new Date().getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        long result= getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        }
        return 0;
    }

}
