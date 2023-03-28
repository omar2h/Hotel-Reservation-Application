# Hotel-Reservation-Application
The Hotel Reservation App is a Java application that allows customers to search for available rooms, book a room, and view their reservations. Administrators can view all customer accounts, rooms, and reservations, as well as add new rooms to the hotel.
The application demonstrates the ability to design classes using OOP, organize and process data with collections, and use common Java types.

## Components
The main components of the Hotel Reservation Application include:

- Command Line Interface (CLI) for the user interface
- Java code for business logic
- Java collections for in-memory storage of data

## Application Architecture
The application is divided into the following layers:

- User interface (UI)
- Resources, which act as the Application Programming Interface (API) to the UI
- Services, which communicate with resources and each other to build business logic
- Data models, which represent the domain within the system (e.g., rooms, reservations, and customers)

Layering is used to support modularization and decoupling, which allows for easier changes to components without affecting other parts of the application. For example, changing the UI from a command-line interface to a webpage can be done without affecting the services or data models.

## Features
- View available rooms and make reservations
- View customer reservations
- Create a new customer account
- Add new rooms

## Getting Started
To get started with the Hotel Reservation Application, follow these steps:

1. Clone the repository
2. Compile the Java code
3. Run the application

## Requirements

### User Scenarios
- **Creating a customer account**
To create a customer account, select the "Create Account" option from the main menu. You will be prompted to enter your first name, last name, and email address. The email address must be in the format name@domain.com. Once you have entered your information, a new customer account will be created and you will be returned to the main menu.

- **Searching for rooms**
To search for available rooms, select the "Search Rooms" option from the main menu. You will be prompted to enter your check-in and check-out dates. The application will display a list of available rooms for your selected dates, along with the price per night. If no rooms are available for your selected dates, the application will search for recommended rooms on alternative dates.

- **Booking a room**
To book a room, select the "Book Room" option from the main menu. You will be prompted to enter your check-in and check-out dates, as well as the room number you wish to book. The application will verify that the room is available for your selected dates and create a reservation. You will be provided with a confirmation number for your reservation.

- **Viewing reservations**
To view your reservations, select the "View Reservations" option from the main menu. You will be prompted to enter your email address. The application will display a list of all reservations associated with your email address.

### Admin Scenarios
- **Displaying all customer accounts**
To display all customer accounts, select the "View Customers" option from the main menu. The application will display a list of all customer accounts, including their first name, last name, and email address.

- **Viewing all rooms**
To view all rooms in the hotel, select the "View Rooms" option from the main menu. The application will display a list of all rooms, including their room number, price per night, and occupancy type.

- **Viewing all reservations**
To view all reservations in the hotel, select the "View Reservations" option from the main menu. The application will display a list of all reservations, including the confirmation number, room number, check-in date, and check-out date.

- **Adding a room to the hotel**
To add a new room to the hotel, select the "Add Room" option from the main menu. You will be prompted to enter the room number, price per night, and occupancy type (single or double). The application will verify that the room number is unique and add the new room to the hotel.

### Error Handling
The Hotel Reservation App handles all exceptions gracefully, meaning that the application does not crash based on user input and there are no unhandled exceptions. If an exception occurs, the application will display a helpful error message to the user.

### Room Requirements
Rooms in the hotel have a price per night, unique room numbers, and occupancy types (single or double).

### Customer Requirements
Customers have a unique email address, first name, and last name. The email address must be in the format name@domain.com.

### Reservation Requirements
Reservations must avoid conflicting reservations, meaning that a single room may only be reserved by a single customer per check-in and check-out date range. If no rooms are available for the customer's selected dates, the application will search for recommended rooms on alternative dates.
