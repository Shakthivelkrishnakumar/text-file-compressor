**Overview:**
The FileReaderDecompressor and FileReaderGUI are Java Swing applications designed to read text files, apply a specially crafted 62-bit number system for hashing, and implement a centralized dictionary for text compression and decompression.

**Features:**

**Text File Reading:**
 
 Both applications allow users to select and read text files.

**62-bit Number System:**
 
  Employs a custom 62-bit number system for hashing text data.

**Centralized Dictionary:**
  
  Utilizes a centralized dictionary to compress and decompress text.

**Database Integration:**
  
  Interacts with an Oracle database (configured for local access) to manage the dictionary.

**FileReaderDecompressor:**

-Reads a text file and decompresses text using the centralized dictionary.

-Decompression involves converting specially encoded sequences back to their original textual representations.

**FileReaderGUI:**

-Reads a text file and compresses text using the centralized dictionary.

-Compression involves converting text into specially encoded sequences based on the 62-bit number system.

**Setup:**

**Environment Setup:**

  Ensure Java and an Oracle database are properly installed and configured.
  
**Database Configuration:**

  Modify the database connection parameters (jdbc:oracle:thin:@localhost:1521:xe, username, password) in the code to match your setup.

**Running the Applications:**

  Execute the respective main methods in FileReaderDecompressor and FileReaderGUI classes to launch the applications.

**Usage:**

**FileReaderDecompressor:**

  Open a text file to decompress its content using the centralized dictionary.
**FileReaderGUI:**

  Open a text file to compress its content using the centralized dictionary.

**Note:**

The applications are configured to work with an Oracle database running locally on port 1521 with a schema named javaproject and password loshak.
Modify database connection details and schema credentials as per your setup in both applications.
These applications showcase a basic implementation; further enhancements can be made for efficiency, error handling, and user experience.

**Authors:**

Shakthivel K

Lokhesh R J
