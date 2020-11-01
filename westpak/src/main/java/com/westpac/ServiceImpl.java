package com.westpac;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Added ThreadPoolExecutor to submit requests in multiple threads (was single thread before).
 * Commented out "synchronized" block (have to reduce AccountLookupServiceImpl.nThreads to 25).
 * TODO: eliminate "synchronized" block in ALL other classes
 */

public class ServiceImpl implements Service {

    private final int nThreads = 10;
    private final ThreadPoolExecutor executor;

    private final AccountLookupService accountLookupService;
    private final LegacyService legacyService;

    public ServiceImpl(AccountLookupService accountLookupService, LegacyService legacyService) {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
        //this.executor = new ThreadPoolExecutor(nThreads, nThreads, 50_000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        //
        this.accountLookupService = accountLookupService;
        this.legacyService = legacyService;
    }

    @Override
    public void submit(final Request request) {
        executor.submit(() -> {
            /*
            System.out.println(String.format("[%d] submit",
                Thread.currentThread().getId()));
            //*/
            accountLookupService.lookup(request.getId(), request.getClientId(), new AccountLookupService.Callback() {
                @Override
                public void onResult(String id, String accountId) {
                    /*
                    System.out.println(String.format("[%d] onResult",
                        Thread.currentThread().getId()));
                    //*/
                    //synchronized (legacyService) {
                        legacyService.execute(request, accountId);
                    //}
                }
            });
        });
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }
}
