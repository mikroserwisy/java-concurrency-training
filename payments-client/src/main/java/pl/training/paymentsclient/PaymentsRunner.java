package pl.training.paymentsclient;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@ConfigurationProperties("payments")
@Setter
@Component
@RequiredArgsConstructor
public class PaymentsRunner implements CommandLineRunner {

    private final PaymentsRepository paymentsRepository;

    private String url;
    private String path;

    private Flux<Payment> getPayments() {
        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(path)
                .retrieve()
                .bodyToFlux(Payment.class);
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<Payment> savedPayments = getPayments()
                .flatMap(paymentsRepository::save);

        savedPayments
                .map(Payment::getAmount)
                .filter(amount -> amount < 5_000)
                .buffer(5)
                .subscribe(System.out::println, System.out::println);
    }

}
