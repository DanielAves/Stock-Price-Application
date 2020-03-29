This project contains two directories:
- 'ExternalApis' - This houses the main code and client interface for integrating with the various APIS.
- 'OwnApi' - Contains the code to run a local host API.

I have used Intellij to build and package the various frameworks.
These directories will need to be opened as separate projects within Intellij.

Lib folder contains the relevent Jar files to compile and run the code.


Setup Steps:

1. Open the projects separately in Intellij
2. Add the 'Lib' folder dependency to Intellij which can be found in cwk2 & OwnApi respectively. This needs to be done for both projects. File > Project Structure > Modules > Dependencies.
3. In the project OwnAPi, run 'Cwk2StartUp', this will start the server.
4. Next run 'Main' found in the project 'api'. Maven should be configured automatically and be set-up with the required dependencies to run Spark.
5. Navigate to http://localhost:8090/
6. Play around searching stock prices for various companies. (Currently the project is looking at the US Stock market, so pick US Companies) 

