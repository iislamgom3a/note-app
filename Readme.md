# Secure Notes Application

This project is a Secure Notes Application that allows users to create, store, and view secure notes. The notes can be protected by passwords, and the application provides a simple editor and sketching tool.

## Features
- **User Registration and Login**: Users can create an account and log in using a username and password.
- **Secure Notes**: Notes can be locked with a password, requiring authentication to unlock and view the content.
- **Markdown Editor**: A text editor with a live preview of the notes, supporting markdown formatting.
- **Drawing Tool**: A sketchpad that allows users to add sketches to their notes.
- **File Management**: Notes are saved to and loaded from files, ensuring data persistence.
- **Password Hashing**: Passwords are securely hashed before storage.

## Architecture

### Classes

- **App**: Entry point of the application. Initializes and runs the main program.
- **GUIManager**: Manages the GUI components and transitions between different frames such as login, registration, editor, and sketching.
- **EditorFrame**: Provides the text editor with live markdown preview.
- **FileManager**: Handles reading and writing files, ensuring data is stored correctly.
- **Note**: A basic class representing a note.
- **SecureNote**: Extends `Note`, allowing notes to be secured with a password.
- **User**: Handles user registration, login, and password management.
- **Sketch**: Provides a drawing panel to add sketches to notes.
- **LoginFrame**: GUI component for user login.
- **RegisterFrame**: GUI component for user registration.
- **Images**: Handles adding and managing images in the notes.

 ## screenshots
![alt text](</attachments/2025-05-31 07_20_53-Microsoft Store.png>) ![alt text](</attachments/2025-05-31 07_26_48-Microsoft Store.png>)  ![alt text](</attachments/2025-05-31 07_29_55-Microsoft Store.png>)

## Authors

- [Islam Gomaa](https://github.com/iislamgom3a)
- [Anas Osama](https://github.com/anas3654/)
