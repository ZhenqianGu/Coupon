# Coupon
CS401 spring project is to build a coupon inventory system (CIS) using JAVA. This project exercises data structure topics discussed under this course. Project must use abstract such as interface(s).

The system has following data field:
1. Name of Coupon Provider (max field: 20 bytes, e.g. Groupon, Living Social, DealDaddie, etc.)
2. Name of product (max field: 20 characters)
3. Price of product (e.g. $10, $20, $50, etc.)
4. Discount rate of the coupon (e.g. range between 5% and 80%)
5. Expiration Period (e.g. range between 0 and 365 days)
6. Status of a coupon: Unused or Redeemed

Let the project create the list by both a file input and by a manual user input. (FREE DESIGN)

User’s initial input creates an unsorted list(UL).
When a user selects a menu, then construct a SL (Sorted List, depends on with a key field).
The UL also needs two different ways to create an array:
1. Default value of N (e.g. use N = 30)
2. User input array size
