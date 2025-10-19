# Virtual-ATM
This is a ATM Simulation Desktop Application
📅 Duration
Jan 2025 – Apr 2025
📖 Overview
The Virtual ATM is a desktop-based application that simulates real-world ATM operations, including withdrawals, deposits, PIN management, balance inquiry, and mini-statements.
It provides a user-friendly interface and secure database-backed transactions, replicating core banking functionalities.
🧰 Tech Stack
Component	Technology
Frontend (GUI)	Java Swing
Backend Database	MySQL
Programming Language	Java
IDE (Recommended)	IntelliJ IDEA / VS Code
Version Control	Git & GitHub
✨ Features
•	💳 Account Authentication — Secure user login using card number and PIN validation.
•	💰 Withdraw & Deposit — Perform transactions with real-time balance updates.
•	📊 Balance Inquiry & Mini Statement — View current balance and recent transaction history.
•	🧩 PIN Management — Change or reset ATM PIN securely.
•	⚙️ Modular Design (OOP) — Implemented using object-oriented principles for scalability and maintainability.
•	🗄️ Database Connectivity — Uses JDBC to connect and manage data in MySQL.
________________________________________
🧱 Project Structure
Virtual-ATM/
│
├── src/                      # Source code files (Java)
│   ├── Login.java
│   ├── Transactions.java
│   ├── Withdraw.java
│   ├── Deposit.java
│   └── ...
│
├── .gitignore
├── README.md
└── Banking Management System.iml (local IDE file - ignored)
⚙️ Installation & Setup
1. Clone the repository
git clone https://github.com/rameshyadav52399/Virtual-ATM.git
cd Virtual-ATM
2. Set up the database
•	Open MySQL and create a database, e.g	CREATE DATABASE banking-management-system;
•	Run your SQL schema file 
•	Update database credentials in your Java code (e.g., inside Conn.java):
•	String url = "jdbc:mysql://localhost:3306/banking-management-system ";
•	String user = "root";
•	String password = "your_password";
3. Compile and run the project
If using command line:
javac -d out src/*.java
Or simply run from your IDE (IntelliJ / VS Code).
🧠 Concepts Implemented
•	Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism).
•	Event Handling in Java Swing.
•	JDBC-based database operations.
•	Form validation and user authentication.

👨‍💻 Author
Ramesh Yadav
📧 ramesh.yadav52399@gmail.com
🌐 Portfolio https://rameshyadav52399.github.io/portfolio/

