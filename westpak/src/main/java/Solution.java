import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.*;

public class Solution {

    private static final boolean DEBUG = !true;

    public static enum Side {
        BUY, SELL;
    }

    public static class Order implements Comparable<Order> {
        public static final Collector<Order, StringJoiner, String> ORDERID_COLLECTOR =
            Collector.of(
                () -> new StringJoiner(","),     // supplier
                (j, o) -> j.add("" + o.orderId), // accumulator
                (j1, j2) -> j1.merge(j2),        // combiner
                StringJoiner::toString);         // finisher
        private final Long orderId;
        private final String product;
        private final Side customerSide;
        private long size;
        public Order(Long orderId, String product, Side customerSide, long size) {
            this.orderId = orderId;
            this.product = product;
            this.customerSide = customerSide;
            this.size = size;
        }
        public Long getOrderId() {
            return orderId;
        }
        public String getProduct() {
            return product;
        }
        public Side getCustomerSide() {
            return customerSide;
        }
        public long getSize() {
            return size;
        }
        public void setSize(long size) {
            this.size = size;
        }
        public long adjustSize(long value) {
            final long result;
            if (this.size > value) {
                result = 0;
                this.size -= value;
            } else {
                result = value - this.size;
                this.size = 0;
            }
            return result;
        }
        public boolean isBuy() {
            return Side.BUY == customerSide;
        }
        public boolean isSell() {
            return Side.SELL == customerSide;
        }
        public boolean match(Order order) {
            return product.equals(order.product)
                && !customerSide.equals(order.customerSide)
                && size > 0 && order.size > 0;
        }
        @Override
        public String toString() {
            return orderId + " " + product + " " + customerSide + " " + size;
        }
        @Override
        public int compareTo(Order o) {
            return this.orderId.compareTo(o.orderId);
        }
    }

    public static class Engine {
        private final List<Order> orders;
        public Engine(List<Order> orders) {
            this.orders = orders
                .stream().sorted((o1, o2) -> {return o1.compareTo(o2);})
                .collect(Collectors.toList())
                ;
        }
        public void run() {
            orders.stream().forEach(o -> process(o));
            if (DEBUG) {
                orders.stream().forEach(System.err::println);
            }
        }
        private void process(Order order) {
            // filter out BUY order (dummy print)
            if (order.isBuy()) {
                System.out.println("0 matches: ");
                return;
            }
            // order is SELL (adjust and print)
            // filtered are BUY
            List<Order> buyOrders = orders.stream()
                .filter(o -> o.match(order))
                .collect(Collectors.toList());
            final String msg;
            if (buyOrders.isEmpty()) {
                msg = "0 matches: ";
            } else {
                // Adjust size of both matched orders accordingly removing any order where size is 0 from your state
                List<Order> processed = new ArrayList<>();
                long sellSize = order.getSize();
                for (Order buyOrder : buyOrders) {
                    if (sellSize > 0) {
                        sellSize = buyOrder.adjustSize(sellSize);
                        order.setSize(sellSize);
                        processed.add(buyOrder);
                    }
                }
                msg = String.format("%d matches: %s", processed.size(), processed.stream().collect(Order.ORDERID_COLLECTOR));
            }
            System.out.println(msg);
        }
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Stream.Builder<Order> builder = Stream.<Order>builder();
        try (Scanner scanner = new Scanner(System.in);) {
            while (scanner.hasNext()) {
                Order order = new Order(
                    scanner.nextBigInteger().longValue(),
                    scanner.next(),
                    Side.valueOf(scanner.next()),
                    scanner.nextBigInteger().longValue());
                builder.add(order);
            }
        }
        Engine engine = new Engine(builder.build().collect(Collectors.toList()));
        engine.run();
        //
        System.exit(0);
    }

}
