## Library Management Hackathon

### Features implemented:-
- Users {Buyer, Seller} signup, login
- Seller can manage thier inventory, upload book and track sales
- Buyer can search for books, add to cart and the purchase from a particular seller using different payment methods.
- Buyer can download the purchased book from the cloud storage.
- Authorization, Authentication to process only valid request.
- Catalogue to organise books with different price option from different sellers, so the users has the best price to choose from.

### Design patterns used to implement above features:-
- Singleton, Builder, Simple factory, Factory method for creating different objects.
- Chain of responsibility, Authentication, Authorization so only seller can upload book not a buyer.
- Observer pattern, when the payment is done, notify order. When seller adds a book, add the book in catalogue too.
- Visitor pattern, to calculate the cart value. Keeping it extendible so discout coupon could also be added in future.
- State pattern, payment state and order state management.

### Team members:-
- Only me

### Step to run the application
- Open the project and run the main method of class App which in the root of the folder.

### Feature yet to be implemented
- Real User Interface for interaction.
- Realistic implementation of digital rights management, such as different encryption algorithm and licencing server.
- Design FrontController using Reflection api to add Authentication, Authorization middleware.

### Apologies
- Couldn't complete UML, ran out of free objects on Lucid charts, wasn't aware of free policy. will try to complete in some other free available tool.