MusicApi is a small program that can send and receive information about songs and artists.

Notes about the program:

As of this moment the application can only be started from the main method within the 'MusicApiApplication' class.
This is a fairly standard spring boot web application that uses a SqlLite database. When starting the application the 
database if not present will be generated. The zip with the project will contain a database that is already filled with
the provided songs and artists. These can also be both added by calling the "bulkInsert" endpoints in the Song and Artist 
controllers. The controllers both contain all the standard CRUD operations. Beside the standard operations you can search on :
- Both an artist and song through a genre.
- An artist by name.
- A song by year.

The database is easily manageable with SqlLiteStudio a database manager which can be found on "https://sqlitestudio.pl/".

Things to upgrade:
- Implement response-entities
  - The api is now sometimes returns null values back to the user. This will be better if there is a clear reason why the api is returning nothing.
- Create more unit tests to obtain more test coverage.
- Make the application runnable through the created jar file in the target file.
- All the other extra requirements possible :).

Thanks for reviewing my code see you Friday! Cheers Laszlo.

