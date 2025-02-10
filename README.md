# E-Commerce Store Order Processing System

A Java-based order processing system for an e-commerce store that specializes in selling customizable shirts and hoodies. The system processes customer orders, calculates revenue and profit, and generates detailed financial reports using multi-threaded processing.

## Features

- Multi-threaded order processing for improved performance
- Support for multiple payment methods (Wallet, Bank Card, Mastercard, Visa)
- Custom shirt size tracking and profit analysis
- Revenue and profit calculation
- CSV input processing
- Detailed financial reporting


## Output Files

The system generates three output files:
- `totalrevenue.txt`: Contains the total revenue from all orders
- `totalprofit.txt`: Contains the total profit from all orders
- `profit_per_shirt_size.csv`: Detailed breakdown of profit by shirt size

## Technical Implementation

### Multi-threading

The application uses a multi-threaded approach to process orders efficiently:
- Orders are split into equal batches
- Each batch is processed by a separate thread
- Results are aggregated after all threads complete

### Payment Strategy Pattern

The system implements the Strategy Pattern for payment processing, allowing easy addition of new payment methods by implementing the `PaymentStrategy` interface.
