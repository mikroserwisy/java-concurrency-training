package pl.training.concurrency.ex018_completion_service;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        Printer printer = new Printer(completionService);
        executorService.execute(printer);
        completionService.submit(new ReportGenerator());
        completionService.submit(new ReportGenerator());
        completionService.submit(new ReportGenerator());
        executorService.shutdown();
    }

}
