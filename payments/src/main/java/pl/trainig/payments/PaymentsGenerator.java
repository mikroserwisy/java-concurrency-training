package pl.trainig.payments;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentsGenerator {

    private static final int MAX_PAYMENT_AMOUNT = 10_000;

    private final Random random = new Random();

    public Flux<Payment> paymentsStream(Duration duration) {
        return Flux.generate(this::paymentSink)
                .zipWith(Flux.interval(duration))
                .map(Tuple2::getT1);
    }

    private void paymentSink(SynchronousSink<Payment> sink) {
        sink.next(createPayment());
    }

    private Payment createPayment() {
        String transactionId = UUID.randomUUID().toString();
        long amount = random.nextInt(MAX_PAYMENT_AMOUNT) + 1;
        Instant timestamp = Instant.now();
        return new Payment(transactionId, amount, timestamp);
    }

}
