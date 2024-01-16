# Internet Banking
## _JavaFX Project_

This project is done in Inteliji Idea using Java language and also SQL, creating a database connection to PostgreSQL. The main idea of this project is to create a platform where you can LOG-IN, add a CARD, make money TRANSFERS and check your personal data, like your sold. Also there exist an admin part that can be accesed and in which all the data of the registered clients can be checked.

## Windows (Scenes)

- LOG-IN, where you can log-in or choose to create a bew account
- CREATE_NEW_ACCOUNT, where you can introduce your data and add your card to the database
- PRINCIPAL, where you can check your sold and data, also you can reach the Transfer Window
- TRANSFER, where you can transfere money to another account, to which you know the id (I-Ban)
- ADMIN, this can be reached by the Log-In, having an admin account, from here you can search and check all the informations of the clients

Every scene is represented by a fxml file, which was edited using SceneBuilder, and is managed by the Controller java class. This class contains methods which interact with the existing scenes and their objects, also, call methods from other java classes. This other classes are DbFunctions.java, which is used to established a SQL connection and to provide with the necessary SQL code comands and Transfer.java, which provides the objects for managing the TableView object of the Principal Scene.
![ProjectDiagram](https://github.com/Pop-Flaviu17/My-project/assets/61428341/41f70c02-258c-4833-8f37-a6d5eb972cc5)
This relationships can be seen in the diagram above
