# nbaAppAndroidApplication
This is the application part of my 95702 class project.

For the Android Web Service: https://github.com/ziangit/nbaAppAndroidWebService

For the GitHub CodeSpace deployment: https://ziangit-ubiquitous-space-fishstick-9r5x6r4v9g9cxqvr.github.dev/

The distributed NBA application will run on an Android device. 
The user can search for NBA player’s seasonal statistics by entering the player’s first name, last name, and the season. 
After the user enters the data and clicks the “submit” button, the Android app makes an HTTP request to the web service deployed on GitHub, 
and the web service calls a third-party API, performs the calculation, and returns the seasonal statistics of the player to the Android app. 
Besides the Android app, the distribution application also has a browser-based dashboard which records three interesting statistics 
(average latency, most popular player, most popular user agent) and logs. This is achieved by a NoSQL database MongoDB. 
The webservice records the logs and useful statistics, stores them in the database, and displays the data on the dashboard.

# Diagram

![Diagram-Full](https://github.com/ziangit/nbaAppAndroidApplication/assets/110576506/933c8a2c-71bc-4e66-adb6-1616e620664f)

# Search for player

![SearchForPlayer](https://github.com/ziangit/nbaAppAndroidApplication/assets/110576506/daf48324-cce6-493b-8d23-84166e42ff38)

# Search result

![SearchResult](https://github.com/ziangit/nbaAppAndroidApplication/assets/110576506/fd1adf35-d349-4cc0-ab12-3d57faee2000)

# Repeatable operations

![Repeatable](https://github.com/ziangit/nbaAppAndroidApplication/assets/110576506/d64acd66-61d1-402b-82e4-768740ab71b8)






