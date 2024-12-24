# Secure Notes Application

This project is a Secure Notes Application that allows users to create, store, and view secure notes. The notes can be protected by passwords, and the application provides a simple editor and sketching tool for enhancing the notes.

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

### Class Relationships
- `Note` is extended by `SecureNote`, adding functionality for password protection.
- `LoginFrame`, `RegisterFrame`, `EditorFrame`, and `Sketch` extend `JFrame` to provide the graphical user interface components.
- `App` class is the entry point, which instantiates and manages the `GUIManager`.

### Key Components

- **SecureNote**: This class provides the mechanism to lock and unlock notes, ensuring that users' sensitive data is protected.
- **FileManager**: Provides methods to save, load, and ensure file existence for storing user data and notes.
- **EditorFrame**: Allows users to create notes with rich text support (markdown).
- **Sketch**: Lets users draw sketches and save them to their notes.

## Authors
- Anas
- Islam



